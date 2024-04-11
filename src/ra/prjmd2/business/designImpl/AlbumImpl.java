package ra.prjmd2.business.designImpl;

import ra.prjmd2.business.design.IAlbum;
import ra.prjmd2.business.entity.Album;
import ra.prjmd2.business.entity.Singer;
import ra.prjmd2.business.entity.Song;
import ra.prjmd2.business.untils.IOFile;
import ra.prjmd2.business.untils.InputMethods;
import ra.prjmd2.business.untils.ShopConstants;

import java.util.List;
import java.util.Scanner;

public class AlbumImpl implements IAlbum {
    @Override
    public void create() {
        AlbumImpl albumImpl = new AlbumImpl();
        System.out.println("Bạn muốn thêm mới bao nhiêu Album");
        int n = InputMethods.getInteger();
        for (int i = 0; i < n; i++) {
            System.out.println("Nhập thông tin cho Album thứ " + (i + 1));
            Album addAlbum = albumImpl.inputData();
            List<Album> albumList = IOFile.readFromFile(ShopConstants.ALBUM_PATH);
            albumList.add(addAlbum);
            IOFile.writeToFile(ShopConstants.ALBUM_PATH,albumList);
        }
    }

    @Override
    public void update() {
        List<Singer> singerList = IOFile.readFromFile(ShopConstants.SINGER_PATH);
        List<Album> albumList = IOFile.readFromFile(ShopConstants.ALBUM_PATH);
        System.out.println("Nhập vào ID Album cần cập nhật:");
        int inputIdUpdate = InputMethods.getInteger();
        Album albumUpdate = findById(inputIdUpdate);
        if (albumUpdate != null) {
            boolean isExit = true;
            do {
                System.out.println("1. Cập nhật ID Ca Sĩ");
                System.out.println("2. Cập nhật tên Album");
                System.out.println("3. Cập nhật mô tả Album");
                System.out.println("4. Cập nhật hình ảnh Album");
                System.out.println("5. Thoát");
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
                                albumUpdate.setSingerId(inputIdNew);
                                break;
                            }
                            System.err.println("ID nhập không hơp lệ, vui lòng chọn lại");
                        }
                        break;
                    case 2:
                        System.out.println("Nhập tên Album mới");
                        albumUpdate.setName(InputMethods.getString());
                        break;
                    case 3:
                        System.out.println("Nhập mô tả Album mới");
                        albumUpdate.setDescription(InputMethods.getString());
                        break;
                    case 4:
                        System.out.println("Nhập ảnh album mới");
                        albumUpdate.setImage(InputMethods.getString());
                        break;
                    case 5:
                        isExit = false;
                        break;
                    default:
                        System.err.println("Vui lòng chọn từ 1-5");
                }
                albumList.set(findIndexById(inputIdUpdate),albumUpdate);
                IOFile.writeToFile(ShopConstants.ALBUM_PATH,albumList);
                System.out.println("Cập nhật thành công");
            } while (isExit);
        } else {
            System.err.println("ID Album không tồn tại");
        }

    }

    @Override
    public void delete() {
        List<Song> songList = IOFile.readFromFile(ShopConstants.SONG_PATH);
        List<Album> albumList = IOFile.readFromFile(ShopConstants.ALBUM_PATH);
        System.out.println("Nhập ID album cần xóa");
        int deleteID = InputMethods.getInteger();
        // Kiểm tra xem album có chứa bài hát không
        if (songList.stream().anyMatch(e->e.getAlbumId() == deleteID)){
            // có bài hát
            System.err.println("Album này đang có bài hát, không xóa được");
            return;
        }
        albumList.remove(deleteID);
        // xóa xong ghi lại vào IOfile
        IOFile.writeToFile(ShopConstants.ALBUM_PATH,albumList);
        System.out.println("Đã xóa album có ID " + deleteID + " thành công");
    }

    @Override
    public void displayAll() {
        List<Album> albumList = IOFile.readFromFile(ShopConstants.ALBUM_PATH);
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("| ID |    Tên Album    |  ID Ca Sĩ |                  Mô tả                  |  Image  |");
        System.out.println("----------------------------------------------------------------------------------------");
        for (Album album : albumList) {
            System.out.printf("| %-2d | %-15s |    %-4d    |           %-20s          |   %-5s   |\n"
                    , album.getId(), album.getName(), album.getSingerId(), album.getDescription(), album.getImage());
            System.out.println("----------------------------------------------------------------------------------------");
        }
    }

    @Override
    public Album inputData() {
        List<Singer> singerList = IOFile.readFromFile(ShopConstants.SINGER_PATH);
        Album album = new Album();
        album.setId(getNewId());
        System.out.println("ID : "+album.getId());
        System.out.println("Nhập tên Album");
        album.setName(InputMethods.getString());
        System.out.println("Danh sach Ca Sĩ");
        for (Singer singer : singerList) {
            System.out.printf("| ID : %d | Tên Ca Sĩ : %-15s |\n",singer.getSingerId(),singer.getSingerName());
        }
        while (true){
            System.out.println("Nhập vào ID  của Ca Sĩ");
            int inputId = InputMethods.getInteger();
            if (inputId >= 0 ){
                album.setSingerId(inputId);
                break;
            }
            System.err.println("ID nhập không hơp lẹ, vui lòng chọn lại");
        }
        System.out.println("Nhập mô tả ");
        album.setDescription(InputMethods.getString());
        System.out.println("Image Album");
        album.setImage(InputMethods.getString());
        return album;
    }

    @Override
    public int getNewId() {
        List<Album> albumList = IOFile.readFromFile(ShopConstants.ALBUM_PATH);
        int idMax = 0;
        for (Album album : albumList){
            if (album.getId() > idMax){
                idMax = album.getId();
            }
        }
        return idMax+1;
    }

    @Override
    public Album findById(int inputIdUpdate) {
        List<Album> albumList = IOFile.readFromFile(ShopConstants.ALBUM_PATH);
        for (Album album : albumList){
            if (album.getId() == inputIdUpdate){
                return album;
            }
        }
        return null;
    }

    @Override
    public void searchAlbumByName() {
        List<Album> albumList = IOFile.readFromFile(ShopConstants.ALBUM_PATH);
        System.out.println("Nhập tên Album cần tìm ");
        String searchName = InputMethods.getString();
        boolean found = false;
        for (Album album : albumList){
            if (album.getName().equalsIgnoreCase(searchName)){
                album.displayData();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy Album nào có tên là \"" + searchName + "\"");
        }
    }

    public int findIndexById(int albumId){
        List<Album> albumList = IOFile.readFromFile(ShopConstants.ALBUM_PATH);
        for (int i = 0; i < albumList.size(); i++) {
            if (albumList.get(i).getId() == albumId){
                return i;
            }
        }
        return -1;
    }

}
