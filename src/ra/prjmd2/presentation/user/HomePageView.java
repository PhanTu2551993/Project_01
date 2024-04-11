package ra.prjmd2.presentation.user;

import ra.prjmd2.business.designImpl.SongImpl;
import ra.prjmd2.business.designImpl.UserImpl;
import ra.prjmd2.business.untils.InputMethods;
import ra.prjmd2.presentation.run.Main;

public class HomePageView {
    public  static  void showHomePage(){
        UserImpl user = new UserImpl();
        System.out.println("HOME PAGE");
        System.out.printf("Chào mừng bạn %s đến với trang web MP3.Zing.vn\n", Main.userLogin.getFullname());
            do {
                System.out.println("****************************MP3.ZING.VN*************************************");
                System.out.println("1. Tìm kiếm bài hát/ca sĩ/album");
                System.out.println("2. Hiển thị bài hát");
                System.out.println("3. Hiển thị bài hát trending");
                System.out.println("4. Hiển thị ca sĩ trending");
                System.out.println("5. Hiển thị album trending");
                System.out.println("6. Quay lai");
                System.out.println("Mời lựa chọn (1/2/3/4/5/6): ");
                byte choice = InputMethods.getByte();
                switch (choice) {
                    case 1:
                        user.searchSingSongAlbum();
                        break;
                    case 2:
                        user.displayAll();
                        break;
                    case 3:

                        break;
                    case 4:

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
