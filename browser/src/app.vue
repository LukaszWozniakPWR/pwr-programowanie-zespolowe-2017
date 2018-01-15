<template>
    <div class="v">
        <Login v-if="!loggedAs" :client="client" :app="this"/>
        <PlayerList v-if="loggedAs && state === 'list'" :client="client" :app="this"/>
        <Game v-if="loggedAs && state === 'game'" :client="client" :app="this" :startgamestate="this.gamestate"/>
    </div>
</template>

<script lang="ts">
    import {Vue, Component, Prop, Model} from "vue-property-decorator";
    import Login from "./components/Login.vue";
    import PlayerList from "./components/PlayerList.vue";
    import Client from "./client/client";
    import Game from "./components/Game.vue";
    import ResponseListener from "./client/responselistener";

    @Component({
        components: {
            Login,
            PlayerList,
            Game,
        }
    })
    export default class Main extends Vue {
        @Prop() name: string = "Main";
        @Model() public loggedAs: string;
        @Model() state: string = "list";
        gamestate = {};
        private client: Client;

        mounted() {
            this.client.addResponseListener("GameStartedResponse", new ResponseListener((response) => {
                this.gamestate = response;
                this.state = "game";
            }));
        }

        public showError(msg: string) {
            let el = document.createElement("div");
            el.classList.add("alert", "alert-danger");
            el.innerHTML = msg;
            document.getElementById("msgbox").appendChild(el);
            setTimeout(() => {
                document.getElementById("msgbox").removeChild(el);
            }, 1500);
        }

        public showInfo(msg: string) {
            let el = document.createElement("div");
            el.classList.add("alert");
            el.innerHTML = msg;
            document.getElementById("msgbox").appendChild(el);
            setTimeout(() => {
                document.getElementById("msgbox").removeChild(el);
            }, 1500);
        }

    }
</script>

<style>
    @import url("./../node_modules/bootstrap/dist/css/bootstrap.min.css");
    @import url("./../node_modules/mdbootstrap/css/mdb.css");
    .v {
        padding: 10px;
        width: 100%;
        height: 100%;
    }

    body.game {
        background: url("../assets/pexels-photo-139312.jpeg");
        background-size: cover;
    }
</style>
