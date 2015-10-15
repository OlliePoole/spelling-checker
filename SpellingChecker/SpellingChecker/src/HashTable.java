

/**
 * Created by oliverpoole on 15/10/15.
 */
public class HashTable {

    int tableSize = 0;
    TableNode[] table;

    public HashTable(String filePath, int tableSize) {

    }

    /*
        Creates a blank hashtable ready for use
     */
    private void createEmptyHashTable() {
        table = new TableNode[tableSize];
    }

    /*
        Adds an element to the hashtable
     */
    private void addElementToHashTable(String element) {

    }

    /*
        Returns true or false if element is found in the hash table
     */
    private Boolean elementExistsInHashTable(String element) {
        return true;
    }
}
