package HashFunctions;

/**
 * Created by Oliver Poole(12022846) on 19/10/15.
 *
 * Implementation of One at a Time Hash by Bob Jenkins
 */
public class OneAtATimeHash implements HashFunctionInterface {

    public int hashElement(String element) {

        int hashValue = 0;
        byte[] elementArray = element.getBytes();

        for (byte b : elementArray) {
            hashValue += (b & 0xFF);
            hashValue += (hashValue << 10);
            hashValue ^= (hashValue >>> 6);
        }

        hashValue += (hashValue << 3);
        hashValue ^= (hashValue >>> 11);
        hashValue += (hashValue << 15);

        return hashValue;
    }
}
