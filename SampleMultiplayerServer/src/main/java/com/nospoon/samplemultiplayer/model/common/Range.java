package com.nospoon.samplemultiplayer.model.common;

/**
 * Created by Nestor on 8/16/2016.
 */
public class Range<T extends Comparable<T>> {

    private T min;
    private T max;

    public Range(T min, T max) {
        this.min = min;
        this.max = max;
    }

    public boolean isInside(T value)
    {
        return value.compareTo(min)>=0  && value.compareTo(max)<=0;
    }

    public T getMin() {
        return min;
    }

    public T getMax() {
        return max;
    }
}
