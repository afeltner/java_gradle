package org.example.array;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MissingElement {

    static Logger log = Logger.getLogger(MissingElement.class.getName());

    /**
     * findMissingElementInArray given an int where it assumes the numbers are always N+1, will find the missing value.
     * For example if you have 1, 2, 4, 5, the missing value will be 3.  Numbers in the array can be in any order.
     */
    public static void findMissingElementInArray() {
        sortAndFindMissing(new int[]{2, 3, 1, 5, 7, 10, 4, 6, 8});
        sortAndFindMissing(new int[]{});
        sortAndFindMissing(null);
        sortAndFindMissing(new int[]{1, 2, 3, 4, 5, 6});
        sortAndFindMissing(new int[]{1});
        sortAndFindMissing(new int[]{1, 3});
    }

    /**
     * sortAndFindMissing given a non-empty array of int contains an odd number of elements, where each element of the
     * array can be paired with another element that has the same value, except for one element that is left unpaired.
     * This method will identify the unpaired int.
     *
     * @param unorderedArray the array of int to be evaluated.
     */
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