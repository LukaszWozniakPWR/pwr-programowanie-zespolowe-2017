import * as debounce from "debounce";
import * as PIXI from "pixi.js";
import Animation from "./animation";
import Assets from "./assets";
import Client from "./client/client";
import MessageBox from "./ui/messagebox";

class App {
    public static TARGET_WIDTH: number = 1600;
    public static TARGET_HEIGHT: number = 1000;

    private app: PIXI.Application;
    private messages: MessageBox[] = [];
    private stage: PIXI.Container;
    private client: Client;

    constructor() {
        this.app = new PIXI.Application(App.TARGET_WIDTH, App.TARGET_HEIGHT, {
                view: document.getElementById("main") as HTMLCanvasElement,
            });
        this.stage = this.app.stage;
        document.body.appendChild(this.app.view);
        window.onresize = debounce(() => this.resize(), 200);

        this.client = new Client(Config.HOSTNAME, Config.PORT);

        this.resize();
        this.start();
    }

    public createMessageBox(title: string, msg: string): MessageBox {
        let msgbox: MessageBox = new MessageBox(title, msg);
        msgbox.pivot.x = msgbox.width / 2;
        msgbox.pivot.y = msgbox.height / 2;
        msgbox.x = App.TARGET_WIDTH / 2;
        msgbox.y = App.TARGET_HEIGHT / 3;
        return msgbox;
    }

    private resize() {
        let w = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);
        let h = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);
        let scale = Math.min(w / App.TARGET_WIDTH, h / App.TARGET_HEIGHT);
        this.app.renderer.resolution = Math.max(scale, scale ** 2);

        w = App.TARGET_WIDTH * scale;
        h = App.TARGET_HEIGHT * scale;
        this.app.renderer.resize(w, h);
        this.app.stage.scale.x = scale;
        this.app.stage.scale.y = scale;
        this.app.renderer.view.width = w;
        this.app.renderer.view.height = h;
    }

    private start() {
        let bg = new PIXI.Sprite(Assets.BACKGROUND);
        let filter = new PIXI.filters.ColorMatrixFilter();
        bg.filters = [filter];
        let msgbox = this.createMessageBox("Info", "View ready");

        this.client.onopen = () => {
            msgbox.setMessage("połączono z serwerem");
        };
        this.client.onclose = () => msgbox.setMessage("rozłączono z serwerem");
        this.client.onerror = () => msgbox.setMessage("błąd");
        this.client.onmessage = (event) => msgbox.setMessage("otrzymano:\n" + event.data);

        Animation.valueAnimation((value) => filter.brightness(value),
            0, 0.5, 1000).then(() => {
            msgbox.alpha = 0;
            this.messages.push(msgbox);
            this.stage.addChild(msgbox);
            return Animation.valueAnimation((value) => {
                msgbox.alpha = value;
                msgbox.scale.x = 1.5 - 0.5 * value;
                msgbox.scale.y = 1.5 - 0.5 * value;
            }, 0, 1, 200);
        });

        this.app.stage.addChild(bg);

    }
}

if (DEBUG) {
    const app: App = new App();
    console.dir(app);
    (window as any).app = app;
}
