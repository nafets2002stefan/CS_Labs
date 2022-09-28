package Lab_1.ciphers;

import Lab_1.Crypting;

import java.util.ArrayList;
import java.util.List;

public class Caesar_With_Permutation_Cipher extends Crypting {
    private static String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static int ALPHABET_LENGTH = 26;
    private String message;
    private int key;
    private String keyword;

    public Caesar_With_Permutation_Cipher(String message, int key, String keyword) {
        this.message = message;
        this.key = key;
        this.keyword = keyword;
    }

    public String encrypt() {
        var encryptedMessage = "";
        var alphabetChanged = generateAlphabet();
        List<Integer> originalMessageIndexes = new ArrayList<>();

        for (char ch: this.message.toCharArray()) {
            originalMessageIndexes.add(ch-97);
        }

        for (int index: originalMessageIndexes) {
            index = (index + this.key) % ALPHABET_LENGTH;
            encryptedMessage += alphabetChanged.get(index);
        }
        return encryptedMessage;
    }

    public String decrypt(String encryptedMessage) {
        var decryptedMessage = "";
        var originalMessageIndexes = restoreOriginalIndexes(encryptedMessage);

        for (int i = 0; i < originalMessageIndexes.size(); i++) {
            var originalIndex = originalMessageIndexes.get(i);
            decryptedMessage += ALPHABET.toCharArray()[originalIndex];
        }
        return decryptedMessage;
    }

    private List<Character> removeDuplicates() {
        var keywordArray = this.keyword.toCharArray();
        List<Character> list = new ArrayList<>();

        for (char ch: keywordArray) {
            if(!list.contains(ch))
                list.add(ch);
        }
        return list;
    }

    private List<Integer> restoreOriginalIndexes(String encryptedMessage) {
        List<Integer> originalMessageIndexes = new ArrayList<>();
        var alphabetChanged = generateAlphabet();

        for (char ch: encryptedMessage.toCharArray()) {
            int index = (alphabetChanged.indexOf(ch) - this.key) ;
            index = index >= 0 ? index % ALPHABET_LENGTH : index + ALPHABET_LENGTH;
            originalMessageIndexes.add(index);
        }
        return originalMessageIndexes;
    }

    private List<Character> generateAlphabet() {
        var key = removeDuplicates();
        char[] alphabet = ALPHABET.toCharArray();
        List<Character> finalAlphabet = new ArrayList<>();

        finalAlphabet.addAll(key);

        for (char ch : alphabet)
            if (!key.contains(ch))
                finalAlphabet.add(ch);

        return finalAlphabet;
    }
}
