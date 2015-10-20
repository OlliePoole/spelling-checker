package HashFunctions;

import SpellingChecker.HashTable;

/**
 * Created by Oliver Poole(12022846) on 19/10/15.
 */
public interface HashFunctionInterface {

    /**
     * Hashes a function
     *
     * @param element - The element to be hashed
     *
     * @return the hash value of the element
     */
    int hashElement(String element);
}
