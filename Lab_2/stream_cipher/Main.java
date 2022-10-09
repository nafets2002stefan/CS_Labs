package Lab_2.stream_cipher;

public class Main {
    public static void main(String[] args) {

        RC4 test = new RC4();
        test.setKey("12345");
        var encrypted = test.encrypt("1990019900");
        System.out.println(encrypted + " is encrypted message");
        System.out.println(test.decrypt(encrypted) + " is decrypted message");

    }
}
