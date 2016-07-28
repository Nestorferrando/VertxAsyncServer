package com.nospoon.jpromises;

/**
 * Functional interface for executing actions given a value
 *
 * @param <T> The value type
 * @author miguel.ballesteros
 */
public interface Callback<T> {

    /**
     * Executes the action for the given value
     *
     * @param data
     * @throws Exception
     */
    void execute(T data) throws Exception;
}