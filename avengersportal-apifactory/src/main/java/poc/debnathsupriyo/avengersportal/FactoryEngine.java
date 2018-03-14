package poc.debnathsupriyo.avengersportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class FactoryEngine {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SpringApplication.run(FactoryEngine.class, args);

	}
	
	@Bean
	public WebMvcConfigurer configureCORS() {
		return new WebMvcConfigurerAdapter() {
			
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/avengersportal-apifactory/microservices/**").allowedOrigins("http://Riyo14-LB-48091037.ap-south-1.elb.amazonaws.com");
			}
		};
	}

}
