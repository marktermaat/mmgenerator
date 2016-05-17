package com.termaat.markovmodel;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class TargetStatesTest {
    @Test
    public void addTransition() throws Exception {
        final TargetStates states = new TargetStates();
        states.addTransition("test");
        final Map<String, StateProbability> statesMap = states.getStates();
        assertEquals(statesMap.size(), 1);
        assertEquals(statesMap.get("test").getNumberOfTransitions(), 1);
    }

    @Test
    public void calculateProbabilities() throws Exception {
        final TargetStates states = new TargetStates();
        states.addTransition("test1");
        states.addTransition("test2");
        states.addTransition("test1");
        states.addTransition("test1");

        final Map<String, StateProbability> statesMap = states.getStates();
        assertEquals(statesMap.size(), 2);
        assertEquals(statesMap.get("test1").getTransitionProbability(), 0.75, 0.0);
        assertEquals(statesMap.get("test2").getTransitionProbability(), 0.25, 0.0);
    }

}