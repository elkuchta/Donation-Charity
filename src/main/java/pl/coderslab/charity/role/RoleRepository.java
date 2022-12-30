package pl.coderslab.charity.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
    public interface RoleRepository extends JpaRepository<Role, Integer> {
        Role findByName(String name);
        Role findById(int id);
        List<Role>findAll();

        Role getById(int id);
    }

