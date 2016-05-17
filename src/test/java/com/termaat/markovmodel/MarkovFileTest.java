package com.termaat.markovmodel;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MarkovFileTest {
    private List<List<String>> states;

    @Test
    public void readMarkovFileCallsTheGivenFunctionWithLines() {
        states = new ArrayList<>();
        new MarkovFile().readMarkovFileStream(Arrays.stream(new String[] {"test1\ttest2", "test3"}), this::addCounter);
        assertEquals("test1", states.get(0).get(0));
        assertEquals("test2", states.get(0).get(1));
        assertEquals("test3", states.get(1).get(0));
    }

    public void addCounter(final List<String> list) {
        states.add(list);
    }
}