package Student.rest.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails surjith= User.builder()
                .username("surjith")
                .password("{bcrypt}$2a$12$nrP0O/uax4ZR5AH04CaNfuiqDNEWhgorUNmW.8uHw78erVSZ7L7T2")
                .roles("EMPLOYEE","MANAGER","ADMIN")
                .build();

        UserDetails sathish= User.builder()
                .username("sathish")
                .password("{noop}1234")
                .roles("EMPLOYEE","MANAGER")
                .build();

        UserDetails kalees= User.builder()
                .username("kalees")
                .password("{bcrypt}$2a$12$9UhjX.mCSwgnpEzSSTY5tuHXkXDlKs6wU8pClv/K4yHMHulecbs5i")
                .roles("EMPLOYEE")
                .build();
        return new InMemoryUserDetailsManager(surjith,kalees,sathish);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET,"/spring").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET,"/spring/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST,"/spring").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT,"/spring").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE,"/spring/**").hasRole("ADMIN")
        );
        //using http basic authentication
        http.httpBasic();
        //diable the cross site request forgery (CSRF)
        //in general, not required for the stateless rest api's that use get,post,put,delete and patch
        http.csrf().disable();
        return http.build();
    }
}