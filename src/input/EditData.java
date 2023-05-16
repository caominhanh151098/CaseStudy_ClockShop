package input;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class EditData {
    public static Scanner scanner = new Scanner(System.in);
    static final int ROLE_MANAGER = 0;
    static final int ROLE_STAFF = 1;

    public static String getPassword(String password) {
        String newPassword;
        do {
            System.out.print("Nhập Password(*): ");
            newPassword = scanner.nextLine();
            if (newPassword.equals("")) {
                return password;
            }
            if (Validate.checkPassword(newPassword) == false) {
                System.out.println("Error! Password không phù hợp!");
            }
        } while (Validate.checkPassword(newPassword) == false);
        return newPassword;
    }

    public static String getName(String name) {
        String newName;
        do {
            System.out.print("Nhập Name(*): ");
            newName = scanner.nextLine();
            if (newName.equals("")) return name;
            if (newName.length() > 100) System.out.println("Error! Name quá dài!");
        } while (newName.length() > 100);
        return newName;
    }

    public static int getRole(int role) {
        do {
            System.out.print("Chọn Role (0: Manager, 1: Staff) : ");
            String number = scanner.nextLine();
            if (number.equals("")) return role;
            else role = Integer.parseInt(number);
            if (role != ROLE_MANAGER && role != ROLE_STAFF) System.out.println("Role không phù hợp! Mời nhập lại!");
        } while (role != ROLE_MANAGER && role != ROLE_STAFF);
        return role;
    }

    public static Date getDate(String date) {
        String newDate;
        String testDate;
        do {
            System.out.print("Nhập ngày sinh(*) (dd-MM-YYYY): ");
            newDate = scanner.nextLine();
            if (newDate.equals("")) {
                try {
                    return new SimpleDateFormat("dd-MM-yyyy").parse(date);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
            if (Validate.checkDate(newDate) == false) System.out.println("Ngày không phù hợp! Mời nhập lại!");

            Date result = null;
            try {
                result = new SimpleDateFormat("dd-MM-yyyy").parse(newDate);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            testDate = new SimpleDateFormat("dd-MM-yyyy").format(result);
            if (!testDate.equals(newDate)) System.out.println("Ngày không phù hợp! Mời nhập lại!");
            else return result;
        } while (Validate.checkDate(newDate) == false || !testDate.equals(newDate));
        return new Date();
    }

    public static String getAddress(String address) {
        String newAddress;
        do {
            System.out.print("Nhập địa chỉ: ");
            newAddress = scanner.nextLine();
            if (newAddress.equals("")) return address;
            if (Validate.checkString(newAddress) == false)
                System.out.println("Địa chỉ không nên có ký tự đặt biệt! Mời nhập lại!");
        } while (Validate.checkString(newAddress) == false);
        return newAddress;
    }

    public static String getEmail(String email) {
        String newEmail;
        do {
            System.out.print("Nhập Email: ");
            newEmail = scanner.nextLine();
            if (newEmail.equals("")) return email;
            if (Validate.checkEmail(newEmail) == false) System.out.println("Email không phù hợp! Mời nhập lại!");
        } while (Validate.checkEmail(newEmail) == false);
        return newEmail;
    }

    public static String getPhoneNum(String phoneNum) {
        String newPhoneNum;
        do {
            System.out.print("Nhập số điện thoại(*) (0xxxxxxxx): ");
            newPhoneNum = scanner.nextLine();
            if (newPhoneNum.equals("")) return phoneNum;
            if (Validate.checkPhoneNum(newPhoneNum) == false)
                System.out.println("Số điện thoại không phù hợp! Mời nhập lại!");
        } while (Validate.checkPhoneNum(newPhoneNum) == false);
        return newPhoneNum;
    }

    public static String getBrand(String brand) {
        String newBrand;
        do {
            System.out.print("Nhập Brand: ");
            newBrand = scanner.nextLine();
            if (newBrand.equals("")) return brand;
        } while (newBrand.equals(""));
        return newBrand;
    }

    public static int getQuantity(int quantity) {
        do {
            System.out.print("Nhập Quantity: ");
            String number = scanner.nextLine();
            if (number.equals("")) return quantity;
            try {
                quantity = Integer.parseInt(number);
            } catch (NumberFormatException e) {
                quantity = -1;
            }
            if (quantity < 0) System.out.println("Error! Nhập không phù hợp!");
        } while (quantity < 0);
        return quantity;
    }

    public static int getPrice(int price) {
        do {
            System.out.print("Nhập Price: ");
            String number = scanner.nextLine();
            if (number.equals("")) return price;
            try {
                price = Integer.parseInt(number);
            } catch (NumberFormatException e) {
                price = -1;
            }
            if (price < 0) System.out.println("Error! Nhập không phù hợp!");
        } while (price < 0);
        return price;
    }
}

