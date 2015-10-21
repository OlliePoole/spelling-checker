package SpellingChecker;

import CollisionResolution.*;
import HashFunctions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Oliver Poole(12022846) on 15/10/15.
 *
 * Implementation of a HashTable
 */
public class HashTable {

    public static int tableSize = 0;
    public TableNode[] table;
    public HashFunction hashFunction;
    public CollisionResolutionMethod collisionResolutionMethod;

    public enum HashFunction {
        SuperFastHash,
        BobJenkinsHash,
        OneAtATimeHash,
        OliverPooleHash
    }

    public enum CollisionResolutionMethod {
        LinearProbing,
        Chaining
    }

    /**
     * Constructor for the HashTable class
     *
     * @param filePath - The filepath for the contents of the dictionary.txt file
     * @param tableSize - The size of the table to use
     * @param hashFunction - The hash function to use
     */
    public HashTable(String filePath, int tableSize, HashFunction hashFunction, CollisionResolutionMethod collisionResolutionMethod) {

        HashTable.tableSize = tableSize;
        this.hashFunction = hashFunction;
        this.collisionResolutionMethod = collisionResolutionMethod;

        // Create new table
        this.createEmptyHashTable();

        Scanner lineScanner = null;
        try {
            lineScanner = new Scanner(new File(filePath));

        } catch (FileNotFoundException e) {
            System.out.println("Dictionary File not found");
            e.printStackTrace();
        }

        while (lineScanner.hasNextLine()) {
            String line = lineScanner.nextLine();
            addElementToHashTable(line);
        }

        lineScanner.close();

        // No we've finished adding item, reset the Observer
        HashTableObserver.elementNotFound = 0;
        HashTableObserver.elementFound = 0;
        HashTableObserver.averageSuccessfulLookup = new ArrayList<>();
        HashTableObserver.averageFailedLookup = new ArrayList<>();
    }


    /**
     * Hashes an element so it can be added to the table
     *
     * @param element - The element to hash
     * @return The hash value of that element
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
            case OliverPooleHash: {
                hashingFunction = new OliverPooleHash();
                break;
            }
        }

        return Math.abs(hashingFunction.hashElement(element)) % tableSize;
    }


    /**
     * Creates a blank hash table ready for use
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

            CollisionResolutionInterface collisionResolution;

            switch (collisionResolutionMethod) {
                case LinearProbing: {
                    collisionResolution = new LinearProbing();

                    hashValue = (int)collisionResolution.resolveConflictWithElement(element, table, hashValue, collisionResolutionMethod);

                    TableNode newNode = new TableNode();
                    newNode.data = element;

                    table[hashValue] = newNode;
                    break;
                }

                case Chaining: {
                    collisionResolution = new Chaining();

                    // Resolve the conflict and update the node in the table
                    TableNode currentNode = (TableNode)collisionResolution.resolveConflictWithElement(element, table, hashValue, collisionResolutionMethod);

                    table[hashValue] = currentNode;

                    break;
                }
            }
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
        if (currentNode == null) {
            HashTableObserver.elementNotFound++;
            return false;
        }

        // Using the same collision resolution strategies, search for the element
        CollisionResolutionInterface collisionResolution = null;

        switch (collisionResolutionMethod) {
            case LinearProbing: collisionResolution = new LinearProbing(); break;
            case Chaining: collisionResolution = new Chaining(); break;
        }

        // Resolve the conflict and assign the updated table
        Boolean elementFound = collisionResolution.elementExistsInTable(element, table, hashValue, collisionResolutionMethod);

        return elementFound;
    }
}
