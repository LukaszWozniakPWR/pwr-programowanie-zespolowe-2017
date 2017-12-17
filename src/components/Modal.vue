<template>
    <div class="modal fade" :id="id" tabindex="-1" role="dialog" :aria-hidden="hidden">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">{{ title }}</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    {{ body }}
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal" @click="secondaryBtnCallback()">{{ secondaryBtnText }}</button>
                    <button type="button" class="btn btn-primary" @click="primaryBtnCallback()">{{ primaryBtnText }}</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
    import {Vue, Component, Prop} from "vue-property-decorator";

    @Component
    export default class Modal extends Vue {
        @Prop() name: string = "Modal";
        @Prop() id: string;
        @Prop() title: string;
        @Prop() body: string;
        @Prop() primaryBtnText: string;
        @Prop() secondaryBtnText: string;
        @Prop() primaryBtnCallback: any;
        @Prop() secondaryBtnCallback: any;
        @Prop() hidden: boolean;
    }
</script>

<style>

</style>