export default class MessageBox extends PIXI.Container {
    private title: string;
    private message: string;
    private titleText: PIXI.Text;
    private messageText: PIXI.Text;
    private margin: number = 10;
    private titleStyle: PIXI.TextStyle = new PIXI.TextStyle({
        fontFamily: "Arial",
        fontSize: 18,
        fontWeight: "bold",
        fill: "#fff",
        wordWrap: true,
        wordWrapWidth: this.width - this.margin * 2,
    });
    private messageStyle: PIXI.TextStyle = new PIXI.TextStyle({
        fontFamily: "Arial",
        fontSize: 12,
        fontWeight: "bold",
        fill: "#eee",
        wordWrap: true,
        wordWrapWidth: this.width - this.margin * 2,
    });

    constructor(title: string = "", message: string = "") {
        super();
        let sprite: PIXI.Sprite = new PIXI.Sprite(PIXI.Texture.WHITE);
        this.width = sprite.width = 300;
        this.height = sprite.height = 150;
        sprite.tint = 0x000000;
        this.addChild(sprite);

        this.titleText = new PIXI.Text("", this.titleStyle);
        this.titleText.x = this.margin;
        this.titleText.y = this.margin;
        this.addChild(this.titleText);

        this.messageText = new PIXI.Text("", this.messageStyle);
        this.messageText.x = this.margin;
        this.addChild(this.messageText);

        this.setData(title, message);
    }

    public setTitle(title: string) {
        this.setData(title, this.message);
    }

    public setMessage(message: string) {
        this.setData(this.title, message);
    }

    public setData(title: string, message: string) {
        this.titleText.text = title;
        this.messageText.text = message;
        this.messageText.y = this.titleText.y + this.titleText.height + this.margin;
        this.recalculateDimensions();
    }

    private recalculateDimensions() {
        this.titleStyle.wordWrapWidth = this.width - this.margin * 2;
        this.messageStyle.wordWrapWidth = this.width - this.margin * 2;
    }
}
