package pl.coderslab.charity.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.role.Role;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);

    User findByEmail(String email);

    User getByUsername(String username);

    @Query( "select u from users u JOIN FETCH u.roles r where r.name = :Name" )
    List<User> findBySpecificRoles(@Param("Name") String Name);

    void deleteById(Long id);

   User getUserById(Long id);

  List <User> findAll();

  void deleteUserById(Long id);

  User getUserByRoles(Set<Role> roles);
User getByEmail(String email);

@Query("SELECT u.email FROM users u WHERE u.id=( SELECT max(z.id) FROM users z)")

String getEmail();

User findUserByToken (String token);

boolean existsByToken(String token);

User getUserByToken(String token);

User getUserByPasswordToken(String passwordToken);




}
