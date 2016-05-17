package com.termaat.markovmodel;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by marktm on 16/05/16.
 */
public class StateProbabilityTest {
    @Test
    public void testInitializesWithZeroTransitions() {
        assertEquals(new StateProbability().getNumberOfTransitions(), 0);
    }

    @Test public void testAddTransitionAddsOneTransition() {
        final StateProbability stateProb = new StateProbability();
        stateProb.addTransition();
        assertEquals(stateProb.getNumberOfTransitions(), 1);
    }

    @Test
    public void testProbabilityWithZeroTransitions() {
        final StateProbability stateProb = new StateProbability();
        stateProb.calculateProbability(10);
        assertEquals(stateProb.getTransitionProbability(), 0.0, 0.0);
    }

    @Test
    public void testProbabilityWithMoreThanZeroTransitions() {
        final StateProbability stateProb = new StateProbability();
        stateProb.addTransition();
        stateProb.calculateProbability(10);
        assertEquals(stateProb.getTransitionProbability(), 0.1, 0.0);
    }
}
