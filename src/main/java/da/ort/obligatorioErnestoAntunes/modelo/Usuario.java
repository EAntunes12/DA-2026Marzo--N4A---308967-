package da.ort.obligatorioErnestoAntunes.modelo;

import da.ort.obligatorioErnestoAntunes.excepciones.UsuarioInvalidoException;

public abstract class Usuario {
    private String nombre;
    private String password;
    private String nombreCompleto;

    protected Usuario(String nombre, String password, String nombreCompleto) {
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

    public abstract void validar() throws UsuarioInvalidoException;

    
}
