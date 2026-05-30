package da.ort.obligatorioErnestoAntunes.presentadores;

import da.ort.obligatorioErnestoAntunes.modelo.Fachada;
import da.ort.obligatorioErnestoAntunes.modelo.Jornada;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import da.ort.obligatorioErnestoAntunes.dto.AdminDTO;
import da.ort.obligatorioErnestoAntunes.dto.JornadaDTO;
import da.ort.obligatorioErnestoAntunes.excepciones.UsuarioInvalidoException;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/panelControlAdmin")
@Scope("session")
public class PresentadorPanelControlAdmin {
    private Fachada fachada;
    private Jornada jornadaActual;

    public PresentadorPanelControlAdmin(Fachada fachada){
        this.fachada = fachada;
    }


    @PostMapping("/vistaConectada")
    public Commands vistaConectada(@SessionAttribute(name="administrador",required=false) AdminDTO adminDTO) {
        if(adminDTO != null){
            if(jornadaActual == null){
                jornadaActual = fachada.obtenerJornadaActual();
            } 
            return Commands.create(/*comandos para armar el tablero */);
        }
        return Commands.create(accesoNoPermitido());
    }
    
    @PostMapping("/siguienteJornada")
    public Commands siguienteJornada(@SessionAttribute(name="administrador",required=false) AdminDTO adminDTO){
        if(adminDTO != null){
            jornadaActual = fachada.siguienteJornada(jornadaActual);
            return Commands.create(/*comandos para armar el tablero */);
        }
        return Commands.create(accesoNoPermitido());
    }

    @PostMapping("/anteriorJornada")
    public Commands anteriorJornada(@SessionAttribute(name="administrador",required=false) AdminDTO adminDTO){
        if(adminDTO != null){
            jornadaActual = fachada.anteriorJornada(jornadaActual);
            return Commands.create(/*comandos para armar el tablero */);
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

    //comando temporal para debuggar
    private Command mostrarJornada(){
        return new Command("mostrarJornada", new JornadaDTO(jornadaActual));
    }
}
