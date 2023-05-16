package menu_ct;

import java.io.*;
import java.util.ArrayList;

public class Writer {

    public static void writeText(ArrayList arrayList) throws IOException {
        ArrayList<String> list = arrayList;
        String url = "D:\\Zero\\CodeGym\\Module_2\\src\\menu_ct\\data\\user.csv";
        BufferedWriter writer = new BufferedWriter(new FileWriter(url));
        int i = 0;
        while (i < list.size()) {
            writer.write(list.get(i));
            writer.newLine();
            i++;
        }
        writer.close();
    }
}
