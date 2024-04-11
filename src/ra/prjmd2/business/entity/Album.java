package ra.prjmd2.business.entity;

import java.io.Serializable;

public class Album implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private int singerId;
    private String name;
    private String description;
    private String image;

    public Album() {
    }

    public Album(int id, int singerId, String name, String description, String image) {
        this.id = id;
        this.singerId = singerId;
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSingerId() {
        return singerId;
    }

    public void setSingerId(int singerId) {
        this.singerId = singerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void displayData() {
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("| ID |    Tên Album    |  ID Ca Sĩ |                  Mô tả                  |  Image  |");
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.printf("| %-2d | %-15s |   %-4d   |           %-20s          |   %-5s   |\n"
                ,this.getId(),this.getName(), this.getSingerId(),this.getDescription(),this.getImage());
        System.out.println("----------------------------------------------------------------------------------------");
    }
}
