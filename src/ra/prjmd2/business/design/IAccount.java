package ra.prjmd2.business.design;

import java.util.Scanner;

public interface IAccount extends IDesign{
    boolean existByUsername(String username);
    void searchAccountByName();
    void changeStatusUser();
}
