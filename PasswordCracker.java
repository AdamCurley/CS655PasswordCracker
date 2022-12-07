import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordCracker {
  public static char[] ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
  public static final int PASSWORD_MAX_LENGTH = 5;

 public static String hashMd5(String input) throws NoSuchAlgorithmException {
  try {
  MessageDigest md = MessageDigest.getInstance("MD5");
  byte[] messageDigest = md.digest(input.getBytes());
  BigInteger no = new BigInteger(1, messageDigest);

  String hashtext = no.toString(16);

  while (hashtext.length() < 32) {
    hashtext = "0" + hashtext;
  }
  return hashtext;
  }
  
  catch (NoSuchAlgorithmException e) {
    throw new RuntimeException(e);
  }
 }

 public static String cracker(String hash) throws NoSuchAlgorithmException {
  String password = "";
  char[] digits = {'a','a','a','a','a'};

  for(int i = 0; i < ALPHABET.length; i++) {
    for(int j = 0; j < ALPHABET.length; j++) {
      for(int k = 0; k < ALPHABET.length; k++) {
        for(int l = 0; l < ALPHABET.length; l++) {
          for(int m = 0; m < ALPHABET.length; m++) {
            digits[0] = ALPHABET[i];
            digits[1] = ALPHABET[j];
            digits[2] = ALPHABET[k];
            digits[3] = ALPHABET[l];
            digits[4] = ALPHABET[m];

            password = String.valueOf(digits);
            System.out.println(password);
            String testHash = hashMd5(password);
            if (hash.equals(testHash)) {
              return password.toString();
            }
          }
        }
      }
    }
  }

  return null; //does nothing but code won't run without it
}
 

 public static void main(String args[]) throws NoSuchAlgorithmException
  {
    String testPassword = "TeStr";
    String testHash = hashMd5(testPassword);
    System.out.println("The Password is: " + testPassword);
    System.out.println("User Submitted MD5 Hash is: " + testHash);
    
    long startTime = System.currentTimeMillis();
    System.out.println("The cracked password is: " + cracker(testHash));
    long elapsedTime = System.currentTimeMillis() - startTime;
    long elapsedSeconds = elapsedTime / 1000;
    long elapsedMinutes = elapsedSeconds / 60;
    System.out.println("Time elapsed: " + elapsedMinutes);
  }
}
