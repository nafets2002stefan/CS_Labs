package Lab_4;

import java.util.ArrayList;
import java.util.List;

public class DB {
    private final List<String> passwords = new ArrayList<>();

    public void addToDB(String password){
        passwords.add(password);
    }

    public boolean checkDbIfContains(String password){
        return passwords.contains(password);
    }
}
