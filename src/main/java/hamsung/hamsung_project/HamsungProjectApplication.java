package hamsung.hamsung_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HamsungProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(HamsungProjectApplication.class, args);
	}

}
