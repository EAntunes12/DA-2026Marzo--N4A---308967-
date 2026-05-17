package da.ort.obligatorioErnestoAntunes.modelo;

import da.ort.obligatorioErnestoAntunes.excepciones.UsuarioInvalidoException;

public class Administrador extends Usuario {

    public Administrador(String nombre, String password, String nombreCompleto) {
        super(nombre, password, nombreCompleto);
    }

    @Override
    public void validar() throws UsuarioInvalidoException {
        if(this.getNombre() == null || this.getNombre().isBlank()){
            throw new UsuarioInvalidoException("El nombre no puede ser vacio");
        }
        if(this.getPassword() == null || this.getPassword().isBlank()){
            throw new UsuarioInvalidoException("La contraseña no puede ser vacia");
        }
        if(this.getNombreCompleto() == null || this.getNombreCompleto().isBlank()){
            throw new UsuarioInvalidoException("El nombre completo no puede ser vacio");
        }
    }

    
}
