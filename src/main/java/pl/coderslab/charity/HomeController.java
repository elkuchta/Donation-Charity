package pl.coderslab.charity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.charity.donation.DonationRepository;
import pl.coderslab.charity.institution.InstitutionRepository;
import pl.coderslab.charity.mail.EmailService;
import pl.coderslab.charity.role.RoleRepository;
import pl.coderslab.charity.user.User;
import pl.coderslab.charity.user.UserRepository;
import pl.coderslab.charity.user.UserService;


import javax.validation.Valid;
import java.util.UUID;


@Controller
public class HomeController {

    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;

    private final RoleRepository roleRepository;

    private final UserService userService;

    private final EmailService emailService;


    private final UserRepository userRepository;

    public HomeController(InstitutionRepository institutionRepository, DonationRepository donationRepository, RoleRepository roleRepository, UserService userService, EmailService emailService, UserRepository userRepository) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
        this.roleRepository = roleRepository;
        this.userService = userService;
        this.emailService = emailService;
        this.userRepository = userRepository;
    }


    @RequestMapping("/")
    public String homeAction(Model model){
        model.addAttribute("institutions",institutionRepository.findAll());
        model.addAttribute("sum",donationRepository.sumQuantities());
        model.addAttribute("donations",donationRepository.findAll());



        return "index";
    }

    @PostMapping(value = "/contact")
    public String contactProcess(RedirectAttributes redirectAttrs, @AuthenticationPrincipal CurrentUser currentUser, @RequestParam("name") String name, @RequestParam("surname") String surname, @RequestParam("message") String message){
        emailService.sendSimpleMessage(name + "," + surname,message);
        redirectAttrs.addFlashAttribute("info", "Wiadomość została wysłana");
        return "redirect:/";
    }

   @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());

       // model.addAttribute("role",roleRepository.findByName("ROLE_USER"));
        return "registerForm";
    }
    @PostMapping("/register")
    public String register(Model model,@Valid User user, BindingResult result)  {



        if(result.hasErrors()){

            return "registerForm";
        }

        userService.saveUser(user);

        return "redirect:/login";
    }

    @RequestMapping(value = "/confirm/user/{uuid}")
    public String confirmMail(@PathVariable String uuid) {
      if(userRepository.existsByToken(uuid)){
          userService.setUserEnabled(userRepository.getUserByToken(uuid));
      }return "redirect:/";
    }
}
