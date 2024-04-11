package ra.prjmd2.business.design;

import ra.prjmd2.business.entity.User;

import java.util.Scanner;

public interface IUser extends IDesign<User> {
    void searchSingSongAlbum();
    void login();
    void register();
    boolean existByUsername(String username);
}
