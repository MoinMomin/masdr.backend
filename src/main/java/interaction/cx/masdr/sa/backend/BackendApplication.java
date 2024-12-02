package interaction.cx.masdr.sa.backend;

import interaction.cx.masdr.sa.backend.service.DbConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {
@Autowired
	DbConnectionService dbConnectionService;
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		dbConnectionService.configAllDataSources();
	}
}
