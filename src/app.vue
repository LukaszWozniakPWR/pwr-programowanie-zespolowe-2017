<template>
    <div class="v">
        <Login v-if="!loggedAs" :client="client" :app="this"/>
        <PlayerList v-if="loggedAs && state === 'list'" :client="client" :app="this"/>
    </div>
</template>

<script lang="ts">
    import {Vue, Component, Prop, Model} from "vue-property-decorator";
    import Login from "./components/Login.vue";
    import PlayerList from "./components/PlayerList.vue"
    import Client from "./client/client";

    @Component({
        components: {
            Login,
            PlayerList,
        }
    })
    export default class Main extends Vue {
        @Prop() name: string = "Main";
        @Model() public loggedAs: string;
        @Model() state: string = "list";
        private client: Client;

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
        padding: 20px;
        width: 100%;
        height: 100%;
    }
</style>