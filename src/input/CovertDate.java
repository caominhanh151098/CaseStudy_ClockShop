package input;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CovertDate {
    public static Date covertDate(String textDate) throws ParseException {
        return new SimpleDateFormat("dd-MM-yyyy").parse(textDate);
    }
}
