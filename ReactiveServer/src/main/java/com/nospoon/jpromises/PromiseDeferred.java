package com.nospoon.jpromises;


import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

class PromiseDeferred<T> implements Promise<T>, Deferred<T> {


    enum Completed {
        NONE, RESOLVED, REJECTED
    }

    private Map<String, String> context;
    private Map<String, String> oldcontext;
    private       Completed completed         = Completed.NONE;
    private final Queue     resolvedCallbacks = new LinkedList<>();
    private final Queue     rejectedCallbacks = new LinkedList<>();
    private T         resolvedValue;
    private Throwable rejectedException;

    public PromiseDeferred() {
        this(Collections.EMPTY_MAP);
    }

    private PromiseDeferred(Completed initialState, Throwable rejectedException) {
        this();
        this.rejectedException = rejectedException;
        completed = initialState;
    }

    public static <T> PromiseDeferred<T> createRejected(Throwable e) {
        return new PromiseDeferred(Completed.REJECTED, e);
    }

    private PromiseDeferred(Map<String, String> context) {
        this.context = context;
    }

    @Override
    public Promise<T> promise() {
        return this;
    }

    @Override
    public void resolve(T resolvedValue) {
        if (completed != Completed.NONE) {
            return;
        }
        this.completed = Completed.RESOLVED;
        this.resolvedValue = resolvedValue;
        tryToExecute();
    }

    @Override
    public void reject(Throwable rejectedException) {
        this.completed = Completed.REJECTED;
        this.rejectedException = rejectedException;
        tryToExecute();
    }

    @Override
    public Promise<T> then(final Callback<T> callback) {
        resolvedCallbacks.add(callback);
        tryToExecute();
        return this;
    }

    @Override
    public Promise<T> fail(Callback<Throwable> callback) {
        rejectedCallbacks.add(callback);
        tryToExecute();
        return this;
    }

    @Override
    public Promise<T> failMap(FailableFunction<Throwable, T> callback) {

        final Deferred<T> deferred = new PromiseDeferred<T>(context);

        resolvedCallbacks.add(new Callback<T>() {

            @Override
            public void execute(T data) {
                try {
                    deferred.resolve(data);
                } catch (Throwable e) {
                    deferred.reject(e);
                }
            }
        });

        rejectedCallbacks.add(new Callback<Throwable>() {

            @Override
            public void execute(Throwable e) {
                try {
                    deferred.resolve(callback.apply(e));
                } catch (Throwable t) {
                    deferred.reject(e);
                }
            }
        });

        tryToExecute();

        return deferred.promise();
    }

    @Override
    public <RT> Promise<RT> thenMap(final FailableFunction<T, RT> callback) {

        final Deferred<RT> deferred = new PromiseDeferred<RT>(context);

        resolvedCallbacks.add(new Callback<T>() {

            @Override
            public void execute(T data) {
                try {
                    deferred.resolve(callback.apply(data));
                } catch (Throwable e) {
                    deferred.reject(e);
                }
            }
        });

        rejectedCallbacks.add(new Callback<Throwable>() {

            @Override
            public void execute(Throwable e) {
                deferred.reject(e);
            }
        });

        tryToExecute();

        return deferred.promise();
    }

    @Override
    public <RT> Promise<RT> pipe(final FailableFunction<T, Promise<RT>> callback) {

        final Deferred<RT> deferred = new PromiseDeferred<RT>(context);

        resolvedCallbacks.add(new Callback<T>() {

            @Override
            public void execute(T data) {
                Promise<RT> promise;
                try {
                    promise = callback.apply(data);
                    promise.then(new Callback<RT>() {

                        @Override
                        public void execute(RT data) {
                            deferred.resolve(data);
                        }
                    });
                    promise.fail(new Callback<Throwable>() {

                        @Override
                        public void execute(Throwable e) {
                            deferred.reject(e);
                        }
                    });
                } catch (Throwable e1) {
                    deferred.reject(e1);
                }
            }
        });

        rejectedCallbacks.add(new Callback<Throwable>() {

            @Override
            public void execute(Throwable e) {
                deferred.reject(e);
            }
        });

        tryToExecute();

        return deferred.promise();
    }

    private void tryToExecute() {
        try {
            switch (this.completed) {
                case RESOLVED:
                    while (!resolvedCallbacks.isEmpty()) {
                        Object callback = resolvedCallbacks.poll();
                        if (callback instanceof Callback) {
                            try {
                                ((Callback<T>) callback).execute(resolvedValue);
                            } catch (Throwable e) {
                                reject(e);
                            }
                        } else {
                            throw new RuntimeException("Resolved no yet implemented for callback type: " + callback
                                    .getClass()
                                                                                                                   .getName());
                        }
                    }
                    break;
                case REJECTED:
                    if (rejectedCallbacks.isEmpty()) {
                        System.err.println("Non handled exception"+ rejectedException.toString());
                    }
                    while (!rejectedCallbacks.isEmpty()) {
                        Object callback = rejectedCallbacks.poll();
                        if (callback instanceof Callback) {
                            try {
                                ((Callback<Throwable>) callback).execute(rejectedException);
                            } catch (Throwable e) {
                                rejectedException = e;
                            }
                        } else {
                            throw new RuntimeException("Rejected no yet implemented for callback type: " + callback
                                    .getClass()
                                                                                                                   .getName());
                        }
                    }
                    break;
            }
        } finally {
            if (Promises.TRACE) {

            }
        }
    }


    @Override
    public String toString() {
        return "PromiseDeferred@" + hashCode() + "[context=" + context + ", oldcontext=" + oldcontext + ", " +
               "completed=" + completed + "]";
    }

    @Override
    public boolean isSolved() {
        return this.completed == Completed.RESOLVED;
    }
}
