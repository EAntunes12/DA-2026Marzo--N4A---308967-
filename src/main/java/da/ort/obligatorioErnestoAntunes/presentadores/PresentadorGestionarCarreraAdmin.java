package da.ort.obligatorioErnestoAntunes.presentadores;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import da.ort.obligatorioErnestoAntunes.dto.AdminDTO;
import da.ort.obligatorioErnestoAntunes.dto.CarreraDTO;
import da.ort.obligatorioErnestoAntunes.dto.ParticipacionDTO;
import da.ort.obligatorioErnestoAntunes.excepciones.CarreraNoValidaException;
import da.ort.obligatorioErnestoAntunes.excepciones.ParticipacionNoValidaException;
import da.ort.obligatorioErnestoAntunes.modelo.Fachada;
import da.ort.obligatorioErnestoAntunes.modelo.Participacion;
import da.ort.obligatorioErnestoAntunes.observer.Observable;
import da.ort.obligatorioErnestoAntunes.observer.Observador;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/gestionarCarrera")
@Scope("session")
public class PresentadorGestionarCarreraAdmin implements Observador {
    private CarreraDTO carrera;
    private Fachada fachada;
    private final ConexionNavegador conexion;

    public PresentadorGestionarCarreraAdmin(Fachada fachada, ConexionNavegador c) {
        this.fachada = fachada;
        this.conexion = c;
    }

    @GetMapping(value = "/registrarSSE", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter registrarSSE(@SessionAttribute(name = "administrador", required = false) AdminDTO admin) {
        if (admin == null)
            return null;
        conexion.conectarSSE();
        return conexion.getConexionSSE();
    }

    @PostMapping("/vistaConectada")
    public Commands vistaConectada(
            @SessionAttribute(name = "administrador", required = false) AdminDTO adminDTO,
            @SessionAttribute(name = "carreraSeleccionada", required = false) CarreraDTO carreraDTO)
            throws CarreraNoValidaException {
        if (adminDTO == null) {
            return Commands.create(accesoNoPermitido());
        }

        if (carreraDTO == null) {
            throw new CarreraNoValidaException("No hay una carrera seleccionada");
        }

        fachada.agregarObservador(this);
        this.carrera = carreraDTO;

        return Commands.create(datosCarrera(), caballosParticipantes());

    }

    @PostMapping("/abrirCarrera")
    public Commands abrirCarrrera() throws CarreraNoValidaException {
        if (carrera != null) {
            fachada.abrirCarrera(carrera.getId());

            carrera = CarreraDTO.from(fachada.buscarCarrera(carrera.getId()));

            return Commands.create(datosCarrera(), caballosParticipantes()); // no se si hace falta llamar a
                                                                             // caballosParticipantes aca
        }
        return Commands.create(carreraNoValida());
    }

    @PostMapping("/cerrarCarrera")
    public Commands cerrarCarrera() throws CarreraNoValidaException {
        if (carrera != null) {
            fachada.cerrarCarrera(carrera.getId());

            carrera = CarreraDTO.from(fachada.buscarCarrera(carrera.getId()));

            return Commands.create(datosCarrera(), caballosParticipantes());
        }
        return Commands.create(carreraNoValida());
    }

    @PostMapping("/finalizarCarrera")
    public Commands finalizarCarrera(@RequestParam int nroRegistro)
            throws CarreraNoValidaException, ParticipacionNoValidaException {
        if (carrera != null) {
            fachada.finalizarCarrera(carrera.getId(), nroRegistro); // paga a lso que apostaron pero capaz que hay que
                                                                    // revisarlo
            carrera = CarreraDTO.from(fachada.buscarCarrera(carrera.getId()));

            return Commands.create(datosCarrera(), caballosParticipantes(), volverAlTablero());
        }
        return Commands.create(carreraNoValida());
    }

    @PostMapping("/volverTablero")
    public Commands volverTablero() {
        fachada.quitarObservador(this);
        return Commands.create(volverAlTablero());
    }

    private Command volverAlTablero() {
        return new Command("Volver al tablero", "tableroAdmin.html");
    }

    private Command carreraNoValida() {
        return new Command("Carrera no valida", "tableroAdmin.html");
    }

    private Command caballosParticipantes() throws CarreraNoValidaException {
        CarreraDTO dto = CarreraDTO.from(fachada.buscarCarrera(carrera.getId()));
        return new Command("Caballos participantes", dto.getParticipaciones());
    }

    private Command datosCarrera() throws CarreraNoValidaException {
        CarreraDTO carrera = CarreraDTO.from(fachada.buscarCarrera(this.carrera.getId()));
        return new Command("Datos carrera", carrera);
    }

    private Command accesoNoPermitido() {
        return new Command("accesoNoPermitido", "loginAdmin.html");
    }

    private Command error(String msg) {
        return new Command("Mensaje de error", msg);
    }

    @Override
    public void actualizar(Object evento, Observable origen) {
        try {
            if (evento == Fachada.Eventos.APUESTA_REALIZADA) {
                Commands cmds = Commands.create(caballosParticipantes(), datosCarrera());
                conexion.enviarJSON(cmds);
            }
        } catch (CarreraNoValidaException ex) {
            ex.printStackTrace();
        }
    }

}
