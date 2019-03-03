package com.example.zipkinservice2;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class ZipkinService2Application {

	public static void main(String[] args) {
		SpringApplication.run(ZipkinService2Application.class, args);
	}
}
@RestController
class ZipkinController{
	
	@Autowired
	RestTemplate restTemplate;
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}/*@Bean
	public AlwaysSampler alwaysSampler() {
		return new AlwaysSampler();
	}*/
	Logger logger = LoggerFactory.getLogger(ZipkinController.class);

	
	@GetMapping(value="/zipkin2")
	public String zipkinService1() {
		logger.info("Inside zipkinService 2..");
		logger.info("Now let's create some intentional delay...");
		try {
			Thread.sleep(20 * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("returning afte delay..");
		String response = (String) restTemplate.exchange("http://localhost:8083/zipkin3", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
        }).getBody();
		return "Hi...";
	}
}