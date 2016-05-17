package com.termaat.markovmodel;

import org.junit.Assert;
import org.junit.Test;

public class LimitedQueueTest {
    @Test
    public void testMaxNumberOfItemsIsStored() {
        final LimitedQueue<Integer> queue = new LimitedQueue<>(2);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        Assert.assertEquals(queue.size(), 2);
        Assert.assertEquals((long) queue.get(0), 2);
        Assert.assertEquals((long) queue.get(1), 3);
    }

    @Test
    public void testReturnsStringRepresentation() {
        final LimitedQueue<String> queue = new LimitedQueue<>(2);
        queue.add("abcd");
        queue.add("1234");
        Assert.assertEquals(queue.toString(), "abcd-1234");
    }
}
