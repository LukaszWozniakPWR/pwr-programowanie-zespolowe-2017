import * as debounce from "debounce";
import * as PIXI from "pixi.js";
import Animation from "./animation";
import Assets from "./assets";

class App {
    public static TARGET_WIDTH: number = 1600;
    public static TARGET_HEIGHT: number = 1000;

    private app: PIXI.Application;

    constructor() {
        this.app = new PIXI.Application(App.TARGET_WIDTH, App.TARGET_HEIGHT, {
                view: document.getElementById("main") as HTMLCanvasElement,
            });
        document.body.appendChild(this.app.view);
        this.app.renderer.autoResize = true;
        window.onresize = debounce(() => this.resize(), 200);
        this.resize();
        this.start();
    }

    private resize() {
        let w = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);
        let h = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);
        const scale = Math.min(w / App.TARGET_WIDTH, h / App.TARGET_HEIGHT);
        w = App.TARGET_WIDTH * scale;
        h = App.TARGET_HEIGHT * scale;
        this.app.renderer.resize(w, h);
        this.app.stage.scale.x = scale;
        this.app.stage.scale.y = scale;
    }

    private start() {
        const bg = new PIXI.Sprite(Assets.BACKGROUND);
        const filter = new PIXI.filters.ColorMatrixFilter();
        bg.filters = [filter];

        Animation.valueAnimation((value) => filter.brightness(value),
            0, 0.5, 1000);
        this.app.stage.addChild(bg);
    }
}

const app: App = new App();
console.dir(app);
