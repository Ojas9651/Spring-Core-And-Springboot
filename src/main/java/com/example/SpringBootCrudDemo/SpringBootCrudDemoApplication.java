package com.example.SpringBootCrudDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication()
public class SpringBootCrudDemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringBootCrudDemoApplication.class, args);

	}
}
