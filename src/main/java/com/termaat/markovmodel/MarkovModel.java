package com.termaat.markovmodel;

import java.util.*;
import java.util.stream.Collectors;

public class MarkovModel {
    private final static String START_TOKEN = "_start_";
    private final static String END_TOKEN = "_end_";

    private final int order;
    private Map<String, TargetStates> model;
    private Set<String> states;

    /**
     * Creates a new MarkovModel of the given order
     * @param order The order of the Markov model
     */
    public MarkovModel( final int order ) {
        this.order = order;
        model = new HashMap<>();
        states = new HashSet<>();
    }

    /**
     * Returns the markov model map, containing history states (of order N) and target states.
     * @return The Markov model map
     */
    public Map<String, TargetStates> getModel() {
        return model;
    }

    /**
     * Processes a sequence of states.
     *
     * @param states A list of states.
     */
    public void processStateSequence(final List<String> states ) {
        if( states.isEmpty() ) return;

        final LimitedQueue stateQueue = new LimitedQueue(order);

        addModelTransition( START_TOKEN, stateQueue );
        states.forEach((state) -> addModelTransition(state, stateQueue));
        addModelTransition( END_TOKEN, stateQueue );
    }

    /**
     * Creates a string representation of the markov model matrix
     *
     * @return The matrix of the markov model as a string
     */
    public String toString() {
        final List<String> columns = getColumns();
        String string = "Previous state; " + columns.stream().collect(Collectors.joining("; ")) + "\n";

        string += model.entrySet().stream()
                .map((entry) -> entry.getKey() + "; " + entry.getValue().toString(columns))
                .collect(Collectors.joining("\n"));
        return string;
    }

    /**
     * Returns a list of all states that should be presented in the matrix columns
     *
     * @return
     */
    private List<String> getColumns() {
        final List<String> columns = new ArrayList<>();
        final List<String> orderedStates = new ArrayList<String>(states);
        orderedStates.sort(Comparator.naturalOrder());
        columns.addAll(orderedStates);
        columns.add(END_TOKEN);
        return columns;
    }

    /**
     * Adds a transition to the markov model from the given history and the target state
     *
     * @param targetState The target state
     * @param historyQueue The history queue so far
     */
    public void addModelTransition( final String targetState, final LimitedQueue historyQueue ) {
        if( targetState != START_TOKEN && targetState != END_TOKEN ) states.add(targetState);
        if( historyQueue.size() > 0 ) {
            final String history = historyQueue.toString();
            final TargetStates targetStates = model.getOrDefault(history, new TargetStates());
            targetStates.addTransition(targetState);
            model.put(history, targetStates);
        }
        historyQueue.add(targetState);
    }
}
