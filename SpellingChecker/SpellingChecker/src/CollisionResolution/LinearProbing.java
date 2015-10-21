package CollisionResolution;
import SpellingChecker.HashTable;
import SpellingChecker.HashTableObserver;
import SpellingChecker.TableNode;

/**
 * Created by oliverpoole on 16/10/15.
 */
public class LinearProbing implements CollisionResolutionInterface {

    public Object resolveConflictWithElement(String element, TableNode[] table, int hashValue, HashTable.CollisionResolutionMethod method) {
        return LinearProbing.resolveConflictUsingLinearProbingWithElement(table, hashValue);
    }

    /**
     * Find the next available index and return it to the hash table
     *
     * @param table - The table to insert it into
     * @param hashValue - The hash value of the element
     *
     * @return The index to insert the node into
     */
    private static int resolveConflictUsingLinearProbingWithElement(TableNode[] table, int hashValue) {

        TableNode currentNode = table[hashValue];

        while (currentNode != null) {
            hashValue++;

            // If we reach the end of the table, loop round to the start
            if (hashValue == HashTable.tableSize) {
                hashValue = 0;
            }

            currentNode = table[hashValue];
        }

        return hashValue;

    }

    public Boolean elementExistsInTable(String element, TableNode[] table, int hashValue, HashTable.CollisionResolutionMethod method) {

        TableNode currentNode = table[hashValue];

        int unsuccessfulSearches = 1;

        while (currentNode != null) {

            if (currentNode.data.equals(element)) {
                HashTableObserver.averageSuccessfulLookup.add(unsuccessfulSearches);
                HashTableObserver.elementFound++;
                return true;
            }

            // Increment the hashValue
            hashValue++;

            // If we reach the end of the table, loop round to the start
            if (hashValue == HashTable.tableSize) {
                hashValue = 0;
            }

            currentNode = table[hashValue];

            unsuccessfulSearches++;
        }

        HashTableObserver.averageFailedLookup.add(unsuccessfulSearches);
        HashTableObserver.elementNotFound++;
        return false;
    }
}
