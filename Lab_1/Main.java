package Lab_1;

import Lab_1.ciphers.Caesar_Cipher;
import Lab_1.ciphers.Caesar_With_Permutation_Cipher;
import Lab_1.ciphers.Playfair_Cipher;
import Lab_1.ciphers.Vigenere_Cipher;

public class Main {
    public static void main(String[] args) {

        Caesar_Cipher caesarCipher = new Caesar_Cipher("Per aspera ad astra", 4);
        var caesarEncrypted = caesarCipher.encrypt();
        var caesarDecrypted = caesarCipher.decrypt(caesarEncrypted);

        System.out.println("Encrypted :" + caesarEncrypted);
        System.out.println("Decrypted :" + caesarDecrypted);


        Caesar_With_Permutation_Cipher caesarWithPermutationCipher = new Caesar_With_Permutation_Cipher
                ("abstractisation", 2, "john");

        var caesarPermutationEncrypted = caesarWithPermutationCipher.encrypt();
        var caesarPermutationDecrypted = caesarWithPermutationCipher.decrypt(caesarPermutationEncrypted);

        System.out.println("Encrypted :" + caesarPermutationEncrypted);
        System.out.println("Decrypted :" + caesarPermutationDecrypted);


        Vigenere_Cipher vigenereCipher = new Vigenere_Cipher("OBJECTORIENTEDPROGRAMMING", "DO");

        var viginereEncrypted = vigenereCipher.encrypt();
        var viginereDecrypted = vigenereCipher.decrypt(viginereEncrypted);

        System.out.println("Encrypted :" + viginereEncrypted);
        System.out.println("Decrypted :" + viginereDecrypted);

        Playfair_Cipher playfairCipher = new Playfair_Cipher("keyword", "per aspera ad astra");
        var playfairCipherEncrypt = playfairCipher.encrypt();
        var playfairCipherDecrypt = playfairCipher.decrypt(playfairCipherEncrypt);

    }
}
