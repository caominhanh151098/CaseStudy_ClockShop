package menu_ct;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Reader {
    public static ArrayList<String> readText() throws IOException {
        String url = "D:\\Zero\\CodeGym\\Module_2\\src\\menu_ct\\data\\user.csv";

        BufferedReader reader = new BufferedReader(new FileReader(url));
        ArrayList<String> list = new ArrayList<>();

        String line = reader.readLine();
        while (line != null) {
            list.add(line);
//            String[] user = line.split("/");
//            int ID = Integer.parseInt(user[0]);
//            String userName = user[1];
//            String passWord = user[2];
//            System.out.printf("ID: %s, User: %s, Pass: %s %n", ID, userName, passWord);
            line = reader.readLine();
        }
        reader.close();
        return list;
    }
}
