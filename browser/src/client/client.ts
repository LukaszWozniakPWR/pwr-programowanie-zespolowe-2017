import ResponseListener from "./responselistener";

export default class Client extends WebSocket {
    private responseListeners;
    private serverUrl: string;

    constructor(hostname: string, port: number) {
        const url = `ws://${hostname}:${port}/ws`;
        super(url);
        this.serverUrl = url;
        this.responseListeners = {};
        this.onmessage = (ev) => {
            if (DEBUG) {
                console.debug(ev);
            }

            let response = JSON.parse(ev.data);

            if (!this.responseListeners[response.type]) {
                this.responseListeners[response.type] = new Set();
            }

            let listeners = this.responseListeners[response.type];
            let toRemove = [];
            for (let l of listeners) {
                l.call(response[response.type]);
                if (l.single) {
                    toRemove.push(l);
                }
            }

            for (let l of toRemove) {
                listeners.delete(l);
            }
        };

        this.onclose = () => {
            let listeners = this.responseListeners.close;
            for (let l of listeners) {
                l.call();
            }
        };
    }

    // public reconnect() {
    //     if (this.readyState === 3) {
    //         this.open(this.url);
    //     }
    //
    //     setTimeout(this.reconnect, 3000);
    // }

    public addResponseListener(responseName: string, listener) {
        if (!this.responseListeners[responseName]) {
            this.responseListeners[responseName] = new Set();
        }

        this.responseListeners[responseName].add(listener);
    }

    public sendCommand(data: object) {
        if (DEBUG) {
            console.debug(data);
        }
        super.send(JSON.stringify(data));
    }

    public setNickname(nickname: string) {
        return new Promise(((resolve, reject) => {
            this.addResponseListener("SetNicknameResponse", new ResponseListener((response) => {
                if (response.success) {
                    resolve();
                } else {
                    reject();
                }
            }, true));

            this.sendCommand({
                command: "set_nickname",
                args: {
                    nickname,
                },
            });
        }));
    }

    public getPlayerList() {
        return new Promise(((resolve, reject) => {
            this.addResponseListener("PlayerList", new ResponseListener((response) => {
                if (response.players) {
                    resolve(response.players);
                } else {
                    reject();
                }
            }, true));

            this.sendCommand({
                command: "get_players",
                args: {
                },
            });
        }));
    }

    public requestGame(nickname: string) {
        return new Promise(((resolve, reject) => {
            this.addResponseListener("RequestGameResponse", new ResponseListener((response) => {
                if (response.success) {
                    resolve(response);
                } else {
                    reject();
                }
            }, true));

            this.sendCommand({
                command: "request_game",
                args: {
                    nickname,
                },
            });
        }));
    }

    public rejectGameRequest(nickname: string) {
        this.sendCommand({
            command: "reject_request_game",
            args: {
                nickname,
            },
        });
    }

    public putCard(index, row) {
        return new Promise(((resolve, reject) => {
            this.addResponseListener("PutCardResponse", new ResponseListener((response) => {
                console.log(response);
                if (response.success) {
                    resolve(response);
                } else {
                    reject();
                }
            }, true));

            this.sendCommand({
                command: "put_card",
                args: {
                    cardNumber: index,
                    row,
                },
            });
        }));
    }

    public pass() {
        return new Promise(((resolve, reject) => {
            this.addResponseListener("PassResponse", new ResponseListener((response) => {
                if (response.success) {
                    resolve(response);
                } else {
                    reject();
                }
            }, true));

            this.sendCommand({
                command: "pass",
                args: {},
            });
        }));
    }
}
