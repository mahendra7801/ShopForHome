package com.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.event.EventListener;

import com.main.mail.EmailSenderService;

@SpringBootApplication
@EnableEurekaClient
public class ShopForHomeBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopForHomeBackendApplication.class, args);
	}
}
