package SpellingChecker;

import java.io.IOException;

/**
 * Created by oliverpoole on 15/10/15.
 */
public class SpellingChecker {

    public static void main(String[] args) {
        // Lets do something useful here

        try {
            HashTable table = new HashTable("/Users/oliverpoole/Desktop/dictionary.txt", 15000,
                    HashTable.HashFunction.SuperFastHash,
                    HashTable.CollisionResolutionMethod.ChainingLinkedList);

            System.out.println("Element 'aa' exists: " + table.elementExistsInHashTable("aa"));
            System.out.println("Element 'vespids' exists: " + table.elementExistsInHashTable("vespids"));
            System.out.println("Element 'Ollieinsjbd' exists: " + table.elementExistsInHashTable("Ollieinsjbd"));

            System.out.println("Hello world");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
