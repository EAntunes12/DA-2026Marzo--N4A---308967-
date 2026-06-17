package da.ort.obligatorioErnestoAntunes.presentadores;

import da.ort.obligatorioErnestoAntunes.conf.ConfiguracionAppObligatorio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import da.ort.obligatorioErnestoAntunes.dto.AdminDTO;
import da.ort.obligatorioErnestoAntunes.dto.ApuestaDTO;
import da.ort.obligatorioErnestoAntunes.dto.ApuestaJugadorDTO;
import da.ort.obligatorioErnestoAntunes.dto.CarreraDTO;
import da.ort.obligatorioErnestoAntunes.dto.JugadorDTO;
import da.ort.obligatorioErnestoAntunes.excepciones.ApuestaNoValidaException;
import da.ort.obligatorioErnestoAntunes.excepciones.CarreraNoValidaException;
import da.ort.obligatorioErnestoAntunes.excepciones.UsuarioInvalidoException;
import da.ort.obligatorioErnestoAntunes.modelo.Carrera;
import da.ort.obligatorioErnestoAntunes.modelo.Fachada;
import da.ort.obligatorioErnestoAntunes.modelo.Jugador;
import da.ort.obligatorioErnestoAntunes.observer.Observable;
import da.ort.obligatorioErnestoAntunes.observer.Observador;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/tableroJugador")
@Scope("session")
public class PresentadorTableroJugador implements Observador {
    private JugadorDTO jugadorDTO;
    private Fachada fachada;
    private final ConexionNavegador conexion;

    public PresentadorTableroJugador(Fachada fachada, ConexionNavegador c) {
        this.fachada = fachada;
        this.conexion = c;
    }

    @GetMapping(value = "/registrarSSE", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter registrarSSE(@SessionAttribute(name = "jugador", required = false) JugadorDTO jug) {
        if (jug == null)
            return null;
        conexion.conectarSSE();
        return conexion.getConexionSSE();
    }

    @PostMapping("/vistaConectada")
    public Commands vistaConectada(@SessionAttribute(name = "jugador", required = false) JugadorDTO jugadorDTO)
            throws UsuarioInvalidoException {
        if (jugadorDTO != null) {
            this.jugadorDTO = jugadorDTO;
            fachada.agregarObservador(this);
            return Commands.create(saldoJugador(), datosJugador(), carrerasDisponibles(), tiposDeApuesta(),
                    apuestasJugador(), totalApostado(), totalGanado());
        }
        return Commands.create(accesoNoPermitido());
    }

    @PostMapping("/apostar")
    public Commands apostar(
            @RequestParam int idCarrera, @RequestParam String modalidad, @RequestParam int nroRegistro,
            @SessionAttribute(name = "jugador", required = false) JugadorDTO jugadorDTO, HttpSession session)
            throws CarreraNoValidaException {
        if (jugadorDTO != null) {
            CarreraDTO carreraDTO = CarreraDTO.from(fachada.buscarCarrera(idCarrera));

            session.setAttribute("carreraApostar", carreraDTO);
            session.setAttribute("modalidadSeleccionada", modalidad);
            session.setAttribute("nroRegistro", nroRegistro);

            return Commands.create(vistaApostar());
        }
        return Commands.create(accesoNoPermitido());
    }

    @PostMapping("/logout")
    public Commands logout(HttpSession session) throws UsuarioInvalidoException {
        fachada.logout(((JugadorDTO) session.getAttribute("jugador")).getNombreCompleto());
        session.invalidate();
        fachada.quitarObservador(this);
        return Commands.create(accesoNoPermitido());
    }

    private Command saldoJugador() throws UsuarioInvalidoException {
        JugadorDTO dto = JugadorDTO.from(fachada.buscarJugador(jugadorDTO.getNombreCompleto()));
        return new Command("Saldo", dto.getSaldo());
    }

    private Command apuestasJugador() {
        return new Command("Apuestas del jugador",
                ApuestaJugadorDTO.fromList(fachada.getApuestasPorJugador(jugadorDTO.getNombre())));
    }

    private Command tiposDeApuesta() {
        return new Command("Modalidades de apuesta", fachada.getModalidades());
    }

    private Command carrerasDisponibles() {
        List<CarreraDTO> carreras = CarreraDTO.fromList(fachada.getCarrerasDisponibles());
        if(carreras.isEmpty()){
            return new Command("Mensaje de error", "No hay carreras disponibles");
        }

        return new Command("Carreras disponibles", carreras);
    }

    private Command datosJugador() {
        return new Command("Datos del jugador", jugadorDTO.getNombre());
    }

    private Command totalApostado() {
        double totalApostado = fachada.getTotalApostadoPorJugador(jugadorDTO.getNombreCompleto());
        return new Command("Total apostado", totalApostado);
    }

    private Command totalGanado() {
        double totalGanado = fachada.getTotalGanadoPorJugador(jugadorDTO.getNombreCompleto());
        return new Command("Total ganado", totalGanado);
    }

    private Command accesoNoPermitido() {
        return new Command("accesoNoPermitido", "loginJugador.html");
    }

    private Command vistaApostar() {
        return new Command("Vista apostar", "apostar.html");
    }

    @Override
    public void actualizar(Object evento, Observable origen) {
        try {
            if (evento == Fachada.Eventos.APUESTA_REALIZADA || evento == Fachada.Eventos.CARRERA_ABIERTA || evento == Fachada.Eventos.CARRERA_CERRADA || evento == Fachada.Eventos.CARRERA_FINALIZADA) {
                Commands cmds = Commands.create(
                        saldoJugador(), apuestasJugador(), tiposDeApuesta(), carrerasDisponibles(),
                        datosJugador(), totalApostado(), totalGanado());
                conexion.enviarJSON(cmds);
            }
        } catch (UsuarioInvalidoException ex) {
            ex.printStackTrace();
        }
    }

}
