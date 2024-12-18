//package com.nnk.springboot.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableWebSecurity
//public class ApplicationSecurityConfig {
//
//    @Autowired
//    private DataSource dataSource; // Utilisé pour connecter à la BDD via JDBC

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(); // Pour comparer les mots de passe hashés
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//                .dataSource(dataSource) // Utilise la source de données configurée
//                .passwordEncoder(passwordEncoder()) // Déclare BCrypt pour le hashage
//                .usersByUsernameQuery("SELECT username, password, true FROM users WHERE username = ?")
//                .authoritiesByUsernameQuery("SELECT username, CONCAT('ROLE_', role) FROM users WHERE username = ?");
//    }

//    protected void configure(HttpSecurity http) throws Exception {
//
//        http
//                .authorizeRequests()
//                .requestMatchers("/","/css/*", "/img/*, /oauth2/**").permitAll()
////                .antMatchers("/admin/**").hasAnyAuthority("ADMIN")
////                .antMatchers("/user/**").hasRole("USER")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/app/login")
//                .loginProcessingUrl("/app/login")
//                .defaultSuccessUrl("/", true)
//                .failureUrl("/app/login?error=true")
//                .permitAll()
////                .and()
////                .oauth2Login();
//                .and()
//                .logout()
//                .logoutUrl("/app-logout") // URL de logout
//                .logoutSuccessUrl("/") // Redirection après déconnexion
//                .invalidateHttpSession(true) // Invalide la session
//                .deleteCookies("JSESSIONID") // Supprime les cookies de session
//                .permitAll();
//      }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("admin").password(passwordEncoder().encode("adminpass")).roles("ADMIN") // Admin user
//                .and()
//                .withUser("user").password(passwordEncoder().encode("userpass")).roles("USER"); // Regular user
//    }
//}
