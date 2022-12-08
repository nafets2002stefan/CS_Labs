package Lab_4;

import Lab_3.RSA_Cipher;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        DB dataBase = new DB();
        SHA384 sha384 = new SHA384();
        String salt = sha384.getSalt();

        //password
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the password: ");
        String password = scanner.nextLine();

        //hashing
        String securePassword = sha384.getSecurePassword(password, salt);
        System.out.println("Hashed password: " + securePassword);

        dataBase.addToDB(securePassword);


        //using rsa asymmetric cipher to encrypt
        RSA_Cipher cipher = new RSA_Cipher(137, 193);

        //encrypt the password
        String encrypted = cipher.encrypt(securePassword);
        //decryption
        String decrypted = cipher.decrypt(encrypted);
        System.out.println("Decrypted message, integer type = " +  decrypted);

        if (dataBase.checkDbIfContains(decrypted) || !cipher.decrypt(encrypted).isEmpty() ){
            System.out.println("Password is correct!");
        } else {
            System.out.println("Wrong password!");
        }
    }
}
