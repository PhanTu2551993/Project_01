package ra.prjmd2.business.entity;

import java.time.LocalDate;

public class History {
    private int historyId;
    private int songId;
    private int userId;
    private String orderAt;
    private double totalPrice;
    private int typePackage;
    private LocalDate createdAt;
    private LocalDate expiryDate;

    public History() {
    }

    public History(int historyId, int songId, int userId, String orderAt, double totalPrice, int typePackage, LocalDate createdAt, LocalDate expiryDate) {

        this.historyId = historyId;
        this.songId = songId;
        this.userId = userId;
        this.orderAt = orderAt;
        this.totalPrice = totalPrice;
        this.typePackage = typePackage;
        this.createdAt = createdAt;
        this.expiryDate = expiryDate;
    }

    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOrderAt() {
        return orderAt;
    }

    public void setOrderAt(String orderAt) {
        this.orderAt = orderAt;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTypePackage() {
        return typePackage;
    }

    public void setTypePackage(int typePackage) {
        this.typePackage = typePackage;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
}
