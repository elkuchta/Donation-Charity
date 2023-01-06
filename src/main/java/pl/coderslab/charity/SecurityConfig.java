package pl.coderslab.charity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {




@Autowired
private DataSource dataSource;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()

                .antMatchers("/").permitAll()
                .antMatchers("/contact").permitAll()
                .antMatchers("/institutions").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/confirm/**").permitAll()
                .antMatchers("/remind").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")

                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")

                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/")

              //  .and().rememberMe().tokenValiditySeconds(36000).tokenRepository(persistentTokenRepository()).userDetailsService(this.customUserDetailsService())
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
        http.cors().and().csrf().disable();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringDataUserDetailsService customUserDetailsService() {
        return new SpringDataUserDetailsService();
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web

                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

}
