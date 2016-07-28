package com.nospoon.jpromises;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class PromisesQueue<T> {

    private final String                  name;
    private final int                     parallelization;
    private final int                     queueSize;
    private final Consumer<Void>          onMaxConcurrency;
    private final Function<T, Promise<T>> queueConsumer;

    private       int                   replyPendingCounter;
    private final List<DeferredItem<T>> deferredQueue;

    public PromisesQueue(String name,
                         int parallelization,
                         int queueSize,
                         Consumer<Void> onMaxConcurrency,
                         Function<T, Promise<T>> queueConsumer) {
        this.name = name;
        this.parallelization = parallelization;
        this.queueSize = queueSize;
        this.onMaxConcurrency = onMaxConcurrency;
        this.queueConsumer = queueConsumer;
        this.deferredQueue = new LinkedList<>();
    }

    public Promise<T> enqueue(T item) {
        Deferred<T> deferred = Promises.defer();

        // If there's still space in the queue... enqueue
        if (deferredQueue.size() < queueSize) {
            enqueueMessage(item, deferred);
            if (onMaxConcurrency != null && deferredQueue.size() > parallelization) {
                onMaxConcurrency.accept(null);
            }
            next();
        }

        // Else fail promise with UnavailableServiceException
        else {
            deferred.reject(new PromisesQueueOverflowException(name + " is full (" + queueSize + "). Wait a while!"));
        }
        return deferred.promise();
    }

    private void enqueueMessage(T message, Deferred<T> deferred) {
        deferredQueue.add(new DeferredItem<T>(message, deferred));
    }

    private void next() {
        if (!deferredQueue.isEmpty() && replyPendingCounter < parallelization) {
            DeferredItem<T> deferredItem = deferredQueue.remove(0);
            replyPendingCounter++;
            if (replyPendingCounter >= parallelization) {
                onMaxConcurrency.accept(null);
            }

            queueConsumer.apply(deferredItem.item).then(res -> {
                replyPendingCounter--;
                deferredItem.deferred.resolve(res);
                // Next one! This tail recursive call will stop when empty
                next();
            }).fail(t -> {
                replyPendingCounter--;
                deferredItem.deferred.reject(t);
                // Next one! This tail recursive call will stop when empty
                next();
            });
        }
    }

    private static class DeferredItem<T> {
        final T           item;
        final Deferred<T> deferred;

        private DeferredItem(T item, Deferred<T> deferred) {
            this.item = item;
            this.deferred = deferred;
        }
    }
}