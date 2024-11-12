package org.example.array;

import org.apache.log4j.Logger;

import java.util.Arrays;

public class Rotate {

    static Logger log = Logger.getLogger(Rotate.class.getName());

    /**
     * rotateAnIntArray is the entry point to rotate an array of int.  Currently, the number of times to rotate and the
     * int array list is hard coded.
     */
    public static void rotateAnIntArray() {
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
}