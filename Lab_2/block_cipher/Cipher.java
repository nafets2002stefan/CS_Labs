package Lab_2.block_cipher;

public interface Cipher {
    String encrypt(String plainText, String key);
    String decrypt(String plainText, String key);
}
