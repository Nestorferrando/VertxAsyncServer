package com.nospoon.jpromises;


public class AsyncSemaphore {

    private boolean        paused;
    private Deferred<Void> ready;

    public void pause() {
        this.paused = true;
        this.ready = Promises.defer();
    }

    public void resume() {
        this.paused = false;
        this.ready.resolve(null);
    }

    public Promise<Void> ready() {
        return paused ? ready.promise() : Promises.resolve(null);
    }
}
