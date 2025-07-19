package project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FileExtensionApplication {

	public static void main(String[] args) {
		System.setProperty("app.initialization.timestamp", String.valueOf(System.currentTimeMillis()));
		ConfigurableApplicationContext context = SpringApplication.run(FileExtensionApplication.class, args);
	}
}