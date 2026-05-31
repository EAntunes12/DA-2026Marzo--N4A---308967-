package da.ort.obligatorioErnestoAntunes.presentadores;

import da.ort.obligatorioErnestoAntunes.modelo.Fachada;
import da.ort.obligatorioErnestoAntunes.modelo.Jornada;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class PresentadorTableroAdmin {
    private Fachada fachada;
    private Jornada jornadaActual;

    public PresentadorTableroAdmin(Fachada fachada){
        this.fachada = fachada;
    }


    @PostMapping("/vistaConectada")
    public Commands vistaConectada(@SessionAttribute(name="administrador",required=false) AdminDTO adminDTO) throws JornadaNoValidaException{
        if(adminDTO != null){
            if(jornadaActual == null){
                jornadaActual = fachada.obtenerJornadaActual();
            } 
            return Commands.create(carrerasFinalizadas(), proximasCarreras());
        }
        return Commands.create(accesoNoPermitido());
    }


    @PostMapping("/siguienteJornada")
    public Commands siguienteJornada(@SessionAttribute(name="administrador",required=false) AdminDTO adminDTO) throws JornadaNoValidaException{
        if(adminDTO != null){
            jornadaActual = fachada.siguienteJornada(jornadaActual);
            return Commands.create(carrerasFinalizadas(), proximasCarreras());
        }
        return Commands.create(accesoNoPermitido());
    }

    @PostMapping("/anteriorJornada")
    public Commands anteriorJornada(@SessionAttribute(name="administrador",required=false) AdminDTO adminDTO) throws JornadaNoValidaException{
        if(adminDTO != null){
            jornadaActual = fachada.anteriorJornada(jornadaActual);
            return Commands.create(carrerasFinalizadas(), proximasCarreras());
        }
        return Commands.create(accesoNoPermitido());
    }



    @PostMapping("/logout")
    public Commands logout(HttpSession session) throws UsuarioInvalidoException{
        fachada.logout(((AdminDTO)session.getAttribute("administrador")).getNombre());
        session.invalidate();
        return Commands.create(accesoNoPermitido());
    }

    private Command accesoNoPermitido() {
       return new Command("accesoNoPermitido", "loginAdmin.html");
    }

    private Command proximasCarreras() throws JornadaNoValidaException{
        if(jornadaActual == null) throw new JornadaNoValidaException("La jornada no existe"); //en teoria nunca deberia pasar que no haya una jornada pero lo dejo por las dudas

        return new Command("Proximas carreras", CarreraDTO.fromList(fachada.getCarrerasDisponiblesPorJornada(jornadaActual.getFecha())));
    }


    private Command carrerasFinalizadas() throws JornadaNoValidaException{
        if(jornadaActual == null) throw new JornadaNoValidaException("La jornada no existe"); //lo mismo

        return new Command("Carreras finalizadas", CarreraDTO.fromList(fachada.getCarrerasFinalizadasPorJornada(jornadaActual.getFecha())));
    }


    //comando temporal para debuggar
    private Command mostrarJornada(){
        return new Command("mostrarJornada", new JornadaDTO(jornadaActual));
    }
}
