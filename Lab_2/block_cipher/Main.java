package Lab_2.block_cipher;

public class Main {
    public static void main(String[] args) {
        DES test = new DES();
        String key = "AABB09182736CCDD";

        String encrypted = test.encrypt("123456ABCD132536", key);
        String decrypted = test.decrypt(encrypted, key);

        System.out.println(encrypted);
        System.out.println(decrypted);
    }
}
