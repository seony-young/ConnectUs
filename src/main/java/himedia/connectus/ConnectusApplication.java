package himedia.connectus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ConnectusApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConnectusApplication.class, args);
	}

}
