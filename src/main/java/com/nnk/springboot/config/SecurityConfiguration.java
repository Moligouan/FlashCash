package com.nnk.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder();};

    @Bean
    public SecurityFilterChain security(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/bootstrap.min.css", "/index.css", "/images/**", "/signin", "/signup").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/signin")
//                        .loginProcessingUrl("/signin-process")
                        .permitAll().usernameParameter("email").defaultSuccessUrl("/", true)
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/?logout=true")
                        .invalidateHttpSession(true)
                        .permitAll());
        return http.build();
    }

        /*
        http.authorizeRequests()
                // uncoment to secure .antMatchers("/user", "/admin", "/user/**", "/admin/**").hasAnyAuthority("AQUIRED")
                .anyRequest()
                // remove permit all to secure
                .permitAll()
                // uncoment to secure .authenticated()
                .and()
                .formLogin()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
        */

//    }
//    @Bean
//    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    /*
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
        throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, 'true' as enabled from user where username = ?")
                .authoritiesByUsernameQuery("select username, role from users where username = ?");
    }
    */
}
