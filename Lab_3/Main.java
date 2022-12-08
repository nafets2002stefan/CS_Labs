package Lab_3;

public class Main {
    public static void main(String[] args) {
        RSA_Cipher cipher = new RSA_Cipher(137, 193);

        String encrypted = cipher.encrypt("testing");
        System.out.println(encrypted);

        String decrypted = cipher.decrypt(encrypted);
        System.out.println(decrypted);
    }
}
