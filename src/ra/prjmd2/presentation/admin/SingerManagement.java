package ra.prjmd2.presentation.admin;

import ra.prjmd2.business.designImpl.AccountImpl;
import ra.prjmd2.business.designImpl.SingerImpl;
import ra.prjmd2.business.untils.InputMethods;

import java.util.Scanner;

public class SingerManagement {
    public static void menuSinger(){
        SingerImpl singer = new SingerImpl();
        do {
            System.out.println("**********************SINGER-MANAGEMENT************************");
            System.out.println("1. Hiển thị danh sách");
            System.out.println("2. Thêm ca sĩ");
            System.out.println("3. Sửa ca sĩ");
            System.out.println("4. Xóa ca sĩ");
            System.out.println("5. Tìm kiếm ");
            System.out.println("6. Quay lai");
            System.out.println("Mời lựa chọn (1/2/3/4/5/6): ");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    singer.displayAll();
                    break;
                case 2:
                    singer.create();
                    break;
                case 3:
                    singer.update();
                    break;
                case 4:
                    singer.delete();
                    break;
                case 5:
                    singer.searchSingerByName();
                    break;
                case 6:
                    return;
                default:
                    System.err.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
        } while (true);
    }
}
