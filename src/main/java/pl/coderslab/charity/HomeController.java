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
    public String register(@RequestParam("pass") String pass,Model model,@Valid User user, BindingResult result,RedirectAttributes redirectAttrs)  {



        if(result.hasErrors()){

            return "registerForm";
        }

        if(pass.equals(user.getPassword())){
            userService.saveUser(user);
            redirectAttrs.addFlashAttribute("info", "Na podanego maila został wysłany link do aktywacji konta");
            return "redirect:/login";
        }
      else{
            model.addAttribute("pass", "niepoprawne powtórzenie hasła");
        }
      return "registerForm";
    }

    @RequestMapping(value = "/confirm/user/{uuid}")
    public String confirmMail(@PathVariable String uuid) {
      if(userRepository.existsByToken(uuid)){
          userService.setUserEnabled(userRepository.getUserByToken(uuid));
      }return "redirect:/";
    }

    @GetMapping(value = "/remind")
    public String remindForm(){
        return "remindPassword";
    }
    @PostMapping(value = "/remind")
    public String remindProcess(Model model,RedirectAttributes redirectAttrs, @RequestParam("email") String email){
        User user = userRepository.getByEmail(email);
        if (user!=null){
            UUID uuid = UUID.randomUUID();
            user.setPasswordToken(uuid.toString());
            userService.saveTokenToChangePassword(user);
            emailService.sendRemindPasswordEmail(email,"Kliknij w link by zmienić hasło: http://localhost:8080/newPassword/" + uuid);
        } else {
            model.addAttribute("email", "Użytkownik z takim mailem nie istnieje");
return "remindPassword";
        }
        redirectAttrs.addFlashAttribute("info", "Na podanego maila został wysłany link do zmiany hasła");
        return "redirect:/";
    }

    @GetMapping(value = "/newPassword/{uuid}")
        public String newPassword(Model model,@PathVariable String uuid,RedirectAttributes redirectAttrs){
        if(userRepository.existsByPasswordToken(uuid)){
       model.addAttribute("user",userRepository.getUserByPasswordToken(uuid));
            return "newPassword";

        } else{
            redirectAttrs.addFlashAttribute("info", "Podany link jest już nie aktywny, wygeneruj nowy");
            return "redirect:/remind";
        }
        }

        @PostMapping(value = "/newPassword/{uuid}")
    public String newPassword(@Valid User user,Model model,@RequestParam("pass") String pass){

            if(pass.equals(user.getPassword())){
                userService.changePassword(user);

                return "redirect:/login";
            }
            else{
                model.addAttribute("pass", "Niepoprawne powtórzone hasło");
            }

            return "newPassword";
        }




}
