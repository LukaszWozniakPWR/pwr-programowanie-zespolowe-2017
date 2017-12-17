export default class ResponseListener {
    public readonly single: boolean;
    private callback: any;

    constructor(callback: any, single: boolean = false) {
        this.single = single;
        this.callback = callback;
    }

    public call(response) {
        this.callback(response);
    }
}
