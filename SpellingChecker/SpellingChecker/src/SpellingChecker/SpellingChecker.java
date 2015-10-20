package SpellingChecker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by oliverpoole on 15/10/15.
 */
public class SpellingChecker {

    public static void main(String[] args) {
        // Lets do something useful here

        // Build the hash table
        HashTable table = new HashTable("SpellingChecker/src/SpellingChecker/dictionary.txt", 150000,
                HashTable.HashFunction.SuperFastHash,
                HashTable.CollisionResolutionMethod.LinearProbing);

        // Load the file to check
        String filePath = "SpellingChecker/src/SpellingChecker/InputFile.txt";

        Scanner lineScanner = null;
        try {
            lineScanner = new Scanner(new File(filePath));

        } catch (FileNotFoundException e) {
            System.out.println("Input File not found");
            e.printStackTrace();
        }

        while (lineScanner.hasNextLine()) {
            Scanner wordScanner = new Scanner(lineScanner.nextLine());

            while (wordScanner.hasNext()) {
                String word = wordScanner.next().replaceAll("[^a-zA-Z ]", "").toLowerCase();

                if (!(word.equals("") || word.equals(" "))) {
                    Boolean wordExists = table.elementExistsInHashTable(word);

                    if (!wordExists) {
                        System.out.println(word + " <-- is unrecognized");
                    }
                }
            }
        }


        /*********     Analytics     **************/

        System.out.println("Successful searches: " + HashTableObserver.elementFound);
        System.out.println("Unsuccessful searches: " + HashTableObserver.elementNotFound);

    }
}
