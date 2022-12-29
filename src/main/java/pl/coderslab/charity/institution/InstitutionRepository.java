package pl.coderslab.charity.institution;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {

    List<Institution> findAll();
    void deleteById(Long id);

    @Query(value = "SELECT distinct m.institution from  Donation m")
    List  institutionindonation();


   @Query( value="Select x from Institution x inner join Donation z on z.institution=x")
    List allinst();
}
