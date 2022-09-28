package Lab_1;

abstract public class Crypting {
    int ALPHABET_LENGTH = 26;
    String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public abstract String encrypt();
    protected abstract String decrypt(String m);
}
