package da.ort.obligatorioErnestoAntunes.presentadores;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import da.ort.obligatorioErnestoAntunes.dto.AdminDTO;
import da.ort.obligatorioErnestoAntunes.dto.CarreraDTO;
import da.ort.obligatorioErnestoAntunes.dto.ParticipacionDTO;
import da.ort.obligatorioErnestoAntunes.excepciones.CarreraNoValidaException;
import da.ort.obligatorioErnestoAntunes.excepciones.ParticipacionNoValidaException;
import da.ort.obligatorioErnestoAntunes.modelo.Fachada;
import da.ort.obligatorioErnestoAntunes.modelo.Participacion;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/gestionarCarrera")
@Scope("session")
public class PresentadorGestionarCarreraAdmin {
    private CarreraDTO carrera;
    private Fachada fachada;

    public PresentadorGestionarCarreraAdmin(Fachada fachada){
        this.fachada = fachada;
    }

    @PostMapping("/vistaConectada")
    public Commands vistaConectada(
        @SessionAttribute(name="administrador",required=false) AdminDTO adminDTO,
        @SessionAttribute(name="carreraSeleccionada",required=false) CarreraDTO carreraDTO
        ) 
    {
        if(adminDTO != null && carreraDTO != null){
            this.carrera = carreraDTO;

            return Commands.create(datosCarrera(), caballosParticipantes());
        }
        return Commands.create(accesoNoPermitido());
    }

    @PostMapping("/abrirCarrera")
    public Commands abrirCarrrera() throws CarreraNoValidaException{
        if(carrera != null){
            fachada.abrirCarrera(carrera.getId());

            carrera = CarreraDTO.from(fachada.buscarCarrera(carrera.getId()));
            
            return Commands.create(datosCarrera(), caballosParticipantes()); //no se si hace falta llamar a caballosParticipantes aca
        }
        return Commands.create(carreraNoValida());
    }

    @PostMapping("/cerrarCarrera")
    public Commands cerrarCarrera() throws CarreraNoValidaException{
        if(carrera != null){
            fachada.cerrarCarrera(carrera.getId());

            carrera = CarreraDTO.from(fachada.buscarCarrera(carrera.getId()));
            
            return Commands.create(datosCarrera(), caballosParticipantes());
        }
        return Commands.create(carreraNoValida());
    }
    
    @PostMapping("/finalizarCarrera")
    public Commands finalizarCarrera(@RequestParam ParticipacionDTO participacion) throws CarreraNoValidaException, ParticipacionNoValidaException{
        if(carrera != null){
            if(participacion == null){
                throw new ParticipacionNoValidaException("Debe indicar caballo ganador de la carrera");
            }

            fachada.finalizarCarrera(carrera.getId(), participacion.getNroRegistro());  //TODO  FALTA QUE LE PAGUE A LOS QUE APOSTARON AL GANADOR
            carrera = CarreraDTO.from(fachada.buscarCarrera(carrera.getId()));

            return Commands.create(datosCarrera(), caballosParticipantes());
        }
        return Commands.create(carreraNoValida());
    }

    @PostMapping("/volverTablero")
    public Commands volverTablero(){
        return Commands.create(volverAlTablero());
    }

    private Command volverAlTablero() {
        return new Command("Volver al tablero", "tableroAdmin.html");
    }

    private Command carreraNoValida() {
        return new Command("Carrera no valida", "tableroAdmin.html");
    }

    private Command caballosParticipantes() {
        return new Command("Caballos participantes", ParticipacionDTO.fromList(carrera.getParticipaciones()));
    }

    private Command datosCarrera() {
        return new Command("Datos carrera", carrera); //TODO CarreraDTO no tiene el total apostado
    }

    private Command accesoNoPermitido() {
       return new Command("accesoNoPermitido", "loginAdmin.html");
    }

}
