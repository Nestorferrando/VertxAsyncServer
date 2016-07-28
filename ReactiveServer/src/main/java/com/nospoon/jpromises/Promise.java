package com.nospoon.jpromises;

/**
 * Promise of a future value
 *
 * @param <T> Future value type
 * @author miguel.ballesteros
 */
public interface Promise<T> {

    /**
     * Execute the specified {@link Action<T>} when this promise get resolved
     *
     * @param callback The {@link Callback} to be called when the promise finishes
     *                 successfully
     * @return Chainable promise
     */
    Promise<T> then(final Callback<T> callback);

    /**
     * Execute the specified {@link Action<Exception>} when this promise get
     * rejected
     *
     * @param callback The {@link Callback} to be called when the promise fails
     * @return Chainable promise
     */
    Promise<T> fail(final Callback<Throwable> callback);

    /**
     * Execute the specified {@link FailableFunction<Exception>} when this promise get
     * rejected. Returned value
     *
     * @param callback
     * @return
     */
    Promise<T> failMap(final FailableFunction<Throwable, T> callback);

    /**
     * Execute the specified mapping {@link Function<T, RT>} when this promise
     * get resolved, returning a promise of mapped value
     *
     * @param callback
     * @return The {@link Function<T, TR>} to be called to map the response
     */
    <TR> Promise<TR> thenMap(final FailableFunction<T, TR> callback);

    /**
     * Pipes to the specified {@link Promise<TR>} when this promise get resolved
     *
     * @param callback
     * @return The {@link Function<T, Promise
     * <TR>>} that returns a promise to get resolved when this promises
     * is resolved
     */
    <TR> Promise<TR> pipe(final FailableFunction<T, Promise<TR>> callback);
}