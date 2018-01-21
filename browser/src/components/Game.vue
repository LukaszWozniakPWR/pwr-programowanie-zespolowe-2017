<template>
    <div id="game">
        <div class="opponent-side">
            <div class="player-info" v-bind:class="{active: this.gamestate.turn === 'OPPONENT', passed: this.gamestate.opponentState.passed}">
                <div class="playername">{{ this.gamestate.opponent.name }}</div>
                <div class="score">Wygrane rundy: {{ this.gamestate.opponentState.score }}</div>
                <div class="points">Punkty: {{ this.gamestate.opponentState.points }}</div>
            </div>
            <div class="board">
                <div class="hand">
                    <Card v-for="i in this.gamestate.opponentState.handLength" :key="i" type="PLACEHOLDER"/>
                </div>
                <div class="gamerow" @click="rowClick(3, true)">
                    <div class="rowpoints">{{ this.gamestate.opponentState.rearRow.points }}</div>
                    <div class="rowcards">
                        <div>
                            <Card v-for="card in this.gamestate.opponentState.rearRow.elements" :key="card" :type="card.name" :strength="card.strength"/>
                        </div>
                    </div>
                </div>
                <div class="gamerow" @click="rowClick(2, true)">
                    <div class="rowpoints">{{ this.gamestate.opponentState.middleRow.points }}</div>
                    <div class="rowcards">
                        <div>
                            <Card v-for="card in this.gamestate.opponentState.middleRow.elements" :key="card" :type="card.name" :strength="card.strength"/>
                        </div>
                    </div>
                </div>
                <div class="gamerow" @click="rowClick(1, true)">
                    <div class="rowpoints">{{ this.gamestate.opponentState.frontRow.points }}</div>
                    <div class="rowcards">
                        <div>
                            <Card v-for="card in this.gamestate.opponentState.frontRow.elements" :key="card" :type="card.name" :strength="card.strength"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="player-side">
            <div class="player-info" v-bind:class="{active: this.gamestate.turn === 'YOUR', passed: this.gamestate.selfState.passed}">
                <div class="playername">{{ this.gamestate.self.name }}</div>
                <div class="score">Wygrane rundy: {{ this.gamestate.selfState.score }}</div>
                <div class="points">Punkty: {{ this.gamestate.selfState.points }}</div>
                <div>
                    <button v-bind:disabled="this.gamestate.turn !== 'YOUR'" class="btn btn-dark-green" @click="pass()">PASS</button>
                </div>
            </div>
            <div class="board">
                <div class="gamerow" @click="rowClick(1)">
                    <div class="rowpoints">{{ this.gamestate.selfState.frontRow.points }}</div>
                    <div class="rowcards">
                        <div>
                            <Card v-for="card in this.gamestate.selfState.frontRow.elements" :key="card" :type="card.name" :strength="card.strength"/>
                        </div>
                    </div>
                </div>
                <div class="gamerow" @click="rowClick(2)">
                    <div class="rowpoints">{{ this.gamestate.selfState.middleRow.points }}</div>
                    <div class="rowcards">
                        <div>
                            <Card v-for="card in this.gamestate.selfState.middleRow.elements" :key="card" :type="card.name" :strength="card.strength"/>
                        </div>
                    </div>
                </div>
                <div class="gamerow" @click="rowClick(3)">
                    <div class="rowpoints">{{ this.gamestate.selfState.rearRow.points }}</div>
                    <div class="rowcards">
                        <div>
                            <Card v-for="card in this.gamestate.selfState.rearRow.elements" :key="card" :type="card.name" :strength="card.strength"/>
                        </div>
                    </div>
                </div>
                <div class="hand">
                    <Card :click="() => handClick(i)" v-bind:class="{selected: selected === i}" v-for="(card, i) in this.gamestate.selfState.hand" :key="i" :type="card.name" :strength="card.strength"/>
                </div>
            </div>
        </div>
        <div id="game-message"></div>
        <div id="card-info"></div>
    </div>
</template>

