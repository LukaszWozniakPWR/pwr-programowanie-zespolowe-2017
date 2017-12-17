import Bootstrap from "bootstrap";
import * as debounce from "debounce";
import Vue from "vue";
import Main from "./app.vue";
import Client from "./client/client";

class App {
    public static TARGET_WIDTH: number = 1600;
    public static TARGET_HEIGHT: number = 1000;
    private client: Client;
    private mainView: HTMLElement;
    private vue: Main;

    constructor() {
        window.onresize = debounce(() => this.resize(), 200);

        this.client = new Client(Config.HOSTNAME, Config.PORT);

        this.vue = new Main({
            el: "#app",
            data: {
                client: this.client,
            },
        });
        this.mainView = document.getElementById("main");
        this.mainView.style.width = `${App.TARGET_WIDTH}px`;
        this.mainView.style.height = `${App.TARGET_HEIGHT}px`;

        this.resize();
    }

    private resize() {
        let w = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);
        let h = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);
        let scale = Math.min(w / App.TARGET_WIDTH, h / App.TARGET_HEIGHT);
        this.mainView.style.transform = `scale(${scale})`;
        this.mainView.style.marginTop = `${(scale - 1) * App.TARGET_HEIGHT / 2}px`;
    }
}

if (DEBUG) {
    const app: App = new App();
    console.dir(app);
    (window as any).app = app;
}
