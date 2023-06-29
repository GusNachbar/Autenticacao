package com.learning.autentica;

import com.learning.autentica.controller.LoginController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AutenticaApplication {

	public static void main(String[] args) { SpringApplication.run(AutenticaApplication.class, args); }

}
