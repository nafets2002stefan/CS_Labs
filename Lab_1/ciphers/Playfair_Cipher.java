package Lab_1.ciphers;
import Lab_1.Crypting;

import java.awt.Point;

public class Playfair_Cipher extends Crypting {

    private String message;
    private int length = 0;
    private String[][] table;

    public Playfair_Cipher(String keyword, String message) {
        table = this.cipherTable(keyword);
        this.message = message;
        String output = encrypt(message);
        String decodedOutput = decrypt(output);

        this.printTable(table);
        this.printResults(output, decodedOutput);
    }

    private String[][] cipherTable(String key) {
        String[][] playfairTable = new String[5][5];
        String keystring = key + "ABCDEFGHIKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                playfairTable[i][j] = "";

        for (int k = 0; k < keystring.length(); k++) {
            boolean repeat = false;
            boolean used = false;

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if(playfairTable[i][j].equals("" + keystring.charAt(k)))
                        repeat = true;
                    else if (playfairTable[i][j].equals("") && !repeat && !used) {
                        playfairTable[i][j] = "" + keystring.charAt(k);
                        used = true;
                    }
                }
            }
        }
        return playfairTable;
    }

    public String encrypt() {
        return encrypt(this.message);
    }

    private String encrypt(String in) {
        length = (int) in.length() / 2 + in.length() % 2;

        for (int i = 0; i < (length - 1); i++) {
            if(in.charAt(2 * i) == in.charAt(2 * i + 1)) {
                in = new StringBuffer(in).insert(2 * i + 1, 'X').toString();
                length = (int) in.length() / 2 + in.length() % 2;
            }
        }

        String [] digraph = new String[length];
        for (int i = 0; i < length; i++) {
            if(i == (length - 1) && in.length() / 2 == (length - 1))
                in = in + "X";
            digraph[i] = in.charAt(2 * i) + "" + in.charAt(2 * i + 1);
        }

        String out = "";
        String [] encDigraphs = new String[length];
        encDigraphs = encodeDigraph(digraph);
        for (int i = 0; i < length; i++)
            out = out + encDigraphs[i];
        return out;
    }

    private String[] encodeDigraph(String di[]) {
        String[] enc = new String[length];
        for (int i = 0; i < length; i++) {
            char a = di[i].charAt(0);
            char b = di[i].charAt(1);

            int r1 = (int) getPoint(a).getX();
            int r2 = (int) getPoint(b).getX();
            int c1 = (int) getPoint(a).getY();
            int c2 = (int) getPoint(b).getY();

            if(r1 == r2) {
                c1 = (c1 + 1) % 5;
                c2 = (c2 + 1) % 5;
            }

            else if(c1 == c2) {
                r1 = (r1 + 1) % 5;
                r2 = (r2 + 1) % 5;
            }

            else {
                int temp = c1;
                c1 = c2;
                c2 = temp;
            }

            enc[i] = table[r1][c1] + "" + table[r2][c2];
        }
        return enc;
    }

    public String decrypt(String out) {
        String decoded = "";
        for (int i = 0; i < out.length() / 2; i++) {
            char a = out.charAt(2 * i);
            char b = out.charAt(2 * i + 1);
            int r1 = (int) getPoint(a).getX();
            int r2 = (int) getPoint(b).getX();
            int c1 = (int) getPoint(a).getY();
            int c2 = (int) getPoint(b).getY();

            if(r1 == r2) {
                c1 = (c1 + 4) % 5;
                c2 = (c2 + 4) % 5;
            }
            else if(c1 == c2) {
                r1 = (r1 + 4) % 5;
                r2 = (r2 + 4) % 5;
            }
            else {
                int temp = c1;
                c1 = c2;
                c2 = temp;
            }
            decoded = decoded + table[r1][c1] + table[r2][c2];
        }
        return decoded;
    }

    private Point getPoint(char c) {
        Point pt =  new Point(0, 0);
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if(c == table[i][j].charAt(0))
                    pt = new Point(i, j);
                return pt;
    }

    private void printTable(String[][] printedTable) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.println(printedTable[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void printResults(String enc, String dec) {
        System.out.println("Encoded Message :");
        System.out.println(enc);
        System.out.println();
        System.out.println("Decoded Message :");
        System.out.println(dec);
    }
}
