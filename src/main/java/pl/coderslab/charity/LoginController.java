package pl.coderslab.charity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.charity.user.UserRepository;


@Controller
public class LoginController {
    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/login")
    public String homeActionLogin(@AuthenticationPrincipal CurrentUser currentUser, Model model){
     /*   model.addAttribute("institutions", institutionRepository.findAll());
        model.addAttribute("quantity", donationRepository.sumQuantities());
        model.addAttribute("donations", donationRepository.findAll());*/

        return "login";
    }


    @RequestMapping(value = {"/logout"} )
    public String logout() {


        return "redirect:/";
    }


}
