package da.ort.obligatorioErnestoAntunes.presentadores;

import da.ort.obligatorioErnestoAntunes.conf.ConfiguracionAppObligatorio;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import da.ort.obligatorioErnestoAntunes.dto.AdminDTO;
import da.ort.obligatorioErnestoAntunes.dto.ApuestaDTO;
import da.ort.obligatorioErnestoAntunes.dto.CarreraDTO;
import da.ort.obligatorioErnestoAntunes.dto.JugadorDTO;
import da.ort.obligatorioErnestoAntunes.excepciones.ApuestaNoValidaException;
import da.ort.obligatorioErnestoAntunes.excepciones.CarreraNoValidaException;
import da.ort.obligatorioErnestoAntunes.excepciones.UsuarioInvalidoException;
import da.ort.obligatorioErnestoAntunes.modelo.Fachada;
import da.ort.obligatorioErnestoAntunes.modelo.Jugador;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/tableroJugador")
@Scope("session")
public class PresentadorTableroJugador {
    private JugadorDTO jugadorDTO;
    private Fachada fachada;

    public PresentadorTableroJugador(Fachada fachada){
        this.fachada = fachada;
    }

    @PostMapping("/vistaConectada")
    public Commands vistaConectada(@SessionAttribute(name="jugador",required=false) JugadorDTO jugadorDTO){
        if(jugadorDTO != null){
            this.jugadorDTO = jugadorDTO;
            return Commands.create(saldoJugador(), datosJugador(), carrerasDisponibles(), tiposDeApuesta(), apuestasJugador(), totalApostado(), totalGanado());
        }
        return Commands.create(accesoNoPermitido());
    }

    @PostMapping("/apostar")
    public Commands apostar(@RequestParam int idCarrera, @RequestParam String modalidad, @SessionAttribute(name="jugador",required=false) JugadorDTO jugadorDTO, HttpSession session) throws CarreraNoValidaException{
        if(jugadorDTO != null){
            CarreraDTO carreraDTO = CarreraDTO.from(fachada.buscarCarrera(idCarrera));

            session.setAttribute("carreraApostar", carreraDTO);
            session.setAttribute("modalidadSeleccionada", modalidad);

            return Commands.create(vistaApostar());
        }
        return Commands.create(accesoNoPermitido());
    }

    @PostMapping("/logout")
    public Commands logout(HttpSession session) throws UsuarioInvalidoException{
        fachada.logout(((JugadorDTO)session.getAttribute("jugador")).getNombre());
        session.invalidate();
        return Commands.create(accesoNoPermitido());
    }
    
    private Command saldoJugador(){
        return new Command("Saldo", jugadorDTO.getSaldo());
    }

    private Command apuestasJugador(){
        return new Command("Apuestas del jugador", ApuestaDTO.fromList(fachada.getApuestasPorJugador(jugadorDTO.getNombre())));
    }

    private Command tiposDeApuesta() {
        return new Command("Modalidades de apuesta", fachada.getModalidades());
    }

    private Command carrerasDisponibles(){
        return new Command("Carreras disponibles", CarreraDTO.fromList(fachada.getCarrerasDisponibles()));
    }

    private Command datosJugador() {
        return new Command("Datos del jugador", jugadorDTO.getNombre()); 
    }

    private Command totalApostado(){
        return new Command("Total apostado", fachada.getTotalApostadoPorJugador(jugadorDTO.getNombreCompleto()));
    }

    private Command totalGanado(){
        return new Command("Total ganado", fachada.getTotalGanadoPorJugador(jugadorDTO.getNombreCompleto()));
    }

    private Command accesoNoPermitido() {
       return new Command("accesoNoPermitido", "loginJugador.html");
    }

    private Command vistaApostar(){
        return new Command("Vista apostar", "apostar.html");
    }
    
}
