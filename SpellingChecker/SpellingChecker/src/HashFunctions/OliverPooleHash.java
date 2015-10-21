package HashFunctions;

/**
 * Created by Oliver Poole(12022846) on 20/10/15.
 *
 * This is my custom hash function, hopefully it'll be as good as the others!
 */
public class OliverPooleHash implements HashFunctionInterface {

    /*  First Attempt at hash function

        public int hashElement(String element) {

        // Start with just adding the ascii values of the characters

        byte[] chars = element.getBytes();
        int hashValue = 0;

        for (byte letter : chars) {
            hashValue += letter;
        }

        return hashValue;
    }
    */


    /* Second Attempt at hash function

    public int hashElement(String element) {

        // Next try multiplying the ascii values of the characters

        byte[] chars = element.getBytes();
        int hashValue = 1;

        for (byte letter : chars) {
            hashValue = hashValue * letter;
        }

        return hashValue;
    }
   */

    /* Third attempt at hash function

    public int hashElement(String element) {

        // Next try truncation, where the first letter is taken, and the last letter

        String firstChar = element.substring(0, 1);
        String lastChar = element.substring(element.length() - 1, element.length());

        // A string containing the first letter of the string, and the last letter
        String concatenatedString = firstChar + lastChar;

        byte[] concatBytes = concatenatedString.getBytes();

        // A string contains the ascii values of the concatenated string in
        String asciiString = new String();
        for (byte character : concatBytes) {
            asciiString += String.valueOf(character);
        }


        return Integer.parseInt(asciiString);
    }

    */

    /* Forth Hash Function Attempt

    public int hashElement(String element) {

        // Next try multiplying the ascii values of the characters
        // then applying an implementation of the avalanche effect

        byte[] chars = element.getBytes();
        int hashValue = 1;

        for (byte letter : chars) {
            hashValue = hashValue * letter;
        }

        hashValue ^= hashValue << chars[0];
        hashValue += hashValue >> chars[chars.length - 1];
        hashValue ^= hashValue << chars[0];
        hashValue += hashValue >> chars[chars.length - 1];
        hashValue ^= hashValue << chars[0];
        hashValue += hashValue >> chars[chars.length - 1];

        return hashValue;
    }
    */

    public int hashElement(String element) {

        // Next try multiplying the ascii values of the characters
        // then applying an implementation of the avalanche effect

        byte[] chars = element.getBytes();
        int hashValue = 1;

        for (byte letter : chars) {
            hashValue = hashValue * letter;
        }

        hashValue ^= hashValue << chars[0];
        hashValue += hashValue >> chars[chars.length - 1];
        hashValue ^= hashValue << chars[0];
        hashValue += hashValue >> chars[chars.length - 1];
        hashValue ^= hashValue << chars[0];
        hashValue += hashValue >> chars[chars.length - 1];

        return hashValue;
    }
}
