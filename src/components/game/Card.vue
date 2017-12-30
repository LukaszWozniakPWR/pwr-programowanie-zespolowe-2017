<template>
    <div class="game-card" @click="click()">
        <div class="card-data" v-if="type !== 'PLACEHOLDER'">
            <div class="card-strength" v-bind:class="{
                buff: strength > basicStrength,
                nerf: strength < basicStrength
            }">{{ strength }}</div>
            <div class="card-name">{{ name }}</div>
        </div>
    </div>
</template>

<script lang="ts">
    import {Vue, Component, Prop} from "vue-property-decorator";
    import * as $ from "jquery";
    import "bootstrap";
    import Cards from "../../cards";

    @Component
    export default class Card extends Vue {
        @Prop() id: string;
        @Prop() type: string;
        @Prop() strength: number;
        @Prop() click;

        get name() {
            return (Cards[this.type]) ? Cards[this.type].name : this.type;
        }

        get description() {
            return (Cards[this.type]) ? Cards[this.type].description : "";
        }

        get basicStrength() {
            return (Cards[this.type]) ? Cards[this.type].basicStrength : this.strength;
        }

        mounted() {
        }
    }
</script>

<style>
    .game-card {
        position: relative;
        display: inline-block;
        width: 80px;
        margin-left: 10px;
        height: 108px;
        border: 2px solid #222;
        backkground: #eee;
        background: url("../../../assets/logo.png") no-repeat;
        background-size: 90%;
        background-position: center;
        border-radius: 4px;
        box-shadow: 0 0 5px rgba(0,0,0,.3);
        transition: all ease .1s;
    }

    .game-card:hover, .game-card.selected {
        box-shadow: 0 0 5px rgba(0,0,0,.8);
        transform: scale(1.1);
    }

    .card-strength {
        position: absolute;
        top: 3px;
        left: 3px;
        font-weight: 800;
        text-align: center;
        width: 30px;
        height: 30px;
        border-radius: 50%;
        border: 2px solid #222;
        color: #222;
        padding: 1px 0;
        background: rgba(255,255,255,0.8);
    }

    .card-name {
        position: absolute;
        bottom: 0;
        height: 24px;
        width: 100%;
        color: #fff;
        padding: 3px 10px;
        background: rgba(0,0,0,0.7);
        font-size: 14px;
    }

    .card-strength.buff {
        color: #1a7c26;
    }
    .card-strength.nerf {
        color: #7c1f18;
    }

    .player-side .hand .game-card {
        cursor: pointer;
    }
</style>