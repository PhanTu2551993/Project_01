package ra.prjmd2.business.designImpl;

import ra.prjmd2.business.design.IAccount;
import ra.prjmd2.business.design.ISinger;
import ra.prjmd2.business.entity.Album;
import ra.prjmd2.business.entity.Singer;
import ra.prjmd2.business.entity.Song;
import ra.prjmd2.business.untils.IOFile;
import ra.prjmd2.business.untils.InputMethods;
import ra.prjmd2.business.untils.ShopConstants;
import java.util.List;

public class SingerImpl implements ISinger {

    @Override
    public void create() {
        SingerImpl singerImpl = new SingerImpl();
        System.out.println("Bạn muốn thêm mới bao nhiêu ca sĩ");
        int n1 = InputMethods.getInteger();
        for (int i = 0; i < n1; i++) {
            System.out.println("Nhập thông tin cho ca sĩ thứ " + (i + 1));
            Singer addSinger = singerImpl.inputData();
            List<Singer> singerList = IOFile.readFromFile(ShopConstants.SINGER_PATH);
            singerList.add(addSinger);
            IOFile.writeToFile(ShopConstants.SINGER_PATH,singerList);
        }
    }

    @Override
    public void update() {
        List<Singer> singerList = IOFile.readFromFile(ShopConstants.SINGER_PATH);
        System.out.println("Nhập vào ID Ca Sĩ cần cập nhật:");
        int inputId = InputMethods.getInteger();
        Singer singerUpdate = findById(inputId);
        if (singerUpdate != null) {
            boolean isExit = true;
            do {
                System.out.println("1. Cập nhật tên Ca Sĩ");
                System.out.println("2. Cập nhật mô tả");
                System.out.println("3. Cập nhật trang thái Ca Sĩ");
                System.out.println("4. Thoát");
                System.out.println("Lựa chọn của bạn:");
                byte choice = InputMethods.getByte();
                switch (choice) {
                    case 1:
                        System.out.println("Nhập tên Ca Sĩ mới");
                        singerUpdate.setSingerName(InputMethods.getString());
                        break;
                    case 2:
                        System.out.println("Nhập mô tả về Ca Sĩ mới");
                        singerUpdate.setDescription(InputMethods.getString());
                        break;
                    case 3:
                        System.out.println("Cập nhật trạng thái mới");
                        singerUpdate.setStatus(InputMethods.getBoolean());
                    case 4:
                        isExit = false;
                        break;
                    default:
                        System.err.println("Vui lòng chọn từ 1-4");
                }
                singerList.set(inputId,singerUpdate);
                IOFile.writeToFile(ShopConstants.SINGER_PATH, singerList);
                System.out.println("Cập nhật thành công");
            } while (isExit);
        } else {
            System.err.println("ID bài hát không tồn tại");
        }
    }

    @Override
    public void delete() {
        List<Singer> singerList = IOFile.readFromFile(ShopConstants.SINGER_PATH);
        List<Song> songList = IOFile.readFromFile(ShopConstants.SONG_PATH);
        List<Album> albumList = IOFile.readFromFile(ShopConstants.ALBUM_PATH);
        System.out.println("Nhập vào ID Ca Sĩ cần xóa ");
        int inputId = InputMethods.getInteger();
        if (songList.stream().anyMatch(e->e.getSingerId() == inputId)){
            // có bài hát
            System.err.println("Ca sĩ này đang có bài hát, không xóa được");
            return;
        }
        if (albumList.stream().anyMatch(e->e.getSingerId() == inputId)){
            // có album
            System.err.println("Ca sĩ này đang có album, không xóa được");
            return;
        }
        boolean found = false;
        for (Singer singer : singerList) {
            if (singer.getSingerId() == inputId) {
                found = true;
                singerList.remove(singer);
                System.out.println("Ca sĩ đã được xóa thành công.");
                break;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy ca sĩ có ID là " + inputId);
        } else {
            IOFile.writeToFile(ShopConstants.SINGER_PATH, singerList);
        }
    }

    @Override
    public void displayAll() {
        List<Singer> singerList = IOFile.readFromFile(ShopConstants.SINGER_PATH);
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("| ID |    Tên Ca Sĩ    |                  Mô tả                  |  Trạng thái  |");
        System.out.println("-----------------------------------------------------------------------------------");
        for (Singer singer : singerList) {
            System.out.printf("| %-2d | %-15s |            %-20s          | %-5s |\n"
                    , singer.getSingerId(), singer.getSingerName(), singer.getDescription(), singer.isStatus() ? "Active" : "Inactive");
            System.out.println("-----------------------------------------------------------------------------------");
        }

    }

    @Override
    public Singer inputData() {
        Singer singer = new Singer();
        singer.setSingerId(getNewId());
        System.out.println("ID : "+singer.getSingerId());
        System.out.println("Nhập tên Ca Sĩ ");
        singer.setSingerName(InputMethods.getString());
        System.out.println("Nhập mô tả ");
        singer.setDescription(InputMethods.getString());
        System.out.println("Nhập trạng thái : true (Active) hoặc false (Inactive)");
        singer.setStatus(InputMethods.getBoolean());
        return singer;
    }

    @Override
    public int getNewId() {
        List<Singer> singerList = IOFile.readFromFile(ShopConstants.SINGER_PATH);
        int idMax = 0;
        for (Singer singer : singerList){
            if (singer.getSingerId() > idMax){
                idMax = singer.getSingerId();
            }
        }
        return idMax+1;
    }

    @Override
    public Singer findById(int inputIdUpdate) {
        List<Singer> singerList = IOFile.readFromFile(ShopConstants.SINGER_PATH);
        for (Singer singer : singerList){
            if (singer.getSingerId() == inputIdUpdate){
                return singer;
            }
        }
        return null;
    }

    @Override
    public void searchSingerByName() {
        List<Singer> singerList = IOFile.readFromFile(ShopConstants.SINGER_PATH);
        System.out.println("Nhập tên Ca Sĩ cần tìm ");
        String searchName = InputMethods.getString();
        boolean found = false;
        for (Singer singer : singerList){
                if (singer.getSingerName().equalsIgnoreCase(searchName)){
                    singer.displayData();
                    found = true;
                    break;
                }
        }
        if (!found) {
            System.out.println("Không tìm thấy Ca Sĩ nào có tên là \"" + searchName + "\"");
        }
    }
}
