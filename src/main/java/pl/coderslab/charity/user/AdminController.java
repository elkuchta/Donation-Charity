package pl.coderslab.charity.user;

import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.CurrentUser;
import pl.coderslab.charity.donation.DonationRepository;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionRepository;
import pl.coderslab.charity.institution.InstitutionService;
import pl.coderslab.charity.role.RoleRepository;

import javax.validation.Valid;

@Controller
public class AdminController {

    private final InstitutionRepository institutionRepository;
    private final UserRepository userRepository;
    private final InstitutionService institutionService;
    private final UserService userService;
    private final RoleRepository roleRepository;


    private final DonationRepository donationRepository;




    public AdminController(InstitutionRepository institutionRepository, UserRepository userRepository, InstitutionService institutionService, UserService userService, RoleRepository roleRepository,  DonationRepository donationRepository) {
        this.institutionRepository = institutionRepository;
        this.userRepository = userRepository;
        this.institutionService = institutionService;
        this.userService = userService;
        this.roleRepository = roleRepository;

        this.donationRepository = donationRepository;
    }

    @RequestMapping("/admin/institutions")
    public String homeAction( Model model){
        model.addAttribute("institutions", institutionRepository.findAll());
        model.addAttribute("aaa",institutionRepository.allinst());
        return "adminInstitutions";
    }

   @RequestMapping("/admin/deleteinstitution/{institutionId}")
    public String institutionDelete(@PathVariable Long institutionId){
        institutionRepository.deleteById(institutionId);


        return "redirect:/admin/institutions";
    }

    @GetMapping("/admin/addinstitution")
    public String addInstitution(Model model){
        model.addAttribute("institution", new Institution());

        return "addInstitution";
    }
    @PostMapping("/admin/addinstitution")
    public String register(@Valid Institution institution, Model model, BindingResult result)  {


        if(result.hasErrors()){

            return "addInstitution";
        }
       institutionService.saveInstitution(institution);
        return "redirect:/admin/institutions";
    }
    @GetMapping("/admin/editinstitution/{id}")
    public String editInstitution(Model model, @PathVariable Long id){
        model.addAttribute("institution", institutionRepository.findById(id));
        return "editInstitution";
    }
    @PostMapping("/admin/editinstitution/{id}")
    public String editInstitution(@Valid Institution institution,Model model, BindingResult result)  {


        if(result.hasErrors()){

            return "editInstitution";
        }
        institutionService.saveInstitution(institution);
        return "redirect:/admin/institutions";
    }

    @RequestMapping("/admin/users")
    public String users( Model model){
        model.addAttribute("users", userRepository.findBySpecificRoles("ROLE_USER"));


        return "users";
    }


    @RequestMapping("/admin/userblock/{id}")
    public String usersBlock(@PathVariable Long id, Model model){

      if(userRepository.getUserById(id).getEnabled()==1){
        userService.setUserDisabled((userRepository.getUserById(id)));}
      else
      {  userService.setUserEnabled((userRepository.getUserById(id))); }

        return "redirect:/admin/users";
    }




    @RequestMapping("/admin/deleteuser/{userId}")
    public String deleteuser(@PathVariable User userId){

        userService.deleteUser(userId);
        return "redirect:/admin/users";
    }

    @RequestMapping("/admin/deleteAdmin/{userId}")
    public String deleteAdmin(@PathVariable User userId,@AuthenticationPrincipal CurrentUser currentUser){

        userService.deleteAdmin(userId,currentUser);
        return "redirect:/admin/users";
    }

    @RequestMapping ("/admin/new/{id}")
    public String makeAdmin( @PathVariable Long id){
    userService.saveUserAdmin(userRepository.getUserById(id));
        return "redirect:/admin/users";
    }



    @RequestMapping("/admin/list")
    public String adminList(@AuthenticationPrincipal CurrentUser currentUser, Model model){
        model.addAttribute("users", userRepository.findBySpecificRoles("ROLE_ADMIN"));
        model.addAttribute("admin",userRepository.findAll());
        model.addAttribute("current",userRepository.getUserById(currentUser.getUser().getId()));
        return "admins";
    }

    @RequestMapping ("/admin/removerole/{id}")
    public String removeAdminRole( @PathVariable Long id){
        userService.removeAdminRole(userRepository.getUserById(id));
        return "redirect:/admin/users";
    }



    @GetMapping("/admin/editAdmin/{id}")
    public String editAdmin(@AuthenticationPrincipal CurrentUser currentUser, Model model, @PathVariable Long id){

        model.addAttribute("user",userRepository.getUserById(id));

        return "editUser";
    }
    @PostMapping("/admin/editAdmin/{id}")
    public String profile(@Valid User user){

        userService.updateAdmin(user);
        return "redirect:/admin/list";
    }

    @GetMapping("/admin/changePassword/{id}")
    public String changePasswordAdmin(@AuthenticationPrincipal CurrentUser currentUser, Model model, @PathVariable Long id){

        model.addAttribute("user",userRepository.getUserById(id));

        return "changePassword";
    }
    @PostMapping("/admin/changePassword/{id}")
    public String changePasswordAdmin(@Valid User user){

       userService.changePassword(user);
        return "redirect:/admin/list";
    }

    @RequestMapping("/admin/donations")
    public String userDonations(@AuthenticationPrincipal CurrentUser currentUser,Model model){
        model.addAttribute("donations",donationRepository.findAll());
        return "donationsList";
    }

}
