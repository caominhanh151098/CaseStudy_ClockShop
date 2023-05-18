package input;

import java.util.Date;

public class Continue {
    public static boolean continueInput(String input) {
        if (input.equals("0")) {
            System.out.print("Bạn có muốn thoát tác vụ này?? (y/n): ");
            if (InputData.choice())
                return true;
            else return false;
        }
        return false;
    }

    public static boolean continueInput(Date date) {
        if (date.equals(new Date(0))) {
            System.out.print("Bạn có muốn thoát tác vụ này?? (y/n): ");
            if (InputData.choice())
                return true;
            else return false;
        }
        return false;
    }
}
