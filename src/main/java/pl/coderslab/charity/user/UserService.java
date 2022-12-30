package pl.coderslab.charity.user;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import pl.coderslab.charity.CurrentUser;

public interface UserService {

    User findByUserName(String name);

    void saveUser(User user);
    void saveUserAdmin(User user);
    void deleteUser(User user);

    void removeAdminRole(User user);

    void setUserDisabled(User user);
    void setUserEnabled(User user);

    void deleteAdmin(User user,@AuthenticationPrincipal CurrentUser currentUser);

    void updateAdmin(User user);

    void changePassword(User user);

    void deleteMyProfile(User user,@AuthenticationPrincipal CurrentUser currentUser);

}
