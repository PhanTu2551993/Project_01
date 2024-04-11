package ra.prjmd2.business.entity;

import java.io.Serializable;
import java.util.Date;

public class Song implements Serializable {
    private static final long serialVersionUID = 1L;
    private int songId;
    private int singerId;
    private String singerName;
    private String songName;
    private String description;
    private String source;
    private double price;
    private int albumId;
    private String albumName;
    private String image;
    private Date createdAt;
    private Date updatedAt;
    private int playingCount;

    public Song() {
    }

    public Song(int songId, int singerId, String songName, String description, String source, double price, int albumId, String image, Date createdAt, Date updatedAt, int playingCount) {
        this.songId = songId;
        this.singerId = singerId;
        this.songName = songName;
        this.description = description;
        this.source = source;
        this.price = price;
        this.albumId = albumId;
        this.image = image;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.playingCount = playingCount;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public int getSingerId() {
        return singerId;
    }

    public void setSingerId(int singerId) {
        this.singerId = singerId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getPlayingCount() {
        return playingCount;
    }

    public void setPlayingCount(int playingCount) {
        this.playingCount = playingCount;
    }

    public void displayData() {
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("| ID |   Tên bài hát   | Ca Sĩ |             Mô Tả             |          Source          | Lượt nghe | Thời gian được tạo | Cập nhật gần nhất |  Giá tiền  |  Mã Album  |");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-2d | %-15s |   %-3d |         %-15s        |    %-14s   |  %-5d | Thời gian đăng ký | Cập nhật gần nhất | %-5.5f |   %-5s|\n"
                ,this.getSongId(),this.getSongName(), this.getSingerId(),this.getDescription(),this.getSource()
                ,this.getPlayingCount(),this.getPrice(),this.getAlbumId());
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
}
