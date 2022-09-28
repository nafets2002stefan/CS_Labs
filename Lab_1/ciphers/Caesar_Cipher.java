package Lab_1.ciphers;

import Lab_1.Crypting;

public class Caesar_Cipher extends Crypting {
    private int key;
    private String message;
    private int ALPHABET_LENGTH = 26;

    public Caesar_Cipher(String message, int key) {
        this.message = message;
        this.key = key;
    }
    public String encrypt() {
        return encrypt(this.message, this.key);
    }
    public String decrypt(String encrypted_message) {
        return decrypt(encrypted_message, this.key);
    }


    private String encrypt(String message, int key) {
        String encryptedMessage = "";

        for (char ch: message.toCharArray()) {
            encryptedMessage += shiftChar(ch, key);
        }
        return encryptedMessage;
    }

    private String decrypt(String encrypted_message, int key) {
        String decryptedMessage = "";

        for (char ch: encrypted_message.toCharArray()) {
            decryptedMessage += shiftChar(ch, -key);
        }
        return decryptedMessage;
    }

    private char shiftChar(char ch, int key) {
        var modulo = key % ALPHABET_LENGTH;
        return (char) (ch + modulo);

    }
}

