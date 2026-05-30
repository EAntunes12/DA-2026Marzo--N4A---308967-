package da.ort.obligatorioErnestoAntunes.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import da.ort.obligatorioErnestoAntunes.modelo.Fachada;

@Configuration
public class ConfiguracionAppObligatorio {
    @Bean
    public Fachada fachada(){
        return Fachada.getInstancia();
    }
}


