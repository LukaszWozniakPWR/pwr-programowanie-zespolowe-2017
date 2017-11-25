declare type Interpolator = (v0: number, v1: number, t: number) => number;

export default class Animation {
    public static valueAnimation(callback: (value) => void, start: number,
                                 end: number, duration: number,
                                 interpolation: Interpolator = Animation.lerp) {

        let startTimestamp;
        return new Animation(((timestamp, next, resolve) => {
            if (!startTimestamp) {
                startTimestamp = timestamp;
            }

            let dt = timestamp - startTimestamp;

            if (dt < duration) {
                next();
                callback(interpolation(start, end, dt / duration));
            } else {
                callback(end);
                resolve();
            }
        }));
    }

    public static lerp(start: number, end: number, t: number): number {
        return start + t * (end - start);
    }

    private promise: Promise<void>;

    constructor(animation: (timestamp: number, next: () => void,
                            end: () => void) => void) {
        this.promise = new Promise((resolve, reject) => {
            let next = () => requestAnimationFrame((timestamp) => {
                animation(timestamp, next, () => resolve());
            });

            next();
        });
    }

    public then(callback: () => any) {
        return this.promise.then(() => callback());
    }
}
