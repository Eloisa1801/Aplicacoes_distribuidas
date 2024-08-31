package com.ms.email.configs.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Value("${app.auth.username}")
    private String username;

    @Value("${app.auth.password}")
    private String password;

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception{

        http
                .authorizeHttpRequests((authorize) -> authorize
                        //.anyRequest().permitAll() //igual ao padrão NoAuth
                        .requestMatchers(HttpMethod.GET, "/sending-email").hasAnyRole("ADMIN","USER") //Comentar ao trabalhar com autenticação em memória ou sem roles
                        .requestMatchers(HttpMethod.POST, "/sending-email").hasRole("ADMIN") //Comentar ao trabalhar com autenticação em memória ou sem roles
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                //.csrf((csrf) -> csrf.disable()); //forma de fazer por função lambda
                .csrf(AbstractHttpConfigurer::disable); // :: é outra forma de referenciar um método
        return http.build();
    }
/*
    @Bean
    public UserDetailsService userDetailsService() throws Exception { //Autenticação em memória
        //UserDetails user = User.withDefaultPasswordEncoder()
        UserDetails user = User.builder()
                .username(username)
                .password(passwordEncoder().encode(password)) //Desnecessário se usar o User.withDefaultPasswordEncoder()
                //.password(password) //Usar assim caso usar o User.withDefaultPasswordEncoder()
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
*/
//    @Bean
//    public PasswordEncoder passwordEncoder() { //Outra classe de encoder que dá certo
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() { //Necessário se não usar o User.withDefaultPasswordEncoder()
        return new BCryptPasswordEncoder();
    }

}
