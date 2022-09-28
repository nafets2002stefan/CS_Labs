package Lab_1.ciphers;

import Lab_1.Crypting;

public class Vigenere_Cipher extends Crypting {
    private int ALPHABET_LENGTH = 26;
    private String message;
    private String key;

    public Vigenere_Cipher(String message, String key) {
        this.message = message;
        this.key = key;
    }

    public String encrypt() {
        return encrypt(this.message, generateKey());
    }

    public String decrypt(String encryptedMessage) {
        return decrypt(encryptedMessage, generateKey());
    }

    private String decrypt(String encryptedMessage, String keyword) {
        String decryptedMessage = "";

        for (int i = 0, j = 0; i < encryptedMessage.length(); i++) {
            char ch = encryptedMessage.charAt(i);

            if(ch < 'A' || ch > 'Z')
                continue;

            decryptedMessage += (char) ((ch- keyword.charAt(j) + ALPHABET_LENGTH) % ALPHABET_LENGTH + 'A');
            j = ++j % keyword.length();
        }
        return decryptedMessage;
    }

    private String encrypt(String message, String keyword) {
        String encrypted = "";

        for (int i = 0, j = 0; i < message.length(); i++) {
            char ch = message.charAt(i);

            if(ch < 'A' || ch > 'Z')
                continue;
            encrypted += (char) ((ch + keyword.charAt(j) - 2 * 'A') % ALPHABET_LENGTH + 'A');
            j = ++j % keyword.length();
        }
        return encrypted;
    }


    private String generateKey() {
        return generateKey(this.key, this.message.length());
    }

    private String generateKey(String key, int length) {
        String finalKey = "";

        int i = 0;
        while(finalKey.length() != length) {
            if(i == key.length())
                i = 0;
            else{
                finalKey += key.charAt(i);
                i++;
            }
        }
        return finalKey;
    }



}
