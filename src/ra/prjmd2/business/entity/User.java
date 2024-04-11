package ra.prjmd2.business.entity;

import org.mindrot.jbcrypt.BCrypt;
import ra.prjmd2.business.untils.*;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private int userId;
    private String username;
    private String email;
    private String fullname;
    private boolean status = true;
    private String password;
    private boolean role = false;
    private String phone;
    private int accountType;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private boolean isBlock;
    private Map<String, Integer> playlist;
    private double wallet;

    public User() {
    }

    public User(int userId, String username, String email, String fullname, boolean status, String password, boolean role, String phone, int accountType, LocalDate createdAt, LocalDate updatedAt, boolean isBlock, Map<String, Integer> playlist, double wallet) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.fullname = fullname;
        this.status = status;
        this.password = password;
        this.role = role;
        this.phone = phone;
        this.accountType = accountType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isBlock = isBlock;
        this.playlist = playlist;
        this.wallet = wallet;
    }

    public boolean isBlock() {
        return isBlock;
    }

    public void setBlock(boolean block) {
        isBlock = block;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getRole() {
        return role;
    }

    public void setRole(Boolean role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Map<String, Integer> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Map<String, Integer> playlist) {
        this.playlist = playlist;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public void displayData() {
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("| ID |  Tên đăng nhập  | Vai trò |                    Email                 |  Số điện thoại  | VIP | Thời gian đăng ký | Cập nhật gần nhất |  Ví tiền  |  Trạng thái  |");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-2d | %-15s |  %-6s |            %-20s          |    %-10s   |  %-2d |     %-10s    |     %-10s    | %-9.3f |    %-7s   |\n"
                    ,this.getUserId(),this.getUsername(), this.getRole()?"Admin":"User",this.getEmail(),this.getPhone()
                    ,this.getAccountType(),this.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    this.getUpdatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),this.getWallet(),this.getStatus()?"Active":"Block");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

}
