package Lab_2.stream_cipher;

import java.util.ArrayList;
import java.util.List;

public class RC4 implements Cipher{

    List<Integer> S = new ArrayList<>();
    List<Integer> T = new ArrayList<>();
    String key;
    long generatedKey;

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String encrypt(String message) {

        fillSTArrays(message.length());
        swapSTArrays();

        generatedKey = generateNewKey();

        long encryptedMessage = Long.parseLong(message) ^ generatedKey;

        return encryptedMessage + "";
    }

    @Override
    public String decrypt(String encryptedMessage) {

        long message = Long.parseLong(encryptedMessage) ^ generatedKey;
        return message + "";
    }

    private void fillSTArrays(int length) {
        S.clear();T.clear();

        for (int i = 0; i < length; i++) {
            S.add(i);
            int keyIndex = i % key.length();
            int keyNumber = key.charAt(keyIndex) - '0';
            T.add(keyNumber);
        }
    }

    private void swapSTArrays() {
        int j = 0;
        for (int i = 0; i < S.size(); i++) {
            j = (S.get(i) + T.get(i)) % S.size();

            //swap s[i] , s[j]
            int temp = S.get(i);
            S.set(i, S.get(j));
            S.set(j, temp);
        }

    }

    private long generateNewKey() {
        long newKey = 0;
        int j = 0;
        for (int i = 0; i < S.size(); i++) {
            j = (j + S.get(i)) % S.size();

            //swap s[i] , s[j]
            int temp = S.get(i);
            S.set(i, S.get(j));
            S.set(j, temp);

            int key = S.get((S.get(i) + S.get(j)) % S.size());
            newKey = newKey * 10 + key;
        }
        return newKey;
    }

}
