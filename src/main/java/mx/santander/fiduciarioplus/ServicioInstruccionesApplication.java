package mx.santander.fiduciarioplus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class ServicioInstruccionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioInstruccionesApplication.class, args);
	}

}
