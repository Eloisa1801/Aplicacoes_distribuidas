package com.ms.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EmailApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailApplication.class, args);

        System.out.println(new BCryptPasswordEncoder().encode("senha123"));//senha joao
        System.out.println(new BCryptPasswordEncoder().encode("senha321"));//senha maria
    }

}
