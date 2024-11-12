package org.example.binary;

import org.apache.log4j.Logger;

public class Gap {

    static Logger log = Logger.getLogger(Gap.class.getName());

    /**
     * findBinaryGapExample given an int, it will convert the value to binary, to identify the max gap between 1's.
     * For example 10001010111000 has a max gap of 3.
     */
    public static void findBinaryGapExample() {
        int numberToEvaluate = 8888;
        findBinaryGap(numberToEvaluate);
    }

    /**
     * findBinaryGap will find the max binary gap withing the binary representation of an int.
     * A binary gap within a positive integer is any maximal sequence of consecutive zeros that is surrounded by
     * ones at both ends in the binary representation of the int.
     *
     * @param numberToEvaluate The int to be evaluated.
     */
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
}