package da.ort.obligatorioErnestoAntunes.modelo;

import da.ort.obligatorioErnestoAntunes.excepciones.UsuarioInvalidoException;

public class Usuario {
    private String nombre;
    private String password;
    private String nombreCompleto;

    public Usuario(String nombre, String password, String nombreCompleto) {
        this.nombre = nombre;
        this.password = password;
        this.nombreCompleto = nombreCompleto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void validar() throws UsuarioInvalidoException{
        if(nombre.isBlank() || nombre == null){
            throw new UsuarioInvalidoException("El nombre no puede ser vacio.");
        }
        if(password.isBlank() || password == null){
            throw new UsuarioInvalidoException("La contraseña no puede ser vacia.");
        }
        if(nombreCompleto.isBlank() || nombreCompleto == null){
            throw new UsuarioInvalidoException("El nombre completo no puede ser vacio.");
        }
    }

    
}
