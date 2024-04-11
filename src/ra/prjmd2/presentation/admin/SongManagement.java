package ra.prjmd2.presentation.admin;

import ra.prjmd2.business.designImpl.SongImpl;
import ra.prjmd2.business.untils.InputMethods;

import java.util.Scanner;

public class SongManagement {

    public static void menuSong(){
        SongImpl song = new SongImpl();
        do {
            System.out.println("**********************SONG-MANAGEMENT************************");
            System.out.println("1. Hiển thị danh sách");
            System.out.println("2. Thêm mới");
            System.out.println("3. Sửa bài hát");
            System.out.println("4. Xóa bài hát theo mã ID");
            System.out.println("5. Tìm kiếm bài hát theo tên");
            System.out.println("6. Quay lai");
            System.out.println("Mời lựa chọn (1/2/3/4/5/6): ");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    song.displayAll();
                    break;
                case 2:
                    song.create();
                    break;
                case 3:
                    song.update();
                    break;
                case 4:
                    song.delete();
                    break;
                case 5:
                    song.searchSongByName();
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