package com.ankit.JPAHibernateDemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpaHibernateDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaHibernateDemoApplication.class, args);

	}

	@Bean    //Executed after the Spring Beans have been loaded
	public CommandLineRunner commandLineRunner(String[] args){
		return runner ->{
			System.out.println("hello World");
		};
	}

}
