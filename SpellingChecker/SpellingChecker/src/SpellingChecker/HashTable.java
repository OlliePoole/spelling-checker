package SpellingChecker;

import CollisionResolution.*;
import HashFunctions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Oliver Poole(12022846) on 15/10/15.
 */
public class HashTable {

    private int tableSize = 0;
    private TableNode[] table;
    private HashFunction hashFunction;
    private CollisionResolutionMethod collisionResolutionMethod;

    public enum HashFunction {
        SuperFastHash,
        BobJenkinsHash,
        OneAtATimeHash
    }

    public enum CollisionResolutionMethod {
        LinearProbing,
        ChainingLinkedList,
        ChainingHashTable
    }

    /**
     * Constructor for the HashTable class
     *
     * @param filePath - The filepath for the contents of the dictionary.txt file
     * @param tableSize - The size of the table to use
     * @param hashFunction - The hash function to use
     */
    public HashTable(String filePath, int tableSize, HashFunction hashFunction, CollisionResolutionMethod collisionResolutionMethod) throws IOException {

        this.tableSize = tableSize;
        this.hashFunction = hashFunction;
        this.collisionResolutionMethod = collisionResolutionMethod;

        // Create new table
        this.createEmptyHashTable();

        // Load from .txt file
        for (String line : Files.readAllLines(Paths.get(filePath))) {

            // Add contents of file to hash table
            addElementToHashTable(line);
        }
    }


    /**
     * Hashes an element so it can be added to the table
     *
     * @param element - The element to hash
     * @return The hashvalue of that element
     */
    protected int hashElement(String element) {

        HashFunctionInterface hashingFunction = null;

        switch (hashFunction) {
            case SuperFastHash: {
                hashingFunction = new SuperFastHash();
                break;
            }
            case BobJenkinsHash: {
                hashingFunction = new BobJenkinsHash();
                break;
            }
            case OneAtATimeHash: {
                hashingFunction = new OneAtATimeHash();
                break;
            }
        }

        return Math.abs(hashingFunction.hashElement(element)) % tableSize;
    }


    /**
     * Creates a blank hashtable ready for use
     */
    private void createEmptyHashTable() {
        table = new TableNode[tableSize];
    }


    /**
     * Adds an element to the hash table
     * @param element - the element to be added
     */
    private void addElementToHashTable(String element) {

        // Does the element exist in the table?
        if (!elementExistsInHashTable(element)) {

            int hashValue = hashElement(element);

            // Find the current node at that hash index
            TableNode currentNode = table[hashValue];

            CollisionResolutionInterface collisionResolution = null;

            switch (collisionResolutionMethod) {
                case LinearProbing: collisionResolution = new LinearProbing(); break;
                case ChainingHashTable: collisionResolution = new Chaining(); break;
                case ChainingLinkedList: collisionResolution = new Chaining(); break;
            }

            // Resolve the conflict and update the node in the table
            currentNode = collisionResolution.resolveConflictWithElement(element, table, currentNode, collisionResolutionMethod);

            table[hashValue] = currentNode;

        }
    }


    /**
     * Searches the hash table for the requested element
     *
     * @param element - The element to be looked for
     * @return True, if the element was found in the table
     */
    public Boolean elementExistsInHashTable(String element) {

        int hashValue = hashElement(element);

        TableNode currentNode = table[hashValue];

        // Is there anything at the hash value location?
        if (currentNode == null) return false;

        // Using the same collision resolution strategies, search for the element
        CollisionResolutionInterface collisionResolution = null;

        switch (collisionResolutionMethod) {
            case LinearProbing: collisionResolution = new LinearProbing(); break;
            case ChainingHashTable: collisionResolution = new Chaining(); break;
            case ChainingLinkedList: collisionResolution = new Chaining(); break;
        }

        // Resolve the conflict and assign the updated table
        return collisionResolution.elementExistsInTable(element, table, hashValue, collisionResolutionMethod);
    }
}
