package pl.coderslab.charity.user;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.CurrentUser;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @RequestMapping("/profile")
    public String profile(Model model,@AuthenticationPrincipal CurrentUser currentUser){
        model.addAttribute("userlogged",userRepository.getUserById(currentUser.getUser().getId()));

        return"profile";
    }

    @GetMapping("/profile/edit")
    public String profile(@AuthenticationPrincipal CurrentUser currentUser, Model model){

        model.addAttribute("userlogged",userRepository.getUserById(currentUser.getUser().getId()));

        return "editProfile";
    }
    @PostMapping("/profile/edit")
    public String profile(@Valid User user, Model model){

        userService.updateAdmin(user);
        return "redirect:/profile";
    }


    @RequestMapping("/profile/delete/{userID}")
    public String deletMyProfile(@PathVariable User userID,@AuthenticationPrincipal CurrentUser currentUser){

  userService.deleteMyProfile(userID,currentUser);
        return "redirect:/logout";
    }


    @GetMapping("/profile/changePassword")
    public String changePasswordAdmin(@AuthenticationPrincipal CurrentUser currentUser, Model model){

        model.addAttribute("user",userRepository.getUserById(currentUser.getUser().getId()));

        return "changePassword";
    }
    @PostMapping("/profile/changePassword")
    public String changePasswordAdmin(@Valid User user){

        userService.changePassword(user);
        return "redirect:/profile";
    }

}
