<template>
    <div id="playerlist" class="v">
        <h1>Lista graczy</h1>

        <table class="table table-hover">
            <thead>
            <tr>
                <th>Nick</th>
                <th>Stan</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="player in players" @click="onPlayerClick(player)">
                <td>{{ player.name }}</td>
                <td>{{ getStateString(player.state) }}</td>
            </tr>
            </tbody>
        </table>

        <Modal v-for="modal in modals"
               :key="modal.id"
               :id="modal.id"
               :title="modal.title"
               :body="modal.body"
               :primaryBtnText="modal.primaryBtnText"
               :secondaryBtnText="modal.secondaryBtnText"
               :primaryBtnCallback="modal.primaryBtnCallback"
               :secondaryBtnCallback="modal.secondaryBtnCallback"
               />
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
    import PlayerState from "../model/playerstate";
    import ResponseListener from "../client/responselistener";
    import Modal from "./Modal.vue";
    import * as $ from "jquery";
    (<any>window).jQuery = $
    import "bootstrap";

    @Component({
        components: {
            Modal,
        }
    })
    export default class PlayerList extends Vue {
        @Prop() client: Client;
        @Prop() app: Main;
        players: Player[] = [];
        modals = [];

        getStateString(state: string) {
            switch (PlayerState[state]) {
                case PlayerState.FREE:
                    return "Wolny";
                case PlayerState.PLAYING:
                    return "W grze";
            }
        }

        getPlayers() {
            if (this.$parent) {
                this.client.getPlayerList().then((players) => {
                    this.players = <Player[]> players;
                    setTimeout(this.getPlayers, 3000)
                });
            }
        }

        mounted() {
            this.getPlayers();
            this.client.addResponseListener("RequestGame", new ResponseListener((response) => {
                this.onGameRequested(response.nickname);
            }));
        }

        onGameRequested(nickname: string) {
            let modal = {
                id: "gr_" + nickname,
                title: "Zaproszenie do gry",
                body: `${nickname} zaprosił cię do wspólnej gry`,
                primaryBtnText: "Zaakceptuj",
                primaryBtnCallback: () => {
                    $(`#${modal.id}`).modal("hide");
                    this.modals.splice(this.modals.indexOf(modal));
                    this.acceptGameRequest(nickname);
                },
                secondaryBtnText: "Odrzuć",
                secondaryBtnCallback: () => {
                    $(`#${modal.id}`).modal("hide");
                    this.modals.splice(this.modals.indexOf(modal));
                    this.rejectGameRequest(nickname);
                }
            };

            this.modals.push(modal);

        }

        onPlayerClick(player: Player) {
            this.client.requestGame(player.name).then(() => {
                this.app.state = "game";
            });
        }

        acceptGameRequest(nickname: string) {
            this.client.requestGame(nickname).then(() => {
               this.app.state = "game";
            });
        }

        rejectGameRequest(nickname: string) {
            this.client.rejectGameRequest(nickname);
        }
    }
</script>

<style lang="scss">
</style>