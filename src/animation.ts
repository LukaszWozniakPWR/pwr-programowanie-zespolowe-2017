declare type Interpolator = (v0: number, v1: number, t: number) => number;

export default class Animation {
    public static valueAnimation(callback: (value) => void, start: number,
                                 end: number, duration: number,
                                 interpolation: Interpolator = Animation.lerp) {
        let startTimestamp;

        callback(start);

        const animate = (timestamp: number) => {
            if (!startTimestamp) {
                startTimestamp = timestamp;
            }

            const dt = timestamp - startTimestamp;

            if (dt < duration) {
                requestAnimationFrame(animate);
                callback(interpolation(start, end, dt / duration));
            } else {
                callback(end);
            }
        };

        requestAnimationFrame(animate);
    }

    public static lerp(start: number, end: number, t: number): number {
        return start + t * (end - start);
    }
}
