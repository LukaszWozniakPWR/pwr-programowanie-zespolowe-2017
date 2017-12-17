<template>
    <div id="login" class="v">
        <div id="main-logo"></div>
        <div id="login-box" class="md-form">
            <div><input type="text" placeholder="Nazwa użytkownika" class="form-control" id="nicknameInput" v-model="username"></div>
            <div><button class="btn btn-dark-green" @click="login">Start</button></div>
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

    @Component
    export default class Login extends Vue {
        @Prop() client: Client;
        @Prop() app: Main;
        @Prop() username: string = "";

        @Emit()
        login() {
            (<HTMLInputElement> document.getElementById("nicknameInput")).disabled = true;
            this.client.setNickname(this.username).then(() => {
                this.app.loggedAs = this.username;
            }).catch(() => {
                this.app.showError("Niepoprawny nick");
                (<HTMLInputElement> document.getElementById("nicknameInput")).disabled = false;
            });
        }
    }
</script>

<style lang="scss">
    #main-logo {
        width: 300px;
        height: 300px;
        background: url("../../assets/logo.png") no-repeat;
        background-size: contain;
        margin: 0 auto;
    }

    #login-box {
        button {
            width: 100%;
        }

        width: 300px;
        margin: 0 auto;
        position: relative;
        top: calc(50% - 300px);
    }
</style>