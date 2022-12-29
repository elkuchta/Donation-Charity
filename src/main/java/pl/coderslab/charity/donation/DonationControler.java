package pl.coderslab.charity.donation;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.CurrentUser;
import pl.coderslab.charity.category.CategoryRepository;
import pl.coderslab.charity.institution.InstitutionRepository;
import pl.coderslab.charity.user.UserRepository;

import javax.validation.Valid;

@Controller
public class DonationControler {

    private final CategoryRepository categoryRepository;
    private final InstitutionRepository institutionRepository;
    private final DonationService donationService;
    private final UserRepository userRepository;

    public DonationControler(CategoryRepository categoryRepository, InstitutionRepository institutionRepository, DonationService donationService, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.institutionRepository = institutionRepository;
        this.donationService = donationService;
        this.userRepository = userRepository;
    }

    @RequestMapping("/formConfirmation")
    public String homeAction(){



        return "formConfirmation";
    }

    @GetMapping("/donate")
    public String Donate( Model model){

        model.addAttribute("category",categoryRepository.findAll());
        model.addAttribute("institution", institutionRepository.findAll());
        model.addAttribute("donation", new Donation());


        return"form";
    }
@PostMapping ("/donate")
    public String addDonation(@AuthenticationPrincipal CurrentUser currentUser,@Valid Donation donation, BindingResult result,Model model){
    model.addAttribute("userlogged",userRepository.getByUsername(currentUser.getUser().getUsername()));

        donationService.saveDonation(donation);
        return "redirect:/formConfirmation";
}

}
