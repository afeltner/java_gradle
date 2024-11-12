package org.example.optional;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserStats {

    static Logger log = Logger.getLogger(UserStats.class.getName());
    static Long ZERO = Long.valueOf("0");

    String userID;
    // I don't know why anyone would use Optional as it is not serializable.
    Optional<Long> visitCount;

    /**
     * Constructor for UserStats object.
     *
     * @param userID     The user ID.
     * @param visitCount The number of visits this user has made.
     */
    public UserStats(String userID, Long visitCount) {
        this.setUserID(userID);
        this.setVisitCount(visitCount);
    }

    public static void testUserStats() {
        Map<String, UserStats> userVisits = new HashMap<>();
        userVisits.put("1", new UserStats("1", Long.parseLong("3")));
        userVisits.put("2", new UserStats("a", Long.parseLong("3")));
        userVisits.put("3", null);
        userVisits.put("4", new UserStats("2", Long.parseLong("3")));
        userVisits.put("5", new UserStats("4", null));
        userVisits.put("6", new UserStats("1", Long.parseLong("1")));
        Map<Long, Long> result = count(userVisits);
        printResultSet(result);
    }

    @SafeVarargs
    private static Map<Long, Long> count(Map<String, UserStats>... visits) {
        Map<Long, Long> result = new HashMap<>();
        for (Map<String, UserStats> visitMap : visits) {
            for (Map.Entry<String, UserStats> entry : visitMap.entrySet()) {
                if (entry.getValue() == null) {
                    System.out.println("entry.value is null and will be skipped");
                    continue;
                }
                String strUserID = entry.getValue().getUserID();
                long userID;
                try {
                    userID = Long.parseLong(strUserID);
                } catch (NumberFormatException e) {
                    System.out.println("userID is not a number and will be skipped: " + strUserID);
                    continue;
                }
                UserStats stats = entry.getValue();
                if (stats == null) {
                    System.out.println("stats is null and will be skipped: ");
                    continue;
                }
                Long visitLong = stats.getVisitCount();
                if (visitLong == 0) {
                    System.out.println("visitCount is 0 and will be skipped: " + strUserID);
                    continue;
                }
                result.put(userID, result.getOrDefault(userID, 0L) + visitLong);
            }
        }
        return result;
    }

    private static void printResultSet(Map<Long, Long> result) {
        int count = 1;
        for (Map.Entry<Long, Long> entry : result.entrySet()) {
            Long key = entry.getKey();
            Long value = entry.getValue();
            log.info("User Stats Entry [" + count + "] key:" + key.toString() + " value: " + value.toString());
            count++;
        }
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Long getVisitCount() {
        return visitCount.orElse(ZERO);
    }

    public void setVisitCount(Long visitCount) {
        this.visitCount = Optional.ofNullable(visitCount);
    }

}
