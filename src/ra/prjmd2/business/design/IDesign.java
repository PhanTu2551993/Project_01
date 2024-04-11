package ra.prjmd2.business.design;

import java.util.Scanner;

public interface IDesign<T> {
    void create();
    void update();
    void delete();
    void displayAll();
    T inputData();
    int getNewId();
}
