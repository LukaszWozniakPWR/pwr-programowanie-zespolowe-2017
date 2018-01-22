<template>
    <div class="modal fade" :id="id" tabindex="-1" role="dialog" :aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Informacja o karcie</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="game-card-big" :class="{hasimg: img}" :style="{'background-image': img}">
                        <div class="card-data">
                            <div class="card-big-strength">{{ basicStrength }}</div>
                            <div class="card-big-name">{{ cardName }}</div>
                        </div>
                    </div>
                    <div class="card-big-description">{{ description }}</div>
                    <div class="card-big-attr" v-if="attrs.length > 0">
                        <h4>Atrybuty:</h4>
                        <div v-for="attr in attrs"><b>{{ AttributeData[attr].name }}</b>: {{ AttributeData[attr].description }}</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
    import {Vue, Component, Prop} from "vue-property-decorator";
    import * as $ from "jquery";
    import "bootstrap";
    import Attribute from "../model/Attribute";
    import AttributeData from "../AttributeData";

    @Component
    export default class CardInfo extends Vue {
        id: string;
        cardName: string;
        description: string;
        basicStrength: number;
        img: string;
        attrs: Attribute[];
        AttributeData = AttributeData;


        mounted() {
            $(`#${this.id}`).appendTo("body").modal("show");
        }
    }
</script>

<style>

    .game-card-big {
        position: relative;
        display: inline-block;
        width: 400px;
        margin-left: 10px;
        height: 460px;
        border: 2px solid #222;
        backkground: #eee;
        background: url("../../assets/logo.png") no-repeat;
        background-size: 90%;
        background-position: center;
        border-radius: 12px;
        box-shadow: 0 0 5px rgba(0,0,0,.3);
        transition: all ease .1s;
    }

    .card-big-strength {
        position: absolute;
        top: 15px;
        left: 15px;
        font-weight: 800;
        text-align: center;
        width: 80px;
        height: 80px;
        border-radius: 50%;
        border: 4px solid #222;
        color: #222;
        padding: 8px 0;
        font-size: 36px;
        background: rgba(255,255,255,0.8);
    }

    .card-big-name {
        position: absolute;
        bottom: 0;
        height: 44px;
        width: 100%;
        border-radius: 4px;
        color: #fff;
        padding: 3px 10px;
        background: rgba(0,0,0,0.7);
        font-size: 24px;
    }

    .game-card-big.hasimg {
        background-size: contain;
        background-position: top center;
        background-color: #000;
    }
</style>