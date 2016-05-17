package com.termaat.markovmodel;

/**
 * A StateProbability keeps track of the number of times a transition is made,
 * and calculates the probability of taking this transition given the total number of transitions.
 */
public class StateProbability {
    private int numberOfTransitions = 0;
    private double transitionProbability;

    /**
     * Add a transition for this state.
     */
    public void addTransition() {
        numberOfTransitions += 1;
    }

    /**
     * Returns the total number of transitions counted for this state.
     * @return The total number of transitions for this state.
     */
    public int getNumberOfTransitions() {
        return numberOfTransitions;
    }

    /**
     * Returns the probability to this state.
     * Has to be calculated with calculateProbability first, else will return null.
     */
    public double getTransitionProbability() {
        return transitionProbability;
    }

    /**
     * Calculates the transition probability given the total number of transitions from this state.
     * @param totalNumberOfTransitions The total number of transitions going out from this state.
     */
    public void calculateProbability( final int totalNumberOfTransitions ) {
        transitionProbability = (double) numberOfTransitions / (double) totalNumberOfTransitions;
    }
}
