package pl.coderslab.charity.institution;

import org.springframework.stereotype.Service;

@Service
public class InstitutionServiceImpl implements InstitutionService {

private final InstitutionRepository institutionRepository;

    public InstitutionServiceImpl(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @Override
    public void saveInstitution(Institution institution) {
        institutionRepository.save(institution);
    }
}
