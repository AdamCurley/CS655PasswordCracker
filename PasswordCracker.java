public class PasswordCracker {

 public static char[] ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
 public static final int PASSWORD_MAX_LENGTH = 5;
 
  public void verify(hash, password) throws NoSuchAlgorithmException {
     MessageDigest md = MessageDigest.getInstance("MD5");
     md.update(password.getBytes());
     byte[] digest = md.digest();
     String myHash = DatatypeConverter
       .printHexBinary(digest).toUpperCase();
     assertThat(myHash.equals(hash)).isTrue();
 }
 
}
