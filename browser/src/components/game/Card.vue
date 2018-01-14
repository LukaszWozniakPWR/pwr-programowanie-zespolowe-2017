<template>
    <div class="game-card" @click="click()" @contextmenu="contextmenu($event)">
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
    import CardInfo from "../CardInfo.vue";

    @Component({
        components: {
            CardInfo,
        }
    })
    export default class Card extends Vue {
        @Prop() id: string;
        @Prop() type: string;
        @Prop() strength: number;
        @Prop() click;
        cardInfo: boolean = false;

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

        contextmenu(e) {
            e.preventDefault();
            if (this.type !== "PLACEHOLDER") {
                this.showCardInfo();
            }
        }

        showCardInfo() {
            console.log(this.name, this.description, this.strength, this.basicStrength);
            new CardInfo({
                el: "#card-info",
                data: {
                    cardName: this.name,
                    description: this.description,
                    basicStrength: this.basicStrength,
                    id: "card-info",
                }
            });
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
        background: #eee url("../../../assets/logo.png") no-repeat;
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