<script lang="ts">
    import {
        Vue,
        Component,
        Prop,
        Model,
        Emit
    } from "vue-property-decorator";
    import Client from "../client/client";
    import Main from "../app.vue";
    import ResponseListener from "../client/responselistener";
    import * as $ from "jquery";
    (<any>window).jQuery = $
    import "bootstrap";
    import Card from "./game/Card.vue";
    import Cards from "../cards";
    import Animation from "../animation";
    import RowInfo from "../model/RowInfo";

    @Component({
        components: {
            Card,
        }
    })
    export default class Game extends Vue {
        @Prop() client: Client;
        @Prop() app: Main;
        @Model() startgamestate;
        selected: number = -1;
        lock = false;
        gs = null;

        mounted() {
            console.log(this.gamestate);
            this.showTurnInfo();

            this.client.addResponseListener("OpponentActionResponse", new ResponseListener((response) => {
                this.updateStateWithInfo(response);
            }));

            this.client.addResponseListener("GameEndedResponse", new ResponseListener((response) => {
                let msg = "Koniec gry";

                switch (response.reason) {
                    case "OPPONENT_DISCONNECTED":
                        msg = "Przeciwnik się rozłączył";
                        break;
                    case "WON":
                        msg = "Wygrałeś!";
                        break;
                    case "LOST":
                        msg = "Przegrałeś!";
                        break;
                }

                this.lock = true;
                let el = document.getElementById("game-message");
                el.innerHTML = msg;
                el.style.display = "block";
                setTimeout(() => {
                    el.classList.remove("show");
                    setTimeout(() => {
                        el.style.display = "none";
                        this.lock = false;
                        el.innerHTML = "";
                        this.app.state = "list";
                        this.app.gamestate = {};
                        document.body.classList.remove("game");
                    }, 250);
                }, 4000);

                el.classList.add("show");
            }));

            document.body.classList.add("game");
        }

        get gamestate() {
            if (this.gs === null) {
                return this.startgamestate;
            } else return this.gs;
        }

        handClick(index: number) {
            if (!this.lock && this.gamestate.turn === "YOUR") {
                if (this.selected == index) this.selected = -1;
                else {
                    let cardType = this.gamestate.selfState.hand[index].name;
                    let card = Cards[cardType];

                    if (card.row == RowInfo.ANY_OF_YOURS) {
                        this.selected = index;
                    } else {
                        this.lock = true;
                        this.client.putCard(index, card.row).then((response) => {
                            this.updateStateWithInfo(response['game']);
                            this.lock = false;
                            this.selected = -1;
                        }).catch(() => {
                            this.app.showError("Błąd");
                            this.lock = false;
                            this.selected = -1;
                        });
                    }
                }
            }
        }

        rowClick(row, opponentsRow: boolean = false) {
            if (!this.lock && this.gamestate.turn === "YOUR" && this.selected !== -1
                && true // can put card in row @todo
            ) {
                this.lock = true;
                this.client.putCard(this.selected, row).then((response) => {
                    this.updateStateWithInfo(response['game']);
                    this.lock = false;
                    this.selected = -1;
                }).catch(() => {
                    this.app.showError("Błąd");
                    this.lock = false;
                    this.selected = -1;
                });
            }
        }

        updateState(gamestate) {
            this.gs = gamestate;
        }

        updateStateWithInfo(gamestate) {
            this.updateState(gamestate);
            this.showTurnInfo();
        }

        showMessage(msg: string) {
            this.lock = true;
            let el = document.getElementById("game-message");
            el.innerHTML = msg;
            el.style.display = "block";
            setTimeout(() => {
                el.classList.remove("show");
                setTimeout(() => {
                    el.style.display = "none";
                    this.lock = false;
                    el.innerHTML = "";
                }, 250);
            }, 2000);

            el.classList.add("show");
        }

        showTurnInfo() {
            if (this.gamestate.turn === "YOUR") this.showMessage("Twoja tura");
            else this.showMessage("Tura przeciwnika");
        }

        pass() {
            if (!this.lock && this.gamestate.turn === "YOUR") {
                this.lock = true;
                this.selected = -1;
                this.client.pass().then((response) => {
                    this.updateStateWithInfo(response['game']);
                    this.lock = false;
                }).catch(() => {
                    this.app.showError("Błąd");
                    this.lock = false;
                });
            }
        }

    }
</script>

<style lang="scss">
    .player-info {
        width: 180px;
        display: inline-block;
    }

    .board {
        display: inline-block;
        width: 1300px;
    }

    .gamerow, .hand {
    }

    .gamerow .rowcards {
        background: url("../../assets/plank.png") no-repeat;
    }

    .rowpoints {
        display: inline-block;
        width: 80px;
        background: url("../../assets/pointsbg.png") no-repeat;
        background-position: center;
        height: 116px;
        float: left;
        padding: 40px 0px;
        text-align: center;
        font-size: 20px;
        color:  #fff;
        font-weight: 900;
    }

    .rowcards {
        width: 1210px;
        display: inline-block;
    }

    .rowcards > div, .hand {
        height: 116px;
        padding: 4px;
        display: flex;
        justify-content: center;
    }


    .player-info {
        border-radius: 4px;
        padding: 4px 6px;
        background: rgba(255,255,255,0.4);
    }

    .player-info.active {
        border: 2px solid #147631;
    }

    .player-info.passed {
        background: #eee;
    }

    #game-message {
        padding: 20px 32px;
        max-width: 800px;
        position: fixed;
        z-index: 9999;
        background: rgba(0,0,0,.8);
        opacity: 0;
        transition: opacity ease .2s;
        top: 0;
        display: none;
        font-weight: 800;
        font-size: 24px;
        color: #eee;
        top: 40%;
        left: 0;
        right: 0;
        margin: 0 auto;
    }

    #game-message.show {
        opacity: 1;
    }
</style>
