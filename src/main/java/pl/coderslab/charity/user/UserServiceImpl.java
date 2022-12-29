package pl.coderslab.charity.user;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.coderslab.charity.CurrentUser;
import pl.coderslab.charity.role.Role;
import pl.coderslab.charity.role.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;



    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;


    }

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setEnabled(1);
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
    }
    @Override
    public void saveUserAdmin(User user) {
        Role role=roleRepository.findByName("ROLE_USER");
        Role rolee=roleRepository.findByName("ROLE_ADMIN");
        user.removeRole(role);
        user.addRole(rolee);

        userRepository.save(userRepository.getUserById(user.getId()));

    }

    @Override


    public void deleteUser(User user) {

         Role role=roleRepository.findByName("ROLE_USER");
       Role rolee=roleRepository.findByName("ROLE_ADMIN");
        user.removeRole(role);
        user.removeRole(rolee);

        userRepository.deleteById(user.getId());

    }

    @Override


    public void deleteAdmin(User user,@AuthenticationPrincipal CurrentUser currentUser) {
        if(userRepository.getUserById(user.getId())!=userRepository.getUserById(currentUser.getUser().getId())) {
            Role role=roleRepository.findByName("ROLE_USER");
            Role rolee=roleRepository.findByName("ROLE_ADMIN");
            user.removeRole(role);
            user.removeRole(rolee);

            userRepository.deleteById(user.getId());}


    }


    @Override
    public void removeAdminRole(User user) {
        Role role=roleRepository.findByName("ROLE_ADMIN");
        Role rolee=roleRepository.findByName("ROLE_USER");
        user.removeRole(role);
        user.addRole(rolee);

        userRepository.save(userRepository.getUserById(user.getId()));

    }

    @Override
    public void setUserDisabled(User user){
        user.setEnabled(0);
        userRepository.save(user);
    }

    @Override
    public void setUserEnabled(User user){
        user.setEnabled(1);
        userRepository.save(user);
    }

    @Override
    public void updateAdmin(User user){

        userRepository.save(user);

    }

    @Override
    public void changePassword(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

}