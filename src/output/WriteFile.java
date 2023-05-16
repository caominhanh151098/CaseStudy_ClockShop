package output;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteFile {
    public static <T> void editData(ArrayList<T> listItem, String path) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            for (T item : listItem) {
                writer.write(item.toString());
                writer.newLine();
            }
            writer.close();
        } catch (
                FileNotFoundException e) {
            System.out.println("Error! Không thấy file data!");
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }
}
