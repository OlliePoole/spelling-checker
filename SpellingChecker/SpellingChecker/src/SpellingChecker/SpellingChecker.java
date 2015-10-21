package SpellingChecker;

import java.io.*;
import java.util.Scanner;

/**
 * Created by oliverpoole on 15/10/15.
 */
public class SpellingChecker {

    public static void main(String[] args) {
        // Lets do something useful here

        // Build the hash table
        HashTable table = new HashTable("SpellingChecker/src/SpellingChecker/dictionary.txt", 150001,
                HashTable.HashFunction.OliverPooleHash,
                HashTable.CollisionResolutionMethod.Chaining);

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


        /*********     Analytics     *********/

        // Find the average number of failed searches
        int total = 0;

        for (Integer searchCount : HashTableObserver.averageFailedLookup) {
            total += searchCount;
        }

        double average = (double)total / HashTableObserver.averageFailedLookup.size();

        System.out.println("Average number of checks when search fails: " + average);
        System.out.println("Unsuccessful searches: " + HashTableObserver.elementNotFound);


        // Find the average number of searches
        total = 0;

        for (Integer searchCount : HashTableObserver.averageSuccessfulLookup) {
            total += searchCount;
        }

        average = (double)(total / HashTableObserver.averageSuccessfulLookup.size());
        System.out.println("Average = " + total + " / " + HashTableObserver.averageSuccessfulLookup.size());

        System.out.println("Average number of checks when search is successful: " + average);

        System.out.println("Successful searches: " + HashTableObserver.elementFound);



        // Find the load factor
        // Load Factor = number of buckets / table size

        int buckets = 0;

        for (int x = 0; x < HashTable.tableSize; x++) {
            TableNode node = table.table[x];

            if (node != null) buckets++;
        }

        System.out.println("Table Load Factor: " + (double)(buckets / table.tableSize));


        // Find the distribution of items in the table

        switch (table.collisionResolutionMethod) {
            case Chaining: {
                // Loop through table, if a node, find out how many chained nodes

                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("SpellingChecker/src/SpellingChecker/bucketDistribution.txt"));

                    for (int x = 0; x < HashTable.tableSize; x++) {
                        TableNode node = table.table[x];

                        if (node == null) {
                            writer.write(0 + "\n");

                        } else {
                            int chainLength = 1;
                            while (node.next != null) {
                                chainLength++;
                                node = node.next;
                            }
                            writer.write(chainLength + "\n");
                        }
                    }
                    writer.close();
                }
                catch (IOException exception) {
                    System.out.println("File not found");
                }

            }
        }
    }
}
