package input;

import model.Account;
import model.Product;
import services.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class InputData {
    public static Scanner scanner = new Scanner(System.in);
    static final int ROLE_MANAGER = 0;
    static final int ROLE_STAFF = 1;
    static final char CHOICE_YES = 'y';
    static final char CHOICE_NO = 'n';
    static UserService userService = new UserService();
    static ArrayList<Account> accountList;

    public static String loginUsername() {
        String username;
        do {
            System.out.print("Nhập Username(*): ");
            username = scanner.nextLine();
            if (username.equals("")) {
                System.out.println("Error! Username không được rỗng!");
                continue;
            }
            if (username.equals("0"))
                return username;
            if (!Validate.checkAccount(username))
                System.out.println("Error! Username không phù hợp! (Ít nhất có 6 ký tự gồm chữ thường và số)");
        } while (username.equals("") || !Validate.checkAccount(username));
        return username;
    }

    public static String getUsername() {
        String username;
        do {
            System.out.print("Nhập Username(*): ");
            username = scanner.nextLine();
            if (username.equals("")) {
                System.out.println("Error! Username không được rỗng!");
                continue;
            }
            if (username.equals("0"))
                return username;
            if (!Validate.checkAccount(username)) {
                System.out.println("Error! Username không phù hợp! (Ít nhất có 6 ký tự gồm chữ thường và số, bắt đầu bằng chữ thường)");
                continue;
            }
            if (userService.contain(username))
                System.out.println("Username đã tồn tại! Xin nhập lại!");
        } while (username.equals("") || !Validate.checkAccount(username) || userService.contain(username));
        return username;
    }

    public static String getPassword() {
        String password;
        do {
            System.out.print("Nhập Password(*): ");
            password = scanner.nextLine();
            if (password.equals("")) {
                System.out.println("Error! Password không được rỗng!");
                continue;
            }
            if (password.equals("0"))
                return password;
            if (!Validate.checkPassword(password)) {
                System.out.println("Error! Password không phù hợp! (Ít nhất có 6 ký tự gồm chữ và số)");
            }
        } while (password.equals("") || !Validate.checkPassword(password));
        return password;
    }

    public static String getName() {
        String name;
        do {
            System.out.print("Nhập Name(*): ");
            name = scanner.nextLine();
            if (name.equals("")) {
                System.out.println("Error! Name không được rỗng!");
                continue;
            }
            if (name.length() > 100) {
                System.out.println("Error! Name quá dài!");
                continue;
            }
            if (name.equals("0"))
                return name;
            if (!Validate.checkName(name.toLowerCase()))
                System.out.println("Error! Name không phù hợp! (Ít nhất 2 từ và cách nhau bởi dấu trắng)");
        } while (name.equals("") || name.length() > 100 || !Validate.checkName(name.toLowerCase()));
        return name;
    }

    public static int getRole() {
        int role;
        do {
            System.out.print("Chọn Role (0: Manager, 1: Staff) : ");
            String number = scanner.nextLine();
            if (number.equals("")) role = ROLE_STAFF;
            else role = Integer.parseInt(number);
            if (role != ROLE_MANAGER && role != ROLE_STAFF) System.out.println("Role không phù hợp! Mời nhập lại!");
        } while (role != ROLE_MANAGER && role != ROLE_STAFF);
        return role;
    }

    public static Date getDate() {
        String date;
        String testDate = null;
        boolean tryAgain = false;
        do {
            System.out.print("Nhập ngày sinh(*) (dd-MM-YYYY): ");
            date = scanner.nextLine();
            if (date.equals("")) {
                System.out.println("Error! Ngày Không được rỗng!");
                tryAgain = true;
                continue;
            }
            if (date.equals("0")) {
                return new Date(0);
            }
            if (!Validate.checkDate(date)) {
                System.out.println("Ngày không phù hợp! Mời nhập lại!");
                tryAgain = true;
                continue;
            }
            Date result = null;
            try {
                result = new SimpleDateFormat("dd-MM-yyyy").parse(date);
                if (result.compareTo(new Date()) >= 0) {
                    System.out.println("Ngày không phù hợp! Mời nhập lại!");
                    tryAgain = true;
                    continue;
                }
            } catch (ParseException e) {
                System.out.println("Lỗi định dạng!!");
            }
            testDate = new SimpleDateFormat("dd-MM-yyyy").format(result);
            if (!testDate.equals(date)) {
                System.out.println("Ngày không phù hợp! Mời nhập lại!");
                tryAgain = true;
            } else return result;
        } while (tryAgain);
        return new Date();
    }

    public static String getAddress() {
        String address;
        do {
            System.out.print("Nhập địa chỉ: ");
            address = scanner.nextLine();
            if (address.equals(""))
                return address;
            if (address.equals("0"))
                return address;
            if (!Validate.checkString(address.toLowerCase()))
                System.out.println("Địa chỉ không nên có ký tự đặt biệt! Mời nhập lại!");
        } while (!Validate.checkString(address.toLowerCase()));
        return address;
    }

    public static String getEmail() {
        String email;
        do {
            System.out.print("Nhập Email: ");
            email = scanner.nextLine();
            if (email.equals("")) return email;
            if (email.equals("0")) return email;
            if (!Validate.checkEmail(email)) {
                System.out.println("Email không phù hợp! Mời nhập lại! (vd:email@g.com)");
                continue;
            }
            if (userService.containEmail(email))
                System.out.println("Email đã có người dùng! Mời nhập lại!");
        } while (email.equals("") || !Validate.checkEmail(email) || userService.containEmail(email));
        return email;
    }

    public static String getPhoneNum() {
        String phoneNum;
        do {
            System.out.print("Nhập số điện thoại(*) (0xxxxxxxx): ");
            phoneNum = scanner.nextLine();
            if (phoneNum.equals("")) {
                System.out.println("Số điện thoại không được rỗng! Mời nhập lại!");
                continue;
            }
            if (phoneNum.equals("0"))
                return phoneNum;
            if (!Validate.checkPhoneNum(phoneNum))
                System.out.println("Số điện thoại không phù hợp! Mời nhập lại!");
        } while (phoneNum.equals("") || !Validate.checkPhoneNum(phoneNum));
        return phoneNum;
    }

    public static int getIndex(ArrayList list) {
        int index = 0;
        accountList = list;
        do {
            String number = scanner.nextLine();
            if (number.equals("")) {
                System.out.print("Error! Không được rỗng! Yêu cầu nhập lại: ");
                continue;
            }
            try {
                index = Integer.parseInt(number);
            } catch (NumberFormatException e) {
                index = 0;
            }
            if (index < 1 || index > accountList.size()) {
                System.out.print("Error! Lỗi nhập STT! Yêu cầu nhập lại: ");
            }
        } while (index < 1 || index > accountList.size());
        return index - 1;
    }

    public static String getNameProduct() {
        String name;
        do {
            System.out.print("Nhập Name(*): ");
            name = scanner.nextLine();
            if (name.equals("")) {
                System.out.println("Error! Name không được rỗng!");
                continue;
            }
            if (name.length() > 100) {
                System.out.println("Error! Name quá dài!");
                continue;
            }
            if (name.equals("0"))
                return name;
            if (!Validate.checkString(name.toLowerCase()))
                System.out.println("Error! Name không phù hợp! (Có ký tự đặc biệt!)");
        } while (name.equals("") || name.length() > 100 || !Validate.checkString(name.toLowerCase()));
        return name;
    }

    public static String getBrand() {
        String brand;
        do {
            System.out.print("Nhập Brand: ");
            brand = scanner.nextLine();
            if (brand.equals("")) {
                System.out.println("Error! Brand không được rỗng!");
                continue;
            }
            if (!Validate.checkString(brand.toLowerCase()))
                System.out.println("Tên hãng không phù hợp! Mời nhập lại! (Không có ký tự đặt biệt)");
        } while (brand.equals("") || !Validate.checkString(brand.toLowerCase()));
        return brand;
    }

    public static int getQuantity() {
        int quantity = 0;
        do {
            System.out.print("Nhập Quantity: ");
            String number = scanner.nextLine();
            if (number.equals(""))
                break;
            try {
                quantity = Integer.parseInt(number);
            } catch (NumberFormatException e) {
                quantity = -1;
            }
            if (quantity < 0)
                System.out.println("Error! Nhập không phù hợp!");
        } while (quantity < 0);
        return quantity;
    }

    public static int getPrice() {
        int price = 0;
        do {
            System.out.print("Nhập Price: ");
            String number = scanner.nextLine();
            if (number.equals(""))
                break;
            try {
                price = Integer.parseInt(number);
            } catch (NumberFormatException e) {
                price = -1;
            }
            if (price < 0)
                System.out.println("Error! Nhập không phù hợp!");
        } while (price < 0);
        return price;
    }

    public static int getQuantityBuy(Product product) {
        int quantity = 0;
        do {
            System.out.print("Nhập số lượng bạn cần mua: ");
            String number = scanner.nextLine();
            if (number.equals("")) {
                System.out.println("Error! Không được rỗng! Yêu cầu nhập lại: ");
                continue;
            }
            try {
                quantity = Integer.parseInt(number);
            } catch (NumberFormatException e) {
                quantity = -1;
            }
            if (quantity == 0) {
                System.out.println("Lỗi! Số lượng phải lớn hơn 0");
                continue;
            }
            if (quantity < 0) {
                System.out.println("Lỗi! Nhập số lượng không phù hợp");
                continue;
            }
            if (quantity > product.getQuantity()) {
                System.out.println("Lỗi! Số lượng nhập lớn hơn số lượng trong kho hàng");
            }
        } while (quantity <= 0 || quantity > product.getQuantity());
        return quantity;
    }

    public static boolean choice() {
        Character answer = null;
        do {
            try {
                answer = scanner.nextLine().charAt(0);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Nhập rỗng! Mời nhập lại!");
                answer = 'o';
                continue;
            }
            answer = Character.toLowerCase(answer);
            if (answer == CHOICE_YES || answer == CHOICE_NO) {
                if (answer == CHOICE_YES) {
                    return true;
                } else {
                    return false;
                }
            } else System.out.println("Không phù hợp! Mời nhập lại!");

        } while (answer != CHOICE_YES && answer != CHOICE_NO);
        return false;
    }
}

