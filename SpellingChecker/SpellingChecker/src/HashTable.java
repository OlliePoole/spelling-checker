import com.sun.istack.internal.NotNull;

/**
 * Created by oliverpoole on 15/10/15.
 */
public class HashTable {

    private int tableSize = 0;
    private TableNode[] table;
    private HashFunction hashFunction;

    public enum HashFunction {
        SuperFastHash
    }

    /**
     * Constructor for the HashTable class
     *
     * @param filePath - The filepath for the contents of the dictionary.txt file
     * @param tableSize - The size of the table to use
     * @param hashFunction - The hash function to use
     */
    public HashTable(String filePath, int tableSize, HashFunction hashFunction) {
        this.tableSize = tableSize;
        this.hashFunction = hashFunction;

        // Create new table
        this.createEmptyHashTable();

        // Load from .txt file




        // Add contents of file to hash table


    }


    /**
     * Hashes an element so it can be added to the table
     *
     * @param element - The element to hash
     * @return The hashvalue of that element
     */
    protected int hashElement(String element) {

        int hashValue = 0;

        switch (hashFunction) {
            case SuperFastHash: {
                hashValue = SuperFastHash.superFastHash(element);
            }
        }

        return hashValue;
    }


    /**
     * Creates a blank hashtable ready for use
     */
    private void createEmptyHashTable() {
        table = new TableNode[tableSize];
    }


    /**
     * Adds an element to the hash table
     * @param element
     */
    private void addElementToHashTable(@NotNull String element) {

        // Does the element exist in the table?
        if (!elementExistsInHashTable(element)) {

            int hashValue = hashElement(element);

            // Find the current node at that hash index
            TableNode currentNode = table[hashValue];

            // Instantiate an empty node ready for insertion
            TableNode newNode = new TableNode();
            newNode.data = element;

            // Is there a current node at the hashValue index?
            if (currentNode == null) {
                // No, add it to the table
                table[hashValue] = newNode;
            }
            else {
                // Yes there is a node, loop though it's sub-nodes
                while (currentNode != null) {
                    currentNode = currentNode.next;
                }
                currentNode.next = newNode;
            }
        }
    }


    /**
     * Searches the hash table for the requested element
     *
     * @param element
     * @return True, if the element was found in the table
     */
    public Boolean elementExistsInHashTable(String element) {
        return true;
    }
}
