package pl.coderslab.charity.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);

    User getByUsername(String username);

    @Query( "select u from users u JOIN FETCH u.roles r where r.name = :Name" )
    List<User> findBySpecificRoles(@Param("Name") String Name);

    void deleteById(Long id);

   User getUserById(Long id);

  List <User> findAll();
}
