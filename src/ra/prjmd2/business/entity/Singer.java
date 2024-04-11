package ra.prjmd2.business.entity;

import ra.prjmd2.business.design.ISinger;

import java.io.Serializable;

public class Singer implements Serializable {
    private static final long serialVersionUID = 1L;
    private int singerId;
    private String singerName;
    private String description;
    private boolean status;

    public Singer() {
    }

    public Singer(int singerId, String singerName, String description, boolean status) {
        this.singerId = singerId;
        this.singerName = singerName;
        this.description = description;
        this.status = status;
    }

    public int getSingerId() {
        return singerId;
    }

    public void setSingerId(int singerId) {
        this.singerId = singerId;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void displayData() {
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("| ID |    Tên Ca Sĩ    |                  Mô tả                  |  Trạng thái  |");
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.printf("| %-2d | %-15s |            %-20s          | %-5s |\n"
                ,this.getSingerId(),this.getSingerName(), this.getDescription(),this.isStatus()?"Active":"Inactive");
        System.out.println("-----------------------------------------------------------------------------------");
    }
}
