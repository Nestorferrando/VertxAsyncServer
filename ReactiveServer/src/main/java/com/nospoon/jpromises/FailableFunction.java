package com.nospoon.jpromises;

/**
 * Functional interface for mapping values
 *
 * @param <T>  The function argument type
 * @param <TR> The function return type
 * @author miguel.ballesteros
 */
public interface FailableFunction<T, TR> {

    /**
     * Maps the value of type {@link T} to a type {@link TR}
     *
     * @param value The value to map
     * @return The mapped value
     * @throws Exception
     */
    TR apply(T value) throws Exception;
}