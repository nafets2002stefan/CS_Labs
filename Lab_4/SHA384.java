package Lab_4;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class SHA384 {
    public String getSecurePassword(String passwordToHash, String salt){
        String generatedPassword = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-384");
            messageDigest.update(salt.getBytes());
            byte[] bytes = messageDigest.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16)
                        .substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public String getSalt() throws NoSuchAlgorithmException {
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return Arrays.toString(salt);
    }
}
