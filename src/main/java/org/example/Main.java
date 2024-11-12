package org.example;

import org.apache.commons.cli.*;
import org.apache.log4j.Logger;
import org.example.array.MissingElement;
import org.example.array.Rotate;
import org.example.array.Unpaired;
import org.example.binary.Gap;
import org.example.exception.InvalidArgumentException;
import org.example.fibonacci.Fib;
import org.example.optional.UserStats;

import java.io.PrintWriter;
import java.io.StringWriter;

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
        options.addOption("a", "array", false, "find missing element in an array example.");
        options.addOption("b", "binary", false, "find max binary gap example.");
        options.addOption("f", "fib", false, "print this Fibonacci sequence starting at x for y iterations.");
        options.addOption("h", "help", false, "print this help message to the output stream.");
        options.addOption("o", "optional", false, "example using optional fields.");
        options.addOption("r", "rotate", false, "rotate an array.");
        options.addOption("u", "unpair", false, "find unpaired element in list example.");
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
        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            log.error(e.getMessage());
            throw new InvalidArgumentException(e.getMessage());
        }
        if (cmd.getOptions() == null || cmd.getOptions().length == 0) {
            logUsage(options);
            return;
        }
        switch (cmd.getOptions()[0].getOpt()) {
            case "h": //help
                logUsage(options);
                break;
            case "a": //array
                MissingElement.findMissingElementInArray();
                break;
            case "f": //fib
                Fib.verifyFibonacciArgsAndExecute(cmd);
                break;
            case "r": //rotate
                Rotate.rotateAnIntArray();
                break;
            case "o": //optional
                UserStats.testUserStats();
                break;
            case "b": //binary
                Gap.findBinaryGapExample();
                break;
            case "u": //unpair
                Unpaired.findUnpairedElementInListExample();
                break;
            default:
                logUsage(options);
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

}