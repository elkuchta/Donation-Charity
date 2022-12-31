package pl.coderslab.charity.donation;

import org.springframework.stereotype.Service;

@Service
public class DonationServiceImpl implements DonationService{

    private final DonationRepository donationRepository;

    public DonationServiceImpl(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    @Override
    public void saveDonation(Donation donation) {
        donation.setStatus(false);
        donationRepository.save(donation);

    }


}
