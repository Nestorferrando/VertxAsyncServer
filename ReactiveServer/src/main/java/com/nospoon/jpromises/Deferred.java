package com.nospoon.jpromises;

/**
 * Deferred object for building, resolving, and rejecting promises
 *
 * @param <T>
 * @author miguel.ballesteros
 */
public interface Deferred<T> {

    /**
     * Returns a {@link Promise} of future {@link T}
     *
     * @return
     */
    Promise<T> promise();

    boolean isSolved();

    /**
     * Resolves the attached promise
     *
     * @param resolved The promised value
     */
    void resolve(T resolved);

    /**
     * Rejects the attached promise
     *
     * @param e The exception
     */
    void reject(Throwable e);
}