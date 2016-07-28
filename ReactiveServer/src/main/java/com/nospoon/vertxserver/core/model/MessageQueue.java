package com.nospoon.vertxserver.core.model;


import com.nospoon.jpromises.Promise;
import com.nospoon.jpromises.Promises;

import java.util.function.Function;

/**
 * Created by Nestor on 7/26/2016.
 */
public class MessageQueue<T, TR> {


    private Promise<T> currentPromise;
    private int enqueuedMessages;

    public MessageQueue() {

        currentPromise = Promises.resolve(null);
    }


    public void enqueueHandler(Function<T, Promise<T>> consumer) {
        enqueuedMessages++;
        currentPromise = currentPromise.pipe(res -> {
            enqueuedMessages--;
            return consumer.apply(null);
        });
    }

}
