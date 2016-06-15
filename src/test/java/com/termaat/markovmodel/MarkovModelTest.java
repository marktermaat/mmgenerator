package com.termaat.markovmodel;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MarkovModelTest {
    @Test
    public void readLineAddsCorrectStates() {
        final MarkovModel model = new MarkovModel(1);
        model.processStateSequence(Arrays.asList(new String[] {"A", "B"}));
        final Map<String, TargetStates> modelMap = model.getModel();

        assertEquals(modelMap.size(), 3);
    }

    @Test
    public void readLineSkipEmptyLines() {
        final MarkovModel model = new MarkovModel(1);
        model.processStateSequence(new ArrayList<String>());
        final Map<String, TargetStates> modelMap = model.getModel();

        assertEquals(modelMap.size(), 0);
    }

    @Test
    public void addModelTransitionAddsWordToQueue() {
        final MarkovModel model = new MarkovModel(1);
        final LimitedQueue<String> queue = new LimitedQueue<>(1);
        model.addModelTransition("Test", queue);
        assertEquals(queue.size(), 1);
    }

    @Test
    public void addModelTransitionDoesNotAddToModelWhenQueueIsEmpty() {
        final MarkovModel model = new MarkovModel(1);
        final LimitedQueue<String> queue = new LimitedQueue<>(1);
        model.addModelTransition("Test", queue);
        assertEquals(model.getModel().size(), 0);
    }

    @Test
    public void addMOdelTransitionAddsToModelIfQueueIsNotEmpty() {
        final MarkovModel model = new MarkovModel(1);
        final LimitedQueue<String> queue = new LimitedQueue<>(1);
        model.addModelTransition("Test", queue);
        model.addModelTransition("Test2", queue);

        final Map<String, TargetStates> modelMap = model.getModel();
        assertEquals(modelMap.size(), 1);
        assertTrue(modelMap.containsKey("Test"));
        assertTrue(modelMap.get("Test").getStates().containsKey("Test2"));
    }

    @Test
    public void testModel1() {
        final MarkovModel model = new MarkovModel(1);
        model.processStateSequence(Arrays.asList(new String[] {"A", "A", "B"}));
        model.processStateSequence(Arrays.asList(new String[] {"A", "B", "A", "A"}));

        String expected = "";
        expected += "Previous state; A; B; _end_\n";
        expected += "_start_; 1.00; 0.00; 0.00\n";
        expected += "A; 0.40; 0.40; 0.20\n";
        expected += "B; 0.50; 0.00; 0.50";
        assertEquals(expected, model.toString());
    }

    @Test
    public void testModel2() {
        final MarkovModel model = new MarkovModel(2);
        model.processStateSequence(Arrays.asList(new String[] {"A", "A", "B"}));
        model.processStateSequence(Arrays.asList(new String[] {"A", "B", "A", "A"}));

        String expected = "";
        expected += "Previous state; A; B; _end_\n";
        expected += "_start_; 1.00; 0.00; 0.00\n";
        expected += "_start_-A; 0.50; 0.50; 0.00\n";
        expected += "A-A; 0.00; 0.50; 0.50\n";
        expected += "A-B; 0.50; 0.00; 0.50\n";
        expected += "B-A; 1.00; 0.00; 0.00";
        assertEquals(expected, model.toString());
    }
}