package ra.prjmd2.presentation.run;

import org.mindrot.jbcrypt.BCrypt;
import ra.prjmd2.business.entity.User;
import ra.prjmd2.business.untils.IOFile;
import ra.prjmd2.business.untils.ShopConstants;

import java.time.LocalDate;
import java.util.List;
public class Test {
    public static void main(String[] args) {
        User user = new User();
        user.setUsername("admin");
        user.setPassword(BCrypt.hashpw("admin",BCrypt.gensalt(5)));
        user.setFullname("Tu");
        user.setEmail("tuextra@gmail.com");
        user.setRole(true);
        user.setCreatedAt(LocalDate.now());
        user.setUpdatedAt(LocalDate.now());
        List<User> userList = IOFile.readFromFile(ShopConstants.USER_PATH);
        userList.add(user);
        IOFile.writeToFile(ShopConstants.USER_PATH,userList);
    }
}
