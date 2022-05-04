package nl.meritude.apimodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "nl.meritude")
public class ApiModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiModuleApplication.class, args);
	}

}
