package ra.prjmd2.presentation.run;

import ra.prjmd2.business.design.IUser;
import ra.prjmd2.business.designImpl.UserImpl;
import ra.prjmd2.business.entity.User;
import ra.prjmd2.business.untils.InputMethods;
import ra.prjmd2.business.untils.ShopMessage;

import java.util.Scanner;
public class Main {
    private static IUser userIpml = new UserImpl();
    public static User userLogin = null;
    public static void main(String[] args) {
        while (true){
            System.out.println("--------------MP3.Zing.vn---------------");
            System.out.println("1. Trang chủ");
            System.out.println("2. Đăng nhập");
            System.out.println("3. Đăng kí");
            System.out.println("4. Thóat");
            System.out.println("Nhap lưa chọn");
            byte choice   = InputMethods.getByte();
            switch (choice){
                case 1:
                    break;
                    case 2:
                        userIpml.login();
                    break;
                    case 3:
                        System.out.println("========Đăng ký tài khoản===========");
                        userIpml.register();
                    break;
                    case 4:
                    break;
                default:
                    System.err.println(ShopMessage.ERROR_ALERT);
            }

        }
    }
}
