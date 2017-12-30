<template>
    <div id="game">
        <div class="opponent-side">
            <div class="player-info">
                <div class="playername">{{ this.gamestate.opponent.name }}</div>
                <div class="score">Wygrane rundy: {{ this.gamestate.opponentState.score }}</div>
                <div class="points">Punkty: {{ this.gamestate.opponentState.points }}</div>
            </div>
            <div class="board">
                <div class="hand">
                    <Card v-for="i in this.gamestate.opponentState.handLength" :key="i" type="PLACEHOLDER"/>
                </div>
                <div class="gamerow">
                    <div class="rowpoints">{{ this.gamestate.opponentState.rearRow.points }}</div>
                    <div class="rowcards">
                        <div>
                            <Card v-for="card in this.gamestate.opponentState.rearRow.elements" :key="card" :type="card.name" :strength="card.strength"/>
                        </div>
                    </div>
                </div>
                <div class="gamerow">
                    <div class="rowpoints">{{ this.gamestate.opponentState.middleRow.points }}</div>
                    <div class="rowcards">
                        <div>
                            <Card v-for="card in this.gamestate.opponentState.middleRow.elements" :key="card" :type="card.name" :strength="card.strength"/>
                        </div>
                    </div>
                </div>
                <div class="gamerow">
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
            <div class="player-info">
                <div class="playername">{{ this.gamestate.self.name }}</div>
                <div class="score">Wygrane rundy: {{ this.gamestate.selfState.score }}</div>
                <div class="points">Punkty: {{ this.gamestate.selfState.points }}</div>
            </div>
            <div class="board">
                <div class="gamerow">
                    <div class="rowpoints">{{ this.gamestate.selfState.frontRow.points }}</div>
                    <div class="rowcards">
                        <div>
                            <Card v-for="card in this.gamestate.selfState.frontRow.elements" :key="card" :type="card.name" :strength="card.strength"/>
                        </div>
                    </div>
                </div>
                <div class="gamerow">
                    <div class="rowpoints">{{ this.gamestate.selfState.middleRow.points }}</div>
                    <div class="rowcards">
                        <div>
                            <Card v-for="card in this.gamestate.selfState.middleRow.elements" :key="card" :type="card.name" :strength="card.strength"/>
                        </div>
                    </div>
                </div>
                <div class="gamerow">
                    <div class="rowpoints">{{ this.gamestate.selfState.rearRow.points }}</div>
                    <div class="rowcards">
                        <div>
                            <Card v-for="card in this.gamestate.selfState.rearRow.elements" :key="card" :type="card.name" :strength="card.strength"/>
                        </div>
                    </div>
                </div>
                <div class="hand">
                    <Card  :click="() => handClick(i)" v-bind:class="{selected: selected === i}" v-for="(card, i) in this.gamestate.selfState.hand" :key="i" :type="card.name" :strength="card.strength"/>
                </div>
            </div>
        </div>
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

    @Component({
        components: {
            Card,
        }
    })
    export default class Game extends Vue {
        @Prop() client: Client;
        @Prop() app: Main;
        @Model() gamestate;
        selected: number = -1;
        lock = false;

        mounted() {
            console.log(this.gamestate);
        }

        handClick(index: number) {
            if (!this.lock && this.gamestate.turn === "YOUR") {
                if (this.selected == index) this.selected = -1;
                else this.selected = index;
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

    .board > div + div {
        border-top: 2px solid #aaa;
    }

    .rowpoints {
        display: inline;
        width: 20px;
    }

    .rowcards {
        width: 1250px;
        display: inline-block;
    }

    .rowcards > div, .hand {
        height: 116px;
        padding: 4px;
        display: flex;
        justify-content: center;
    }

    .board {
        border: 2px solid #aaa;
    }
</style>