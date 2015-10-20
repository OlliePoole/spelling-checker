package CollisionResolution;
import SpellingChecker.HashTable;
import SpellingChecker.HashTableObserver;
import SpellingChecker.TableNode;
import javafx.scene.control.Tab;

/**
 * Created by oliverpoole on 16/10/15.
 */
public class LinearProbing implements CollisionResolutionInterface {

    public Object resolveConflictWithElement(String element, TableNode[] table, int hashValue, HashTable.CollisionResolutionMethod method) {
        return LinearProbing.resolveConflictUsingLinearProbingWithElement(table, hashValue);
    }

    public Boolean elementExistsInTable(String element, TableNode[] table, int hashValue, HashTable.CollisionResolutionMethod method) {

        TableNode currentNode = table[hashValue];

        if (currentNode == null) {
            return false;
        }

        // If we reach the end of the table, loop round to the start
        if (hashValue == HashTable.tableSize - 1) {
            hashValue = 0;
        }

        if (currentNode.data.equals(element)) {
            return true;
        }
        else {
            HashTableObserver.elementNotFound++;
            return elementExistsInTable(element, table, ++hashValue, method);
        }
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

        // If we reach the end of the table, loop round to the start
        if (hashValue == HashTable.tableSize - 1) {
            hashValue = 0;
        }

        TableNode currentNode = table[hashValue];

        if (currentNode == null) {
            return hashValue;
        }
        else {
            return resolveConflictUsingLinearProbingWithElement(table, ++hashValue);
        }
    }
}
