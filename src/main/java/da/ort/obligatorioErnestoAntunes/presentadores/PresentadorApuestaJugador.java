package da.ort.obligatorioErnestoAntunes.presentadores;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import da.ort.obligatorioErnestoAntunes.dto.AdminDTO;
import da.ort.obligatorioErnestoAntunes.dto.ApuestaDTO;
import da.ort.obligatorioErnestoAntunes.dto.CaballoDTO;
import da.ort.obligatorioErnestoAntunes.dto.CarreraDTO;
import da.ort.obligatorioErnestoAntunes.dto.JugadorDTO;
import da.ort.obligatorioErnestoAntunes.dto.ParticipacionDTO;
import da.ort.obligatorioErnestoAntunes.excepciones.ApuestaNoValidaException;
import da.ort.obligatorioErnestoAntunes.excepciones.CaballoNoValidoException;
import da.ort.obligatorioErnestoAntunes.excepciones.CarreraNoValidaException;
import da.ort.obligatorioErnestoAntunes.excepciones.ModalidadNoValidaException;
import da.ort.obligatorioErnestoAntunes.excepciones.ParticipacionNoValidaException;
import da.ort.obligatorioErnestoAntunes.excepciones.UsuarioInvalidoException;
import da.ort.obligatorioErnestoAntunes.modelo.Fachada;

@RestController
@RequestMapping("/apuestaJugador")
@Scope("session")
public class PresentadorApuestaJugador {
    private String modalidad;
    private CarreraDTO carreraDTO;
    private ParticipacionDTO partDTO;
    private Fachada fachada;
    private ApuestaDTO apuestaDTO;

    public PresentadorApuestaJugador(Fachada fachada) {
        this.fachada = fachada;
    }

    @PostMapping("/vistaConectada")
    public Commands vistaConectada(
            @SessionAttribute(name = "jugador", required = false) JugadorDTO jugadorDTO,
            @SessionAttribute(name = "carreraApostar", required = false) CarreraDTO carreraDTO,
            @SessionAttribute(name = "modalidadSeleccionada", required = false) String modalidad,
            @SessionAttribute(name = "nroRegistro", required = false) int nroRegistro)
            throws ParticipacionNoValidaException, CarreraNoValidaException {
        if (jugadorDTO == null) {
            return Commands.create(accesoNoPermitido());
        }

        if (carreraDTO != null && modalidad != null) {
            this.modalidad = modalidad;
            this.carreraDTO = carreraDTO;
            this.partDTO = ParticipacionDTO.from(fachada.buscarParticipacion(nroRegistro, carreraDTO.getId()),
                    fachada.buscarCarrera(carreraDTO.getId()));

            return Commands.create(numCarrera(), nombreCarrera(), nombreCaballo(), dividendoActual(), tipoApuesta(),
                    montoACobrar(0));
        }
        return Commands.create(accesoNoPermitido());
    }

    @PostMapping("/confirmarApuesta")
    public Commands confirmarApuesta(@RequestParam double valorApuesta, @RequestParam String pass,
            @SessionAttribute(name = "jugador", required = false) JugadorDTO jugadorDTO)
            throws UsuarioInvalidoException, ParticipacionNoValidaException, ModalidadNoValidaException,
            ApuestaNoValidaException, CarreraNoValidaException {
        if (jugadorDTO != null) {
            boolean passCorrecta = fachada.buscarPassword(pass, jugadorDTO.getNombreCompleto());
            boolean montoValido = fachada.montoValido(valorApuesta, jugadorDTO.getNombreCompleto());
            if(passCorrecta && montoValido){
                ApuestaDTO apuestaDTO = ApuestaDTO.from(fachada.crearApuesta(valorApuesta, jugadorDTO.getNombreCompleto(), partDTO.getNroRegistro(),
                        modalidad, carreraDTO.getId()));
                this.apuestaDTO = apuestaDTO;
                return Commands.create(volverAlTablero());
            }                
            return Commands.create(error("La contraseña es incorrecta"));                      
            
        }
        return Commands.create(accesoNoPermitido());
    }

    @PostMapping("/actualizarPremio")
    public Commands actualizarPremio(@RequestParam double valorApuesta) throws CarreraNoValidaException, ApuestaNoValidaException{
        double posiblePremio = fachada.getPremioSiEsGanadora(carreraDTO.getId(), apuestaDTO.getId());
        return Commands.create(montoACobrar(posiblePremio));
    }

    @PostMapping("/descartarApuesta") 
    public Commands descartarApuesta(){
        return Commands.create(volverAlTablero());
    }

    private Command montoACobrar(double valorApuesta) {
        return new Command("Monto a cobrar si es ganadora", valorApuesta);
    }

    private Command tipoApuesta() {
        return new Command("Modalidad", modalidad);
    }

    private Command dividendoActual() {
        return new Command("Dividendo actual", partDTO.getDividendo());
    }

    private Command nombreCaballo() {
        return new Command("Nombre del caballo", partDTO.getCaballo().getNombre());
    }

    private Command numCarrera() {
        return new Command("Numero de la carrera", carreraDTO.getNumero());
    }

    private Command nombreCarrera() {
        return new Command("Nombre de la carrera", carreraDTO.getNombre());
    }

    private Command accesoNoPermitido() {
        return new Command("accesoNoPermitido", "loginJugador.html");
    }

    private Command error(String msg){
        return new Command("Mensaje de error: ", msg);
    }

    private Command volverAlTablero(){
        return new Command("Volver al tablero", "tablerojugador.html");
    }

}
