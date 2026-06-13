package da.ort.obligatorioErnestoAntunes.presentadores;

import da.ort.obligatorioErnestoAntunes.modelo.Carrera;
import da.ort.obligatorioErnestoAntunes.modelo.Fachada;
import da.ort.obligatorioErnestoAntunes.modelo.Jornada;
import da.ort.obligatorioErnestoAntunes.observer.Observable;
import da.ort.obligatorioErnestoAntunes.observer.Observador;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import da.ort.obligatorioErnestoAntunes.dto.AdminDTO;
import da.ort.obligatorioErnestoAntunes.dto.CarreraDTO;
import da.ort.obligatorioErnestoAntunes.dto.JornadaDTO;
import da.ort.obligatorioErnestoAntunes.excepciones.CarreraNoValidaException;
import da.ort.obligatorioErnestoAntunes.excepciones.JornadaNoValidaException;
import da.ort.obligatorioErnestoAntunes.excepciones.UsuarioInvalidoException;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/tableroAdmin")
@Scope("session")
public class PresentadorTableroAdmin implements Observador{
    private Fachada fachada;
    private Jornada jornadaActual;
    private final ConexionNavegador conexion;

    public PresentadorTableroAdmin(Fachada fachada, ConexionNavegador c){
        this.fachada = fachada;
        this.conexion = c;
    }


    @PostMapping("/vistaConectada")
    public Commands vistaConectada(@SessionAttribute(name="administrador",required=false) AdminDTO adminDTO) throws JornadaNoValidaException{
        if(adminDTO != null){            
            if(jornadaActual == null){
                jornadaActual = fachada.obtenerJornadaActual();
            } 
            fachada.agregarObservador(this);
            return Commands.create(
                totalApostado(), totalPagado(), comisiones(), balanceJornada(),
                cantProximasCarreras(), cantCarrerasFinalizadas(), cantCarrerasJornada(),
                carrerasFinalizadas(), proximasCarreras(), mostrarJornada()
            ); 
        }
        return Commands.create(accesoNoPermitido());
    }

    @PostMapping("/siguienteJornada")
    public Commands siguienteJornada(@SessionAttribute(name="administrador",required=false) AdminDTO adminDTO) throws JornadaNoValidaException{
        if(adminDTO != null){
            jornadaActual = fachada.siguienteJornada(jornadaActual);
            return Commands.create(
                totalApostado(), totalPagado(), comisiones(), balanceJornada(),
                cantProximasCarreras(), cantCarrerasFinalizadas(), cantCarrerasJornada(),
                carrerasFinalizadas(), proximasCarreras(), mostrarJornada()
            );
        }
        return Commands.create(accesoNoPermitido());
    }

    @PostMapping("/anteriorJornada")
    public Commands anteriorJornada(@SessionAttribute(name="administrador",required=false) AdminDTO adminDTO) throws JornadaNoValidaException{
        if(adminDTO != null){
            jornadaActual = fachada.anteriorJornada(jornadaActual);
            return Commands.create(
                totalApostado(), totalPagado(), comisiones(), balanceJornada(),
                cantProximasCarreras(), cantCarrerasFinalizadas(), cantCarrerasJornada(),
                carrerasFinalizadas(), proximasCarreras(), mostrarJornada()
            );
        }
        return Commands.create(accesoNoPermitido());
    }


    @PostMapping("/gestionarCarrera")
    public Commands gestionarCarrera(@RequestParam int carrera, @SessionAttribute(name="administrador",required=false) AdminDTO adminDTO, HttpSession session) throws CarreraNoValidaException{
        if(adminDTO != null){
            CarreraDTO carreraDTO = CarreraDTO.from(fachada.buscarCarrera(carrera));
            session.setAttribute("carreraSeleccionada", carreraDTO);
            return Commands.create(vistaGestionarCarrera());
        }
        return Commands.create(accesoNoPermitido());
    }    
    
    @PostMapping("/logout")
    public Commands logout(HttpSession session) throws UsuarioInvalidoException{
        fachada.logout(((AdminDTO)session.getAttribute("administrador")).getNombreCompleto());
        session.invalidate();
        fachada.quitarObservador(this);
        return Commands.create(accesoNoPermitido());
    }
    
    private Command vistaGestionarCarrera() {
        return new Command("Gestionar carrera", "gestionarCarrera.html");
    }

    
    private Command accesoNoPermitido() {
       return new Command("accesoNoPermitido", "loginAdmin.html");
    }

    private Command totalApostado() throws JornadaNoValidaException {
        return new Command("Total apostado en la jornada", fachada.getTotalApostadoPorJornada(jornadaActual.getFecha()));
    }

    private Command totalPagado() throws JornadaNoValidaException{
        return new Command("Total pagado en la jornada", fachada.getTotalPagadoPorJornada(jornadaActual.getFecha()));
    }

    private Command balanceJornada() throws JornadaNoValidaException{
        return new Command("Balance de la jornada", fachada.getBalanceJornada(jornadaActual.getFecha()));
    }

    private Command comisiones() throws JornadaNoValidaException{
        return new Command("Comisiones", fachada.getTotalComisionJornada(jornadaActual.getFecha()));
    }

    private Command proximasCarreras() throws JornadaNoValidaException{
        return new Command("Proximas carreras", CarreraDTO.fromList(fachada.getCarrerasDisponiblesPorJornada(jornadaActual.getFecha())));
    }

    private Command carrerasFinalizadas() throws JornadaNoValidaException{
        return new Command("Carreras finalizadas", CarreraDTO.fromList(fachada.getCarrerasFinalizadasPorJornada(jornadaActual.getFecha())));
    }

    private Command cantProximasCarreras() throws JornadaNoValidaException{
        return new Command("Cantidad proximas carreras", fachada.getCarrerasDisponiblesPorJornada(jornadaActual.getFecha()).size());
    }

    private Command cantCarrerasFinalizadas() throws JornadaNoValidaException{
        return new Command("Cantidad carreras finalizadas", fachada.getCarrerasFinalizadasPorJornada(jornadaActual.getFecha()).size());
    }

    private Command cantCarrerasJornada() throws JornadaNoValidaException{
        return new Command("Total de carreras", fachada.getCantCarrerasJornada(jornadaActual.getFecha()));
    }

    private Command mostrarJornada(){
        return new Command("mostrarJornada", new JornadaDTO(jornadaActual));
    }


    @Override
    public void actualizar(Object evento, Observable origen) {
        System.out.println("evento: "+ evento);
    }
}
