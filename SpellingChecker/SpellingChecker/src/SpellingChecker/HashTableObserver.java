package SpellingChecker;

import java.util.ArrayList;

/**
 * Created by Oliver Poole(12022846) on 20/10/15.
 *
 * Observes the Hash table to find insights on average chain length, number of successful lookup, etc
 */
public class HashTableObserver {

    public static long elementFound;
    public static long elementNotFound;

    // Linked List
    public static int averageChainLength;

    // Linear Probing
    public static int averageNodesChecked;

    public static ArrayList<Integer> averageFailedLookup = new ArrayList<>();
    public static ArrayList<Integer> averageSuccessfulLookup = new ArrayList<>();




}
