package org.example.array;

import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Unpaired {

    static Logger log = Logger.getLogger(Unpaired.class.getName());

    /**
     * findUnpairedElementInListExample given an int array where there is always an odd number of elements,
     * with only 1 value unpaired, this method will identify the unpaired number.
     */
    public static void findUnpairedElementInListExample() {
        int[] emptyUnpairedList = new int[]{100, 3};
        findUnpairedElements(emptyUnpairedList);
        findUnpairedElements(null);
        int[] unpairedList = {9, 3, 9, 3, 9, 7};
        findUnpairedElements(unpairedList);
        emptyUnpairedList = new int[]{};
        findUnpairedElements(emptyUnpairedList);
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

}