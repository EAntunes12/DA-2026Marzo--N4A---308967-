package da.ort.obligatorioErnestoAntunes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import da.ort.obligatorioErnestoAntunes.datosPrueba.DatosPrueba;

@SpringBootApplication
public class ObligatorioErnestoAntunesApplication {

	public static void main(String[] args) {
		DatosPrueba.cargar();
		SpringApplication.run(ObligatorioErnestoAntunesApplication.class, args);
	}

}
