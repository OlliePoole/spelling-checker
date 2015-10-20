package CollisionResolution;
import SpellingChecker.TableNode;
import SpellingChecker.HashTable;

/**
 * Created by oliverpoole on 16/10/15.
 */

public class Chaining implements CollisionResolutionInterface {

     public TableNode resolveConflictWithElement(String element, TableNode[] table, TableNode currentNode, HashTable.CollisionResolutionMethod method) {

        switch (method) {
            case ChainingLinkedList: {
                currentNode = Chaining.resolveCollisionWithLinkedList(element, table, currentNode);
                break;
            }
            case ChainingHashTable: {
                currentNode = Chaining.resolveCollisionWithHashTable(element, table);
                break;
            }
        }

        return currentNode;
    }


    public Boolean elementExistsInTable(String element, TableNode[] table, int hashValue, HashTable.CollisionResolutionMethod method) {

        switch (method) {
            case ChainingLinkedList: {
                return Chaining.elementExistsInTableUsingLinkedListWithElement(element, table[hashValue]);
            }
            case ChainingHashTable: {
                return Chaining.elementExistsInTableUsingHashTableWithElement(element, hashValue, table);
            }
            default: return false;
        }
    }


    /**
     * Resolves a conflict using a linked list
     *
     * @param element - The element to resolve
     * @param table - The Table to add the element to
     *
     * @return The updated Table
     */
    private static TableNode resolveCollisionWithLinkedList(String element, TableNode table[], TableNode currentNode) {

        // Find the end of the chain for the current node
        while (currentNode != null) {
            currentNode = currentNode.next;
        }

        currentNode.next = new TableNode();
        currentNode.next.data = element;

        return currentNode;
    }

    /**
     * Resolves a conflict by embedding a further hash table inside each element of the existing hash table
     *
     * @param element - the element to add
     * @param table - The table to edit
     *
     * @return The updated table
     */
    private static TableNode resolveCollisionWithHashTable(String element, TableNode table[]) {
        //TODO: Implement this!
        return new TableNode();
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

        if (currentNode == null) return false;

        if (currentNode.data.equals(element)) {
            return true;
        }
        else {
            return elementExistsInTableUsingLinkedListWithElement(element, currentNode.next);
        }
    }

    private static Boolean elementExistsInTableUsingHashTableWithElement(String element, int hashValue, TableNode[] table) {
        // TODO: Implement this!
        return false;
    }
}
