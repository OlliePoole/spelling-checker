package HashFunctions;

/**
 * Created by Oliver Poole(12022846) on 19/10/15.
 *
 * An implementation of Bob Jenkins Hash
 */
public class BobJenkinsHash implements HashFunctionInterface {

    public int hashElement(String element) {

        byte[] elementArray = element.getBytes();
        int length = elementArray.length;

        int a, b, c;
        int pc = 0;

        a = b = c = 0xdeadbeef + length + pc;

        int offset = 0;
        while (length > 12) {
            a += elementArray[offset + 0];
            a += elementArray[offset + 1] << 8;
            a += elementArray[offset + 2] << 16;
            a += elementArray[offset + 3] << 24;
            b += elementArray[offset + 4];
            b += elementArray[offset + 5] << 8;
            b += elementArray[offset + 6] << 16;
            b += elementArray[offset + 7] << 24;
            c += elementArray[offset + 8];
            c += elementArray[offset + 9] << 8;
            c += elementArray[offset + 10] << 16;
            c += elementArray[offset + 11] << 24;

            // mix(a, b, c);
            a -= c;
            a ^= rotate(c, 4);
            c += b;
            b -= a;
            b ^= rotate(a, 6);
            a += c;
            c -= b;
            c ^= rotate(b, 8);
            b += a;
            a -= c;
            a ^= rotate(c, 16);
            c += b;
            b -= a;
            b ^= rotate(a, 19);
            a += c;
            c -= b;
            c ^= rotate(b, 4);
            b += a;

            length -= 12;
            offset += 12;
        }

        switch (length) {
            case 12:
                c += elementArray[offset + 11] << 24;
            case 11:
                c += elementArray[offset + 10] << 16;
            case 10:
                c += elementArray[offset + 9] << 8;
            case 9:
                c += elementArray[offset + 8];
            case 8:
                b += elementArray[offset + 7] << 24;
            case 7:
                b += elementArray[offset + 6] << 16;
            case 6:
                b += elementArray[offset + 5] << 8;
            case 5:
                b += elementArray[offset + 4];
            case 4:
                a += elementArray[offset + 3] << 24;
            case 3:
                a += elementArray[offset + 2] << 16;
            case 2:
                a += elementArray[offset + 1] << 8;
            case 1:
                a += elementArray[offset + 0];
                break;
            case 0:
                return c;
        }

        // Final mixing of three values into c
        c ^= b;
        c -= rotate(b, 14);
        a ^= c;
        a -= rotate(c, 11);
        b ^= a;
        b -= rotate(a, 25);
        c ^= b;
        c -= rotate(b, 16);
        a ^= c;
        a -= rotate(c, 4);
        b ^= a;
        b -= rotate(a, 14);
        c ^= b;
        c -= rotate(b, 24);

        return c;
    }

    long rotate(int x, int distance) {
        return (x << distance) | (x >>> (32 - distance));
    }
}
