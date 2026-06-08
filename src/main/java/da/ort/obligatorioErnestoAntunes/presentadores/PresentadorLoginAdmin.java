package da.ort.obligatorioErnestoAntunes.presentadores;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import da.ort.obligatorioErnestoAntunes.dto.AdminDTO;
import da.ort.obligatorioErnestoAntunes.excepciones.UsuarioInvalidoException;
import da.ort.obligatorioErnestoAntunes.modelo.Administrador;
import da.ort.obligatorioErnestoAntunes.modelo.Fachada;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/loginAdmin")
public class PresentadorLoginAdmin {
    private Fachada fachada;

    public PresentadorLoginAdmin(Fachada fachada) {
        this.fachada = fachada;
    }

    @PostMapping("/login")
    public Commands login(HttpSession session, @RequestParam String nombre, @RequestParam String pass) throws UsuarioInvalidoException{
        Administrador admin = fachada.loginAdmin(nombre, pass);
        session.setAttribute("administrador", new AdminDTO(admin));
        return Commands.create(accesoPermitido());
    }

    private Command accesoPermitido(){
        return new Command("accesoPermitido", "tableroAdmin.html");
    }
}
