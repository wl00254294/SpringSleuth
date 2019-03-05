package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.controller.Test;

import brave.sampler.Sampler;

@SpringBootApplication
@EnableAutoConfiguration
public class SpringSleuthApplication {
	//private static Logger log = Logger.getLogger(SpringSleuthApplication.class.getName());
		
	public static void main(String[] args) {
		SpringApplication.run(SpringSleuthApplication.class, args);
	}
	
}
