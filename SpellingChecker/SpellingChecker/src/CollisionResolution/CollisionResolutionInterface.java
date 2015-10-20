package CollisionResolution;

import SpellingChecker.HashTable;
import SpellingChecker.TableNode;

/**
 * Created by oliverpoole on 19/10/15.
 */
public interface CollisionResolutionInterface {

    /**
     * An interface method for resolving a conflict
     *
     * @param element - the element to add
     * @param table - The table to add the element to
     * @param hashValue - The hash value of the element
     * @param method - The collision resolution method to use
     *
     * @return The updated node
     */
    Object resolveConflictWithElement(String element, TableNode[] table, int hashValue, HashTable.CollisionResolutionMethod method);


    /**
     * An interface method for searching for an element given a certain collison resolution type
     *
     * @param element - The element to find
     * @param table - The table to search in
     * @param hashValue - The element's hash value
     * @param method - The collision resolution method to use
     *
     * @return True if the element is found, false if the element doesn't exist
     */
    Boolean elementExistsInTable(String element, TableNode[] table, int hashValue, HashTable.CollisionResolutionMethod method);
}
