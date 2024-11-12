package org.example;

import org.apache.commons.cli.*;
import org.apache.log4j.Logger;
import org.example.exception.InvalidArgumentException;
import org.example.optional.UserStats;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

public class Main {

    static Logger log = Logger.getLogger(Main.class.getName());

    /**
     * main is the entry point to this service.  It will define the acceptable command line options and validate those
     * against what was passed in.  If there is an error, a message will be displayed.  If no error, the service will
     * behave based on those parameters.
     *
     * @param args Command line arguments used to control behavior.
     * @throws InvalidArgumentException Exception thrown when a passed argument is invalid.
     */
    public static void main(String[] args) throws InvalidArgumentException {
        Options options = new Options();
        options.addOption("f", "fib", false, "print this Fibonacci sequence starting at x.");
        options.addOption("h", "help", false, "print this help message to the output stream.");
        options.addOption("o", "optional", false, "example using optional fields.");
        options.addOption("r", "rotate", false, "rotate an array.");
        verifyInputArguments(options, args);
    }

    /**
     * verifyInputArguments will validate the arguments passed with the valid options available.
     *
     * @param options Valid options available for this service.
     * @param args    Arguments passed into his service.
     * @throws InvalidArgumentException Exception thrown when a passed argument is invalid.
     */
    private static void verifyInputArguments(Options options, String[] args) throws InvalidArgumentException {
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("help")) {
                logUsage(options);
            } else if (cmd.hasOption("fib")) {
                verifyFibonacciArgsAndExecute(cmd);
            } else if (cmd.hasOption("rotate")) {
                rotateAnIntArray();
            } else if (cmd.hasOption("optional")) {
                UserStats.testUserStats();
            }
        } catch (ParseException e) {
            log.error(e.getMessage());
            throw new InvalidArgumentException(e.getMessage());
        }
    }

    /**
     * logUsage will log the usage to Standard Out.  The only logging set up in the log4j.properties file.
     *
     * @param options Valid options available for this service.
     */
    private static void logUsage(Options options) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        HelpFormatter f = new HelpFormatter();
        f.printHelp(pw, f.getWidth(), "Main", null, options,
                f.getLeftPadding(), f.getDescPadding(), null, true);
        pw.flush();
        log.info("\n" + sw);
    }

    /**
     * verifyFibonacciArgsAndExecute will first verify that the number of arguments is equal to 2, and it will parse
     * the strings to insure hey are numbers.  Once it passes validation, it will print out the Fibonacci sequence.
     *
     * @param cmd Values parsed from the command line.
     * @throws InvalidArgumentException Exception thrown when a passed argument is invalid
     */
    private static void verifyFibonacciArgsAndExecute(CommandLine cmd) throws InvalidArgumentException {
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

    /**
     * rotateAnIntArray is the entry point to rotate an array of int.  Currently, the number of times to rotate and the
     * int array list is hard coded.
     */
    private static void rotateAnIntArray() {
        sortAndFindMissing(new int[]{2, 3, 1, 5, 7, 10, 4, 6, 8});
        sortAndFindMissing(new int[]{});
        sortAndFindMissing(null);
        sortAndFindMissing(new int[]{1, 2, 3, 4, 5, 6});
        sortAndFindMissing(new int[]{1});
        sortAndFindMissing(new int[]{1, 3});


        int numberToEvaluate = 8888;
        findBinaryGap(numberToEvaluate);

        int[] emptyUnpairedList = new int[]{100, 3};
        findUnpairedElements(emptyUnpairedList);
        findUnpairedElements(null);
        int[] unpairedList = {9, 3, 9, 3, 9, 7};
        findUnpairedElements(unpairedList);
        emptyUnpairedList = new int[]{};
        findUnpairedElements(emptyUnpairedList);

        int timesToRotate = 2;
        int[] intList = {1, 2, 3, 4, 5};
        rotateIntArrayLastToFirst(intList, timesToRotate);
        rotateIntArrayFirstToLast(intList, timesToRotate);
    }

    /**
     * rotateIntArrayLastToFirst given an array will rotate x times, moving the last element to the first element
     * of the list.
     *
     * @param arrayToRotate the list of int to be rotated
     * @param timesToRotate the number of times to rotate the list
     */
    private static void rotateIntArrayLastToFirst(final int[] arrayToRotate, int timesToRotate) {
        if (arrayToRotate == null || arrayToRotate.length == 0) {
            log.error("Last To First Rotation: Array contained no data.");
            return;
        }
        int lengthOfList = arrayToRotate.length;
        int[] rotatedArray = Arrays.copyOf(arrayToRotate, lengthOfList);
        int pass = 1;
        while (pass <= timesToRotate) {
            int[] intList = new int[lengthOfList];
            int last = rotatedArray[lengthOfList - 1];
            intList[0] = last;
            System.arraycopy(rotatedArray, 0, intList, 1, lengthOfList - 1);
            rotatedArray = Arrays.copyOf(intList, intList.length);
            pass++;
        }
        log.info("Last To First Rotation: " + Arrays.toString(rotatedArray));
    }

    /**
     * rotateIntArrayLastToFirst given an array will rotate x times, moving the first element to the last element
     * of the list.
     *
     * @param arrayToRotate the list of int to be rotated
     * @param timesToRotate the number of times to rotate the list
     */
    private static void rotateIntArrayFirstToLast(final int[] arrayToRotate, int timesToRotate) {
        int lengthOfList = arrayToRotate.length;
        int[] rotatedArray = Arrays.copyOf(arrayToRotate, lengthOfList);

        int pass = 1;
        while (pass <= timesToRotate) {
            int last = rotatedArray[0];
            for (int i = 0; i < lengthOfList - 1; i++) {
                rotatedArray[i] = rotatedArray[i + 1];
            }
            rotatedArray[lengthOfList - 1] = last;
            pass++;
        }
        log.info("First To Last Rotation: " + Arrays.toString(rotatedArray));
    }

    /**
     * findUnpairedElements will evaluate an array of int, and identify which numbers only occur one time.
     *
     * @param arrayToCheck contains a list of int to be evaluated
     */
    private static void findUnpairedElements(int[] arrayToCheck) {
        if (arrayToCheck == null || arrayToCheck.length == 0) {
            log.error("Unpaired Elements has empty or null array.");
            return;
        }
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int item : arrayToCheck) {
            if (!hashMap.containsKey(item)) {
                hashMap.put(item, 1);
            } else {
                Integer numberOccurrences = hashMap.get(item);
                numberOccurrences++;
                hashMap.replace(item, numberOccurrences);
            }
        }
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue() == 1) {
                log.info("Unpaired Elements for: " + Arrays.toString(arrayToCheck) + " was " + entry.getKey());
            }
        }
    }

    private static void findBinaryGap(int numberToEvaluate) {
        String binary = Integer.toBinaryString(numberToEvaluate);
        int gap = 0;
        int maxGap = 0;
        boolean startCounting = false;
        for (int i = 0; i < binary.length(); i++) {
            char character = binary.charAt(i);
            if ("1".equals(String.valueOf(character))) {
                if (startCounting) {
                    maxGap = Math.max(gap, maxGap);
                    gap = 0;
                } else {
                    startCounting = true;
                }
            } else {
                // Only count after the first 1 is found
                if (startCounting) {
                    gap++;
                }
            }
        }
        log.info("Max Gap of: " + binary + " is: " + maxGap);
    }

    private static void sortAndFindMissing(int[] unorderedArray) {
        int missingNumber = 1;
        if (unorderedArray == null || unorderedArray.length == 0) {
            log.info("Missing number from: " + Arrays.toString(unorderedArray) + " is: " + missingNumber);
            return;
        }
        ArrayList<Integer> unorderedList = new ArrayList<>(unorderedArray.length);
        for (int i : unorderedArray) {
            unorderedList.add(i);
        }
        Collections.sort(unorderedList);
        int firstNumber = unorderedList.getFirst();
        int expectedNextNumber = firstNumber + 1;
        for (int i = 1; i <= unorderedList.size() - 1; i++) {
            int nextNumber = unorderedList.get(i);
            if (nextNumber > expectedNextNumber) {
                log.info("Missing number from: " + Arrays.toString(unorderedArray) + " is: " + expectedNextNumber);
                return;
            } else {
                expectedNextNumber++;
            }
        }
        log.info("Missing number from: " + Arrays.toString(unorderedArray) + " is: " + expectedNextNumber);
    }
}