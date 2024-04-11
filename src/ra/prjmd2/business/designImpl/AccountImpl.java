package ra.prjmd2.business.designImpl;
import org.mindrot.jbcrypt.BCrypt;
import ra.prjmd2.business.design.IAccount;
import ra.prjmd2.business.entity.User;
import ra.prjmd2.business.untils.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
public class AccountImpl implements IAccount {
    UserImpl user = new UserImpl();

    @Override
    public void create() {
        System.out.println("Bạn muốn thêm bao nhiêu tài khoản ADMIN hay USER");
        System.out.println("1. Thêm mới tài khoản ADMIN");
        System.out.println("2. Thêm mới tài khoản USER");
        byte choice = InputMethods.getByte();
            switch (choice) {
                case 1 :
                System.out.println("Bạn muốn thêm mới bao nhiêu ADMIN");
                int n1 = InputMethods.getInteger();
                for (int i = 0; i < n1; i++) {
                    System.out.println("Nhập thông tin cho tài khoản thứ "+(i+1));
                    User admin = new User();
                    admin.setUserId(getNewId());
                    System.out.println("ID : "+admin.getUserId());
                    System.out.println("Nhập username ");
                    while (true){
                        String username = InputMethods.getString();
                        if (ShopValidate.checkUserNameIsValid(username)){
                            if (!existByUsername(username)){
                                admin.setUsername(username);
                                break;
                            }
                            System.err.println(ShopMessage.ERROR_USERNAME_EXIST);
                        }else {
                            System.err.println(ShopMessage.ERROR_USERNAME_INVALID);
                        }
                    }
                    System.out.println("Nhâp password");
                    String pass = InputMethods.getString();
                    admin.setPassword(BCrypt.hashpw(pass,BCrypt.gensalt(5)));
                    System.out.println("Nhâp fullname");
                    admin.setFullname(InputMethods.getString());
                    System.out.println("Nhâp Email");
                    admin.setEmail(InputMethods.getString());
                    System.out.println("Nhâp số điện thoại");
                    admin.setPhone(InputMethods.getString());
                    admin.setRole(true);
                    List<User> userList = IOFile.readFromFile(ShopConstants.USER_PATH);
                    userList.add(admin);
                    IOFile.writeToFile(ShopConstants.USER_PATH,userList);
                }
                break;
                case 2 :
                System.out.println("Bạn muốn thêm mới bao nhiêu USER");
                int n2 = InputMethods.getInteger();
                for (int i = 0; i < n2; i++) {
                    System.out.println("Nhập thông tin cho tài khoản thứ "+(i+1));
                    User addUser = user.inputData();
                    List<User> userList = IOFile.readFromFile(ShopConstants.USER_PATH);
                    userList.add(addUser);
                    IOFile.writeToFile(ShopConstants.USER_PATH,userList);
                }
                break;
            }


        }

    @Override
    public void update() {
    }
    @Override
    public void delete() {

    }
    @Override
    public void displayAll() {
        List<User> userList = IOFile.readFromFile(ShopConstants.USER_PATH);
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("| ID |  Tên đăng nhập  | Vai trò |                    Email                 |  Số điện thoại  | VIP | Thời gian đăng ký | Cập nhật gần nhất |  Ví tiền  |  Trạng thái  |");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        for (User user : userList) {
            System.out.printf("| %-2d | %-15s |  %-6s |            %-20s          |    %-10s   |  %-2d |     %-10s    |     %-10s    | %-9.3f |    %-7s   |\n"
                            ,user.getUserId(),user.getUsername(), user.getRole()?"Admin":"User",user.getEmail(),user.getPhone()
                           ,user.getAccountType(),user.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    user.getUpdatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),user.getWallet(),user.getStatus()?"Active":"Block");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    @Override
    public Object inputData() {
        return null;
    }

    @Override
    public int getNewId() {
        List<User> userList = IOFile.readFromFile(ShopConstants.USER_PATH);
        int idMax = 0;
        for (User u : userList){
            if (u.getUserId() > idMax){
                idMax = u.getUserId();
            }
        }
        return idMax+1;
    }

    @Override
    public boolean existByUsername(String username) {
        List<User> userList = IOFile.readFromFile(ShopConstants.USER_PATH);
        return userList.stream().anyMatch(t -> t.getUsername().equals(username));
    }

    @Override
    public void searchAccountByName() {
        List<User> userList = IOFile.readFromFile(ShopConstants.USER_PATH);
        System.out.print("Nhập tên tài khoản cần tìm kiếm: ");
        String searchName = InputMethods.getString();
        boolean found = false;
        for (User user2 : userList){
            if (user2.getUsername().equalsIgnoreCase(searchName)) {
                user2.displayData();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy tài khoản nào có tên là \"" + searchName + "\"");
        }
    }

    @Override
    public void changeStatusUser() {
        List<User> userList = IOFile.readFromFile(ShopConstants.USER_PATH);
        System.out.println("Nhập vào ID cần cập nhật trạng thái:");
        int userId = InputMethods.getInteger();
        boolean found = false;
        for (User user : userList) {
            if (user.getUserId() == userId) {
                user.setStatus(!user.getStatus());
                user.setUpdatedAt(LocalDate.now());
                found = true;
                break;
            }
        }
        if (!found) {
            System.err.println("ID người dùng không tồn tại");
        } else {
            System.out.println("Đã cập nhật trạng thái tài khoản có ID " +userId);
            IOFile.writeToFile(ShopConstants.USER_PATH, userList);
        }
    }
}
