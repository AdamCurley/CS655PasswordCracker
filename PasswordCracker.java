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

  public static void main(String args[]) throws NoSuchAlgorithmException
  {
    String s = "testing";
    System.out.println("Your HashCode Generated by MD5 is: " + getMd5(s));
  }
 }
}
