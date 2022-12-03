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
  char[] digits = {'a','a','a','a','a'}; //initial combination

  for(int i = 0; i < 5; i++) {
    for(int j = 0; j < 51; j++) {
      password = String.valueOf(digits);
      String testHash = hashMd5(password);
      if (hash.equals(testHash)) {
        return password.toString();
      }
    }
  }
  return null; //does nothing but code won't run without it (until complete)
 }
 

 public static void main(String args[]) throws NoSuchAlgorithmException
  {
    String testPassword = "TeStr";
    String testHash = hashMd5(testPassword);
    System.out.println("The Password is: " + testPassword);
    System.out.println("User Submitted MD5 Hash is: " + testHash);
    System.out.println("The cracked password is: " + cracker(testHash));
  }
}
