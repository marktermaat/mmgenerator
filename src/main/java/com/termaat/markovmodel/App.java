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
     */
    public App(final String inputFile, final String outputFile, final int order ) {
        final MarkovModel model = new MarkovModel( order );

        new MarkovFile().readMarkovFile(inputFile, model::processStateSequence);

        try {
            Files.write(Paths.get(outputFile), model.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main( String[] args ) {
        if( args.length != 3 ) {
            System.err.println("Usage: java -jar markovmodel.jar <inputfile.txt> <outputfile.csv> <order>");
            System.exit(1);
        }

        new App(args[0], args[1], Integer.parseInt(args[2]));
    }
}
