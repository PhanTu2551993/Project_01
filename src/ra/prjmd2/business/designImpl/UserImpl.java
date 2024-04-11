package ra.prjmd2.business.designImpl;

import org.mindrot.jbcrypt.BCrypt;
import ra.prjmd2.business.design.IUser;
import ra.prjmd2.business.entity.Album;
import ra.prjmd2.business.entity.Singer;
import ra.prjmd2.business.entity.Song;
import ra.prjmd2.business.entity.User;
import ra.prjmd2.business.untils.*;
import ra.prjmd2.presentation.admin.*;
import ra.prjmd2.presentation.run.Main;
import ra.prjmd2.presentation.user.HomePageView;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class UserImpl implements IUser {
    @Override
    public void create() {

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }
    // Hiển thị tất cả bài hát
    @Override
    public void displayAll() {
        List<Song> songList = IOFile.readFromFile(ShopConstants.SONG_PATH);
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("| ID |   Tên bài hát   |       Ca Sĩ       |            Album             | Lượt nghe |            Mô Tả            | Thời gian đăng | Cập nhật gần nhất |  Giá tiền  |");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        for (Song song : songList) {
            System.out.printf("| %-2d |   %-13s |     %-13s |        %-16s      |    %-6d |           %-18s| Thời gian đăng ký | Cập nhật gần nhất | %-5.3f |\n"
                    , song.getSongId(), song.getSongName(), song.getSingerName(), song.getAlbumName(), song.getPlayingCount()
                    , song.getDescription(), song.getPrice());
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    @Override
    public User inputData() {
        User user = new User();
        user.setUserId(getNewId());//ID tự tăng
        System.out.println("ID : "+user.getUserId());
        System.out.println("Nhập username ");
        while (true){
            String username = InputMethods.getString();
            if (ShopValidate.checkUserNameIsValid(username)){
                // kiểm tra  trùng lặp
                if (!existByUsername(username)){
                    user.setUsername(username);
                    break;
                }
                System.err.println(ShopMessage.ERROR_USERNAME_EXIST);
            }else {
                System.err.println(ShopMessage.ERROR_USERNAME_INVALID);
            }
        }
        System.out.println("Nhâp password");
        String pass = InputMethods.getString();
        user.setPassword(BCrypt.hashpw(pass,BCrypt.gensalt(5)));
        System.out.println("Nhâp fullname");
        user.setFullname(InputMethods.getString());
        System.out.println("Nhâp Email");
        user.setEmail(InputMethods.getString());
        System.out.println("Nhâp số điện thoại");
        user.setPhone(InputMethods.getString());
        user.setCreatedAt(LocalDate.now());
        user.setUpdatedAt(LocalDate.now());
        System.out.println("Nhâp số tiền muốn nạp vào tài khoản (Đơn vị : Nghìn đồng)");
        double walletAmount = InputMethods.getDouble();
        if (walletAmount >= 100 && walletAmount < 200) {
            user.setAccountType(1);
        }else if (walletAmount >= 200 && walletAmount < 500) {
            user.setAccountType(2);
        }else if (walletAmount >= 500 && walletAmount < 1000) {
            user.setAccountType(3);
        }else if (walletAmount >= 1000 && walletAmount < 2000) {
            user.setAccountType(4);
        }else if (walletAmount >= 2000 ) {
            user.setAccountType(5);
        }
        user.setWallet(walletAmount);
        return user;
    }

    @Override
    public void searchSingSongAlbum() {

    }

    @Override
    public void login() {
        System.out.println("Nhập username");
        String username = InputMethods.getString();
        System.out.println("Nhập password");
        String password= InputMethods.getString();
        List<User> userList = IOFile.readFromFile(ShopConstants.USER_PATH);
        User userLogin = userList.stream().filter(u->u.getUsername().equals(username) && BCrypt.checkpw(password,u.getPassword())).findFirst().orElse(null);
        if (userLogin==null){
            System.err.println(ShopMessage.ERROR_ACCOUNT_NOTFOUND);
            System.out.println("Bạn có muốn nhập lại không ? (Y,N)");
            if (InputMethods.getChar()=='Y'){
                login();
            }
        }else {
           if (userLogin.getRole()) {
               Main.userLogin = userLogin;
               System.out.println("**********************Danh mục quản lý************************");
               System.out.println("1. Quản lý tài khoản");
               System.out.println("2. Quản lý Ca Sĩ");
               System.out.println("3. Quản lý bài hát");
               System.out.println("4. Quản lý Album");
               System.out.println("Mời lựa chọn (1/2/3/4): ");
               byte choice = InputMethods.getByte();
               switch (choice){
                   case 1 :
                       AccountManagement.menuAccount();
                   case 2 :
                       SingerManagement.menuSinger();
                   case 3 :
                       SongManagement.menuSong();
                   case 4 :
                       AlbumManagement.menuAlbum();
               }
           }else {
                    if (userLogin.isBlock()){
                        System.err.println(ShopMessage.ERROR_ACCOUNT_ISBLOCK);
                    }else {
                        System.out.println("Đăng nhập thành công");
                        Main.userLogin = userLogin;
                        HomePageView.showHomePage();
                    }
            }
        }

    }

    @Override
    public void register() {
        User user = inputData();
        List<User> userList = IOFile.readFromFile(ShopConstants.USER_PATH);
        userList.add(user);
        IOFile.writeToFile(ShopConstants.USER_PATH,userList);
        System.out.println("Đăng ký thành công");
        login();
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
}
