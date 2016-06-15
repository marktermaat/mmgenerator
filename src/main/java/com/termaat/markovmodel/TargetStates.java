package com.termaat.markovmodel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Keeps track of target states and probabilities.
 */
public class TargetStates {
    final Map<String, StateProbability> states = new HashMap<>();

    /**
     * Returns the states map containing target states and probabilities
     * @return The map with states and probabilities
     */
    public Map<String, StateProbability> getStates() {
        return states;
    }

    /**
     * Adds a transition to the given state.
     * This will increase the counter of transitions to that state and recalculate all probabilities.
     * @param targetState The name of the target state.
     */
    public void addTransition(final String targetState ) {
        final StateProbability state = states.getOrDefault(targetState, new StateProbability());
        state.addTransition();
        states.put(targetState, state);
        calculateProbabilities();
    }

    /**
     * Calculates the state probabilities of all target states given the total number of transitions leaving this state.
     */
    private void calculateProbabilities() {
        final int totalTransitions = states.values().stream().collect(Collectors.summingInt(s -> s.getNumberOfTransitions()));
        states.forEach((state, probability) -> probability.calculateProbability(totalTransitions));
    }

    /**
     * Returns a string representation of these probabilities.
     *
     * @param columns A list of columns to order the states by.
     * @return A string of all probabilities of the given states.
     */
    public String toString( final List<String> columns ) {
        return columns.stream()
                .map((column) -> String.format("%.2f", states.getOrDefault(column, new StateProbability()).getTransitionProbability()))
                .collect(Collectors.joining("; "));
    }
}
