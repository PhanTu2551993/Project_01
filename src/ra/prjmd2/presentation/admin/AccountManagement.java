package ra.prjmd2.presentation.admin;

import ra.prjmd2.business.design.IAccount;
import ra.prjmd2.business.designImpl.AccountImpl;
import ra.prjmd2.business.untils.InputMethods;

import java.util.Scanner;

public class AccountManagement {
    public static void menuAccount(){
        AccountImpl account = new AccountImpl();
        do {
            System.out.println("**********************ACCOUNT-MANAGEMENT************************");
            System.out.println("1. Hiển thị danh sách");
            System.out.println("2. Thêm mới tài khoản");
            System.out.println("3. Tìm kiếm");
            System.out.println("4. Đổi trạng thái user");
            System.out.println("5. Quay lai");
            System.out.println("Mời lựa chọn (1/2/3/4/5): ");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    account.displayAll();
                    break;
                case 2:
                    account.create();
                    break;
                case 3:
                    account.searchAccountByName();
                    break;
                case 4:
                    account.changeStatusUser();
                    break;
                case 5:
                    return;
                default:
                    System.err.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
        } while (true);
    }
}
