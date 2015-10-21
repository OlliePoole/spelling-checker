package HashFunctions;

import SpellingChecker.HashTable;

/**
 * Implementation of Super Fast Hash
 * oliverpoole on 15/10/15.
 */
public class SuperFastHash implements HashFunctionInterface {

    public int hashElement(String element) {
        return superFastHash(element);
    }

    private static final long k16_BIT_MASK = 0xFFFFL;

    /**
     * Super Fast Hash, Paul Hsieh
     * more info: http://www.azillionmonkeys.com/qed/hash.html
     *
     * @param element - The element to be hashed
     * @return
     */
    private int superFastHash(String element) {
        int temp;
        int hash = 0;

        // Convert the string element to ASCII
        long asciiValue = 0;
        byte[] stringBytes = element.getBytes();
        for (int x = 0; x < stringBytes.length; x++) { asciiValue += (int)stringBytes[x]; }


        for (int x = 0; x < 4; x += 2) {
            hash += get16BitsAligned(asciiValue, x);

            /*
                << - Left binary shift
                >> - Right binary shift
             */

            temp = (get16BitsAligned(asciiValue, x + 1) << 11) ^ hash;
            hash = (hash << 16) ^ temp;

            hash += hash >> 11;

        }

        /*
           Use the Avalanche effect, meaning small input changes will have a large effect on output.
           Typically used for cryptographic hashes where non-reversibility is crucial
         */

        hash ^= hash << 3;
        hash += hash >> 5;
        hash ^= hash << 4;
        hash += hash >> 17;
        hash ^= hash << 25;
        hash += hash >> 6;

        return hash;
    }


    /********************************************************************\
    |                              Util Methods                          |
    \********************************************************************/

    /**
     * Returns 16 bits from the long number.
     *
     * @param data
     * @param offset one of 0 to 3
     * @return
     */
    private int get16BitsAligned(long data, int offset) {

        // Normalize offset
        offset = offset % 4;

        // Set the mask
        long mask = k16_BIT_MASK << 16 * offset;

        // Get the bits
        long result = data & mask;

        // Put bits in position
        return (int) (result >>> (16 * offset));
    }
}
