package org.example.fibonacci;

import org.apache.commons.cli.CommandLine;
import org.apache.log4j.Logger;
import org.example.exception.InvalidArgumentException;

import java.util.ArrayList;
import java.util.List;

public class Fib {

    static Logger log = Logger.getLogger(Fib.class.getName());
    
    /**
     * verifyFibonacciArgsAndExecute will first verify that the number of arguments is equal to 2, and it will parse
     * the strings to insure hey are numbers.  Once it passes validation, it will print out the Fibonacci sequence.
     *
     * @param cmd Values parsed from the command line.
     * @throws InvalidArgumentException Exception thrown when a passed argument is invalid
     */
    public static void verifyFibonacciArgsAndExecute(CommandLine cmd) throws InvalidArgumentException {
        if (cmd.getArgs() != null && cmd.getArgs().length != 2) {
            String errMsg = "You must also provide a starting and end number for this option.";
            throw new InvalidArgumentException(errMsg);
        }
        int count = 0;
        long startFib = 0;
        int numIterations = 0;
        for (String s : cmd.getArgs()) {
            try {
                if (count == 0) {
                    startFib = Long.parseLong(s);
                } else {
                    numIterations = Integer.parseInt(s);
                }
                count++;
            } catch (NumberFormatException e) {
                String errMsg = "start sequence, and iteration count for -f are expected and must be numbers.";
                throw new InvalidArgumentException(errMsg);
            }
        }
        calculateAndLogFibonacciSequence(startFib, numIterations);
    }

    /**
     * calculateAndLogFibonacciSequence will calculate the Fibonacci sequence and log the numbers to the console.
     * In mathematics, the Fibonacci sequence is a sequence in which each number is the sum of the two preceding ones.
     *
     * @param startFib      Starting sequence number.
     * @param numIterations Number of iterations to process.
     */
    private static void calculateAndLogFibonacciSequence(long startFib, int numIterations) {
        List<Long> fibSequence = new ArrayList<>();
        long firstPreceding = 0;
        long secondPreceding = 1;
        long next = firstPreceding + secondPreceding;
        fibSequence.add(startFib);
        for (long i = 1; i < numIterations; ) {
            if (next > startFib) {
                fibSequence.add(next);
                i++;
            }
            firstPreceding = secondPreceding;
            secondPreceding = next;
            next = firstPreceding + secondPreceding;
        }
        log.info("Fibonacci Sequence is: " + fibSequence);
    }
}