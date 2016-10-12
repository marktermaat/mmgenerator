# mmgenerator
A Markov Model generator

Copy the jar file to a directory.
Usage: `java -jar markovmodel.jar <inputfile> <outputfile> <order>`
Extra options:
`-output-state-counts <filename>` outputs all states and their number of visits
`-output-transition-counts <filename>` outputs the model with all transitions counts instead of probabilities

Order is the order of the markov model, use '1' for a simple first-order model.

The inputfile should contain sequences of states. One sequence per line, and states should be separated by tabs.