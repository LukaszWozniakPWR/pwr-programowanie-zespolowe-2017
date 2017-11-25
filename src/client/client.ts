export default class Client extends WebSocket {
    constructor(hostname: string, port: number) {
        const url = `ws://${hostname}:${port}/ws`;
        super(url);
    }
}
