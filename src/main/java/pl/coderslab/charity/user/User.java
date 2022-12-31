package pl.coderslab.charity.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDeleteAll;
import pl.coderslab.charity.role.Role;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor

//@EqualPasswords()
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 60, message = "Nazwa użytkownika min 4 max 60 znaków")
   @NotBlank(message = "Pole nie może być puste")
 //   @CheckLogin(message = "Taki login juz istnieje w bazie danych")
    private String username;

    @Column(nullable = false)
    @Size(min=4,message = "Minimum 4 znaki")
    @NotBlank(message = "Pole nie może być puste")
    private String password;


    @Email
    @Column( nullable = false, unique = true)
    @NotBlank(message = "Pole nie może być puste")
  // @CheckEmail(message = "Taki email już istnieje w bazie dancyh")
    private String email;

    private int enabled;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    private String token;


    public  void addRole(Role role){
        this.roles.add(role);
    }

    public  void removeRole(Role role){
        this.roles.remove(role);

    }

    public void showRole(Role role){
        this.roles.contains("ROLE_USER");
    }




}






