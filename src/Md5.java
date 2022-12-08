import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {

    public static char[] ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    public static final int PASSWORD_MAX_LENGTH = 5;

    public static String hashMd5(String input) throws IOException {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);

            StringBuilder hashText = new StringBuilder(no.toString(16));

            while (hashText.length() < 32) {
                hashText.insert(0, "0");
            }
            return hashText.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String cracker(int start, String hash, int total) throws IOException {
        String password = "";
        char[] digits = {'a', 'a', 'a', 'a', 'a'};

        for (int i = (ALPHABET.length / total) * (start - 1); start == total ? i < ALPHABET.length : i < (ALPHABET.length / total) * start; i++) {
            for (char element : ALPHABET) {
                for (char item : ALPHABET) {
                    for (char value : ALPHABET) {
                        for (char c : ALPHABET) {
                            digits[0] = ALPHABET[i];
                            digits[1] = element;
                            digits[2] = item;
                            digits[3] = value;
                            digits[4] = c;

                            password = String.valueOf(digits);
                            System.out.println("Worker" + start + " is cracking: " + password);
                            if (hash.equals(hashMd5(password))) {
                                return password;
                            }
                        }
                    }
                }
            }
        }

        return null; //does nothing but code won't run without it (until complete)
    }
}