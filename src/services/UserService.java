package services;

import input.CovertDate;
import model.Account;
import output.WriteFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;

public class UserService {
    private static ArrayList<Account> accountList = new ArrayList<>();
    private String path = "data\\user.csv";

    public ArrayList<Account> getUserList() {
        accountList.clear();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            while (line != null) {
                String[] user = line.split(",");
                long id = Long.parseLong(user[0]);
                String username = user[1];
                String password = user[2];
                String name = user[3];
                int role = Integer.parseInt(user[4]);
                Date dob = CovertDate.covertDate(user[5]);
                String address = user[6];
                String email = user[7];
                String phoneNum = user[8];
                Account accountInfo = new Account()
                        .setId(id)
                        .setUsername(username)
                        .setPassword(password)
                        .setName(name)
                        .setRote(role)
                        .setDob(dob)
                        .setAddress(address)
                        .setEmail(email)
                        .setNumPhone(phoneNum);
                accountList.add(accountInfo);
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error! Không thấy file data!");
        } catch (Exception e) {
            System.out.println("Error 404! Lỗi dữ liệu!");
        }
        return accountList;
    }

    public void addUser(Account newAccount) {
        getUserList();
        accountList.add(newAccount);
        WriteFile.editData(accountList, path);
    }

    public void editUser(int index, Account editAccount) {
        getUserList();
        accountList.set(index, editAccount);
        WriteFile.editData(accountList, path);
    }

    public void editUser(Account editAccount) {
        getUserList();
        int index = 0;
        for (Account account : accountList) {
            if (account.getId() == editAccount.getId()) {
                accountList.set(index,editAccount);
                break;
            } else index++;
        }
        WriteFile.editData(accountList, path);
    }

    public void deleteUser(int index) {
        getUserList();
        accountList.remove(index);
        WriteFile.editData(accountList, path);
    }

    public Account findUserByID(long id) {
        getUserList();
        for (Account account : accountList) {
            if (account.getId() == id) {
                return account;
            }
        }
        return null;
    }

    public boolean contain(String username) {
        getUserList();
        for (Account account : accountList) {
            if (account.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean containEmail(String email) {
        getUserList();
        for (Account account : accountList) {
            if (account.getEmail().equals(email))
                return true;
        }
        return false;
    }
}



