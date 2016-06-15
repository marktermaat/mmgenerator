package com.termaat.markovmodel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Utility class to read the input file
 */
public class MarkovFile {

    /**
     * Reads the input file and gives each line in the file to the given lineFunction
     * @param inputFile The input file
     * @param lineFunction The function to send each line to
     */
    public void readMarkovFile( final String inputFile, final Consumer<List<String>> lineFunction ) {
        try (Stream<String> stream = Files.lines(Paths.get(inputFile))) {
            readMarkovFileStream(stream, lineFunction);
        } catch( IOException e ) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the file as a stream and processes each line
     * @param stream The file stream
     * @param lineFunction The function to send each line to
     */
    protected void readMarkovFileStream( Stream<String> stream, final Consumer<List<String>> lineFunction ) {
        stream.forEach((line) -> readLine(line, lineFunction));
    }

    /**
     * Processes the given line and sends it to the given function
     * Replaces groups of 1 or more spaces with tabs
     * @param line The line to process
     * @param lineFunction THe line function
     */
    protected void readLine(final String line, final Consumer<List<String>> lineFunction) {
        final String tabbedLine = line.replaceAll(" +", "\t");
        lineFunction.accept(Arrays.asList(tabbedLine.split("\\t")));
    }
}
