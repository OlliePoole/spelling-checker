package CollisionResolution;
import SpellingChecker.HashTableObserver;
import SpellingChecker.TableNode;
import SpellingChecker.HashTable;

/**
 * Created by oliverpoole on 16/10/15.
 */

public class Chaining implements CollisionResolutionInterface {

    private static int numberOfSearches;

    public Object resolveConflictWithElement(String element, TableNode[] table, int hashValue, HashTable.CollisionResolutionMethod method) {

        TableNode currentNode = table[hashValue];

        currentNode = Chaining.resolveCollisionWithLinkedList(element, currentNode);

        return currentNode;
    }


    public Boolean elementExistsInTable(String element, TableNode[] table, int hashValue, HashTable.CollisionResolutionMethod method) {

        // reset the search count
        numberOfSearches = 1;

        return Chaining.elementExistsInTableUsingLinkedListWithElement(element, table[hashValue]);
    }


    /**
     * Resolves a conflict using a linked list
     *
     * @param element - The element to resolve
     *
     * @return The updated Table
     */
    private static TableNode resolveCollisionWithLinkedList(String element, TableNode currentNode) {

        TableNode newNode = new TableNode();
        newNode.data = element;

        if (currentNode != null) {
            newNode.next = currentNode;
        }

        return newNode;
    }


    /**
     * Searches the table based on the premise that the collision resolution strategy
     * that was used was chaining with linked lists
     *
     * @param element - The element to search for
     * @param currentNode - The node in the table where the hash function points to
     *
     * @return True, if the element exists in the table
     */
    private static Boolean elementExistsInTableUsingLinkedListWithElement(String element, TableNode currentNode) {

        while (currentNode != null) {
            if (currentNode.data.equals(element)) {
                HashTableObserver.averageSuccessfulLookup.add(numberOfSearches);
                HashTableObserver.elementFound++;
                return true;
            }
            else {

                if (currentNode.next == null) {
                    HashTableObserver.averageFailedLookup.add(numberOfSearches);
                    HashTableObserver.elementNotFound++;
                    return false;
                }

                numberOfSearches++;
                currentNode = currentNode.next;
            }
        }

        HashTableObserver.averageFailedLookup.add(numberOfSearches);
        HashTableObserver.elementNotFound++;
        return false;
    }
}
