package input;

import services.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class EditData {
    static Scanner scanner = new Scanner(System.in);
    static final int ROLE_MANAGER = 0;
    static final int ROLE_STAFF = 1;
    static UserService userService = new UserService();

    public static String getPassword(String password) {
        String newPassword;
        do {
            System.out.print("Nhập Password(*): ");
            newPassword = scanner.nextLine();
            if (newPassword.equals("")) {
                return password;
            }
            if (newPassword.equals("0")) {
                return newPassword;
            }
            if (!Validate.checkPassword(newPassword)) {
                System.out.println("Error! Password không phù hợp!");
            }
        } while (!Validate.checkPassword(newPassword));
        return newPassword;
    }

    public static String getName(String name) {
        String newName;
        do {
            System.out.print("Nhập Name(*): ");
            newName = scanner.nextLine();
            if (newName.equals("")) return name;
            if (newName.length() > 100) {
                System.out.println("Error! Name quá dài!");
                continue;
            }
            if (newName.equals("0")) return newName;
            if (!Validate.checkName(name.toLowerCase()))
                System.out.println("Error! Name không phù hợp! (Ít nhất 2 từ và cách nhau bởi dấu trắng)");
        } while (newName.length() > 100 || !Validate.checkName(name.toLowerCase()));
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
        String testDate = null;
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
            if (newDate.equals("0"))
                return new Date(0);
            if (!Validate.checkDate(newDate)) {
                System.out.println("Ngày không phù hợp! Mời nhập lại!");
                continue;
            }
            Date result = null;
            try {
                result = new SimpleDateFormat("dd-MM-yyyy").parse(newDate);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            testDate = new SimpleDateFormat("dd-MM-yyyy").format(result);
            if (!testDate.equals(newDate)) System.out.println("Ngày không phù hợp! Mời nhập lại!");
            else return result;
        } while (!Validate.checkDate(newDate) || !testDate.equals(newDate));
        return new Date();
    }

    public static String getAddress(String address) {
        String newAddress;
        do {
            System.out.print("Nhập địa chỉ: ");
            newAddress = scanner.nextLine();
            if (newAddress.equals("")) return address;
            if (newAddress.equals("0")) return newAddress;
            if (!Validate.checkString(newAddress.toLowerCase()))
                System.out.println("Địa chỉ không nên có ký tự đặt biệt! Mời nhập lại!");
        } while (!Validate.checkString(newAddress.toLowerCase()));
        return newAddress;
    }

    public static String getEmail(String email) {
        String newEmail;
        do {
            System.out.print("Nhập Email: ");
            newEmail = scanner.nextLine();
            if (newEmail.equals("")) return email;
            if (newEmail.equals("0")) return newEmail;
            if (!Validate.checkEmail(newEmail)) {
                System.out.println("Email không phù hợp! Mời nhập lại! (vd:email@g.com)");
                continue;
            }
            if (userService.containEmail(email))
                System.out.println("Email đã có người dùng! Mời nhập lại!");
        } while (!Validate.checkEmail(newEmail) || userService.containEmail(email));
        return newEmail;
    }

    public static String getPhoneNum(String phoneNum) {
        String newPhoneNum;
        do {
            System.out.print("Nhập số điện thoại(*) (0xxxxxxxx): ");
            newPhoneNum = scanner.nextLine();
            if (newPhoneNum.equals("")) return phoneNum;
            if (newPhoneNum.equals("0")) return newPhoneNum;
            if (!Validate.checkPhoneNum(newPhoneNum))
                System.out.println("Số điện thoại không phù hợp! Mời nhập lại!");
        } while (!Validate.checkPhoneNum(newPhoneNum));
        return newPhoneNum;
    }

    public static String getBrand(String brand) {
        String newBrand;
        do {
            System.out.print("Nhập Brand: ");
            newBrand = scanner.nextLine();
            if (newBrand.equals("")) return brand;
            if (newBrand.equals("0")) return newBrand;
            if (!Validate.checkString(newBrand.toLowerCase()))
                System.out.println("Tên hãng không phù hợp! Mời nhập lại! (Không có ký tự đặt biệt)");
        } while (newBrand.equals("") || !Validate.checkString(newBrand.toLowerCase()));
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

