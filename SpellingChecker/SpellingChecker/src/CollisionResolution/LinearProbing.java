package CollisionResolution;
import SpellingChecker.HashTable;
import SpellingChecker.TableNode;

/**
 * Created by oliverpoole on 16/10/15.
 */
public class LinearProbing implements CollisionResolutionInterface {

    public TableNode resolveConflictWithElement(String element, TableNode[] table, TableNode currentNode, HashTable.CollisionResolutionMethod method) {
        return new TableNode();
    }

    public Boolean elementExistsInTable(String element, TableNode[] table, int hashValue, HashTable.CollisionResolutionMethod method) {

        TableNode currentNode = table[hashValue];

        if (currentNode == null) {
            return false;
        }

        if (currentNode.data.equals(element)) {
            return true;
        }
        else {
            return elementExistsInTable(element, table, hashValue + 1, method);
        }

    }


}
