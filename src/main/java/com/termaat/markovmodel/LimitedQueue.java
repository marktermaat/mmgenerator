package com.termaat.markovmodel;

import java.util.LinkedList;
import java.util.StringJoiner;

/**
 * Creates a queue with a maximum number of elements.
 * It will remove elements from the head of the queue when adding too many elements on the tail.
 */
public class LimitedQueue<E> extends LinkedList<E> {
    private final int maxSize;

    /**
     * Creates a new LimitedQueue with a maximum size
     * @param maxSize The maximum number of elements this queue should have
     */
    public LimitedQueue( final int maxSize ) {
        super();
        this.maxSize = maxSize;
    }

    /**
     * Add a new element to the tail of the queue.
     * It will first remove enough elements from the head of the queue to accomodate the new value.
     * @param e The new element in the queue.
     * @return true if the insert succeeded.
     */
    public boolean add( E e ) {
        while( size() >= maxSize ) {
            remove();
        }
        return super.add(e);
    }

    /**
     * Creates a string-representation of the elements in the queue.
     * It will concatenate the items, separated with a -
     * @return The string representation of the queue
     */
    public String toString() {
        final StringJoiner joiner = new StringJoiner("-");
        for(E element : this) {
            joiner.add(element.toString());
        }
        return joiner.toString();
    }
}
