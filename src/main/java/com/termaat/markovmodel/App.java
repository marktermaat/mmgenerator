package com.termaat.markovmodel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The main application which can be used to convert an input file containing sequences of tab-separated states to a markov model or order N.
 */
public class App {

    /**
     * Starts the application, reads the inputfile, creates a markov model of the given order and writes the model matrix to the output file.
     *
     * @param inputFile The input file
     * @param outputFile The output file
     * @param order The markov model order
     * @param stateCountsFile The file to store state counts
     * @param transitionCountsFile The file to store transition counts
     */
    public App(final String inputFile, final String outputFile, final int order, final String stateCountsFile, final String transitionCountsFile ) {
        final MarkovModel model = new MarkovModel( order );

        new MarkovFile().readMarkovFile(inputFile, model::processStateSequence);

        try {
            Files.write(Paths.get(outputFile), model.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(stateCountsFile != null) {
            try {
                Files.write(Paths.get(stateCountsFile), model.getStateCounts().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(transitionCountsFile != null) {
            try {
                Files.write(Paths.get(transitionCountsFile), model.getTransitionCounts().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String parseParameter( final String[] args, final String parameterName ) {
        for(int i = 3; i + 1 < args.length; i += 2) {
            if(args[i].equals(parameterName)) {
                return args[i + 1];
            }
        }
        return null;
    }

    public static void main( String[] args ) {
        if( args.length < 3 ) {
            System.err.println("Usage: java -jar markovmodel.jar <inputfile.txt> <outputfile.csv> <order>");
            System.exit(1);
        }

        final String stateCountsFile = parseParameter(args, "-output-state-counts");
        final String transitionCountsFile = parseParameter(args, "-output-transition-counts");

        new App(args[0], args[1], Integer.parseInt(args[2]), stateCountsFile, transitionCountsFile);
    }
}
