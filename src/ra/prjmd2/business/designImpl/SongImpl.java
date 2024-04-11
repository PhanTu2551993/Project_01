package ra.prjmd2.business.designImpl;

import ra.prjmd2.business.design.ISong;
import ra.prjmd2.business.entity.Album;
import ra.prjmd2.business.entity.Singer;
import ra.prjmd2.business.entity.Song;
import ra.prjmd2.business.entity.User;
import ra.prjmd2.business.untils.IOFile;
import ra.prjmd2.business.untils.InputMethods;
import ra.prjmd2.business.untils.ShopConstants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class SongImpl implements ISong {
    @Override
    public void create() {
        SongImpl songImpl = new SongImpl();
        System.out.println("Nhập số lượng bài hát muốn thêm :");
        int n = InputMethods.getInteger();
        for (int i = 0; i < n; i++) {
            System.out.println("NHập thông tin cho bài hát thứ "+(i+1));
            Song addSong = songImpl.inputData();
            addSong.setCreatedAt(new Date());
            List<Song> songList = IOFile.readFromFile(ShopConstants.SONG_PATH);
            songList.add(addSong);
            IOFile.writeToFile(ShopConstants.SONG_PATH,songList);
        }

    }

    @Override
    public void update() {
        List<Song> songList = IOFile.readFromFile(ShopConstants.SONG_PATH);
        List<Singer> singerList = IOFile.readFromFile(ShopConstants.SINGER_PATH);
        List<Album> albumList = IOFile.readFromFile(ShopConstants.ALBUM_PATH);
        System.out.println("Nhập vào ID bài hát cần cập nhật:");
        int inputId = InputMethods.getInteger();
        Song songUpdate = findById(inputId);
        if (songUpdate != null) {
            boolean isExit = true;
            do {
                System.out.println("1. Cập nhật ID Ca Sĩ");
                System.out.println("2. Cập nhật tên bài hát");
                System.out.println("3. Cập nhật mô tả bài hát");
                System.out.println("4. Cập nhật link bài hát");
                System.out.println("5. Cập nhật giá tiền bài hát(đơn vị : trăm ngàn đồng : 00000)");
                System.out.println("6. Cập nhật hình ảnh bài hát");
                System.out.println("7. Cập nhật mã Album");
                System.out.println("8. Thoát");
                System.out.println("Lựa chọn của bạn:");
                byte choice = InputMethods.getByte();
                switch (choice) {
                    case 1:
                        System.out.println("Danh sach Ca Sĩ");
                        for (Singer singer : singerList) {
                            System.out.printf("| ID : %d | Tên Ca Sĩ : %-15s |\n",singer.getSingerId(),singer.getSingerName());
                        }
                        while (true){
                            System.out.println("Nhập vào ID  của Ca Sĩ mới");
                            int inputIdNew = InputMethods.getInteger();
                            if (inputIdNew >= 0 ){
                                songUpdate.setSingerId(inputIdNew);
                                break;
                            }
                            System.err.println("ID nhập không hơp lệ, vui lòng chọn lại");
                        }
                        break;
                    case 2:
                        System.out.println("Nhập tên bài hát mới");
                        songUpdate.setSongName(InputMethods.getString());
                        break;
                    case 3:
                        System.out.println("Nhập mô tả bài hát mới");
                        songUpdate.setDescription(InputMethods.getString());
                        break;
                    case 4:
                        System.out.println("Nhập link bài hát mới");
                        songUpdate.setSource(InputMethods.getString());
                        break;
                    case 5:
                        System.out.println("Nhập giá tiền bài hát mới");
                        songUpdate.setPrice(InputMethods.getDouble());
                        break;
                    case 6:
                        System.out.println("Nhập ảnh bài hát mới");
                        songUpdate.setImage(InputMethods.getString());
                        break;
                    case 7:
                        System.out.println("Danh sach Album");
                        for (Album album : albumList) {
                            System.out.printf("| ID : %d | Tên Album : %-15s |\n",album.getId(),album.getName());
                        }
                        while (true){
                            System.out.println("Nhập vào ID  của Album mới");
                            int inputIdAlbum = InputMethods.getInteger();
                            if (inputIdAlbum >= 0 ){
                                songUpdate.setAlbumId(inputIdAlbum);
                                break;
                            }
                            System.err.println("ID nhập không hơp lẹ, vui lòng chọn lại");
                        }
                        break;
                    case 8:
                        isExit = false;
                        break;
                    default:
                        System.err.println("Vui lòng chọn từ 1-8");
                }
                songList.set(findIndexById(inputId),songUpdate);
                IOFile.writeToFile(ShopConstants.SONG_PATH,songList);
                System.out.println("Cập nhật thành công");
            } while (isExit);
        } else {
            System.err.println("ID bài hát không tồn tại");
        }
    }

    @Override
    public void delete() {
        List<Song> songList = IOFile.readFromFile(ShopConstants.SONG_PATH);
        System.out.println("Nhập ID bài hát muốn xóa ");
        int inputId = InputMethods.getInteger();
        Song deleteId = findById(inputId);
        if (deleteId == null){
            System.err.println("ID không tim thấy");
            return;
        }
        songList.remove(deleteId);
        IOFile.writeToFile(ShopConstants.SONG_PATH,songList);
        System.out.println("Đã xóa bài hát có ID " + inputId + " thành công");
    }

    @Override
    public void displayAll() {
        List<Song> songList = IOFile.readFromFile(ShopConstants.SONG_PATH);
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("| ID |   Tên bài hát   | Ca Sĩ |             Mô Tả             |          Source          | Lượt nghe | Thời gian được tạo | Cập nhật gần nhất |  Giá tiền  |  Mã Album  |");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        for (Song song : songList) {
            System.out.printf("| %-2d | %-15s |   %-3d |         %-15s       |        %-14s   |  %-5d | Thời gian đăng ký | Cập nhật gần nhất | %-5.5f |   %-5s|\n"
                    , song.getSongId(), song.getSongName(), song.getSingerId(), song.getDescription(), song.getSource()
                    , song.getPlayingCount(), song.getPrice(), song.getAlbumId());
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    @Override
    public Song inputData() {
        List<Singer> singerList = IOFile.readFromFile(ShopConstants.SINGER_PATH);
        List<Album> albumList = IOFile.readFromFile(ShopConstants.ALBUM_PATH);
        Song song =new Song();
        song.setSongId(getNewId());
        System.out.println("ID : "+song.getSongId());
        System.out.println("Nhập tên bài hát ");
        song.setSongName(InputMethods.getString());
        System.out.println("Danh sach Ca Sĩ");
        for (Singer singer : singerList) {
            System.out.printf("| ID : %d | Tên Ca Sĩ : %-15s |\n",singer.getSingerId(),singer.getSingerName());
        }
        while (true){
            System.out.println("Nhập vào ID  của Ca Sĩ");
            int inputId = InputMethods.getInteger();
            if (inputId >= 0){
                for (Singer singer : singerList) {
                    if (singer.getSingerId() == inputId) {
                        song.setSingerName(singer.getSingerName());
                        song.setSingerId(inputId);
                        break;
                    }
                }
                break;
            }
            System.err.println("ID nhập không hơp lẹ, vui lòng chọn lại");
        }
        System.out.println("Danh sach Album");
        for (Album album : albumList) {
            System.out.printf("| ID : %d | Tên Album : %-15s |\n",album.getId(),album.getName());
        }
        while (true){
            System.out.println("Nhập vào ID  của Album");
            int inputIdAlbum = InputMethods.getInteger();
            if (inputIdAlbum >= 0 ){
                for (Album album : albumList) {
                    if (album.getId() == inputIdAlbum) {
                        song.setAlbumName(album.getName());
                        song.setAlbumId(inputIdAlbum);
                        break;
                    }
                }
                break;
            }
            System.err.println("ID nhập không hơp lẹ, vui lòng chọn lại");
        }
        System.out.println("Nhập mô tả ");
        song.setDescription(InputMethods.getString());
        System.out.println("Nhập Source bài hát");
        song.setSource(InputMethods.getString());
        return song;
    }

    @Override
    public int getNewId() {
        List<Song> songList = IOFile.readFromFile(ShopConstants.SONG_PATH);
        int idMax = 0;
        for (Song song : songList){
            if (song.getSongId() > idMax){
                idMax = song.getSongId();
            }
        }
        return idMax+1;

    }

    @Override
    public Song findById(int inputId) {
        List<Song> songList = IOFile.readFromFile(ShopConstants.SONG_PATH);
        for (Song song : songList){
            if (song.getSongId() == inputId){
                return song;
            }
        }
        return null;
    }

    @Override
    public void searchSongByName() {
        List<Song> songList = IOFile.readFromFile(ShopConstants.SONG_PATH);
        System.out.println("Nhập tên bài cần tìm ");
        String searchName = InputMethods.getString();
        boolean found = false;
        for (Song song : songList){
            if (song.getSongName().equalsIgnoreCase(searchName)){
                song.displayData();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy bài hát nào có tên là \"" + searchName + "\"");
        }
    }

    public int findIndexById(int songId){
        List<Song> songList = IOFile.readFromFile(ShopConstants.SONG_PATH);
        for (int i = 0; i < songList.size(); i++) {
            if (songList.get(i).getSongId() == songId){
                return i;
            }
        }
        return -1;
    }
}
