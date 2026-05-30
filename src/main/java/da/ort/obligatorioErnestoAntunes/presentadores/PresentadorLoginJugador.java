package da.ort.obligatorioErnestoAntunes.presentadores;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import da.ort.obligatorioErnestoAntunes.dto.AdminDTO;
import da.ort.obligatorioErnestoAntunes.dto.JugadorDTO;
import da.ort.obligatorioErnestoAntunes.excepciones.UsuarioInvalidoException;
import da.ort.obligatorioErnestoAntunes.modelo.Administrador;
import da.ort.obligatorioErnestoAntunes.modelo.Fachada;
import da.ort.obligatorioErnestoAntunes.modelo.Jugador;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/loginJugador")
public class PresentadorLoginJugador {
    private Fachada fachada;

    public PresentadorLoginJugador(Fachada fachada) {
        this.fachada = fachada;
    }

    @PostMapping("/login")
    public Commands login(HttpSession session, @RequestParam String nombre, @RequestParam String pass) throws UsuarioInvalidoException{
        Jugador jugador = fachada.loginJugador(nombre, pass);
        session.setAttribute("jugador", new JugadorDTO(jugador));
        return Commands.create(accesoPermitido());
    }

    private Command accesoPermitido(){
        return new Command("accesoPermitido", "tablero.html"); //tablero.html en realidad es la vista a la que tenga que mandar al jugador una vez que se loguea.
    }
}
