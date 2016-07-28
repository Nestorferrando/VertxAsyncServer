package com.nospoon.jpromises;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * Deferred factory and promise utility methods
 *
 * @author miguel.ballesteros
 */
public class Promises {

    static boolean TRACE = false;


    public static void enableTracing() {
        TRACE = true;
    }

    /**
     * Creates a new {@link Deferred} object for building {@link Promise}s
     *
     * @return The newly created {@link Deferred} object
     */
    public static <T> Deferred<T> defer() {
        return new PromiseDeferred<T>();
    }

    /**
     * Creates a resolved {@link Promise}
     *
     * @param value The promised value
     * @return The resolved {@link Promise}
     */
    public static <T> Promise<T> resolve(T value) {
        final Deferred<T> deferred = Promises.defer();
        if (value instanceof Exception) {
            deferred.reject((Exception) value);
        } else {
            deferred.resolve(value);
        }
        return deferred.promise();
    }

    /**
     * Creates a promise that will be resolved when all the given promises are
     * resolved, or that fails if any of them fails
     *
     * @param promises The array of promises to be resolved
     * @return The combined promise
     */
    public static Promise<List> all(Promise... promises) {
        if (promises.length == 0) {
            return Promises.resolve(Collections.EMPTY_LIST);
        }
        final Deferred<List> deferred     = Promises.defer();
        final Object[]       resolvedList = new Object[promises.length];

        final int[] counter = new int[]{promises.length};
        for (int i = 0; i < promises.length; i++) {
            final int pos     = i;
            Promise   promise = promises[i];
            promise.then(new Callback<Object>() {

                @Override
                public void execute(Object data) throws Exception {
                    resolvedList[pos] = data;
                    counter[0]--;
                    if (counter[0] == 0) {
                        deferred.resolve(Arrays.asList(resolvedList));
                    }
                }
            });
            promise.fail(new Callback<Exception>() {

                @Override
                public void execute(Exception e) throws Exception {
                    deferred.reject(e);
                }
            });
        }

        return deferred.promise();
    }

    private final static Promise[] PROMISES_EMPTY_ARRAY = new Promise[]{};

    public static Promise<List> all(List<Promise> promises) {
        return all(promises.toArray(PROMISES_EMPTY_ARRAY));
    }

    public static <T> Promise<T> reject(Throwable e) {
        final Deferred<T> deferred = PromiseDeferred.createRejected(e);
        return deferred.promise();
    }

    public static <A, B> AsyncContext begin(Function<A, B> f) {
        return new AsyncContext(f);
    }

}