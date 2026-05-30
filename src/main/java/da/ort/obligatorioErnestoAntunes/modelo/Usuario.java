package da.ort.obligatorioErnestoAntunes.modelo;

import da.ort.obligatorioErnestoAntunes.excepciones.UsuarioInvalidoException;

public abstract class Usuario {
    private String nombre;
    private String password;
    private String nombreCompleto;
    private boolean logueado;

    public boolean isLogueado() {
        return logueado;
    }

    public void setLogueado(boolean logueado) {
        this.logueado = logueado;
    }

    protected Usuario(String nombre, String password, String nombreCompleto) {
        this.nombre = nombre;
        this.password = password;
        this.nombreCompleto = nombreCompleto;
        this.logueado = false;
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
        if(this.getNombre() == null || this.getNombre().isBlank()){
            throw new UsuarioInvalidoException("El nombre no puede ser vacio");
        }
        if(this.getPassword() == null || this.getPassword().isBlank()){
            throw new UsuarioInvalidoException("La contraseña no puede ser vacia");
        }
        if(this.getNombreCompleto() == null || this.getNombreCompleto().isBlank()){
            throw new UsuarioInvalidoException("El nombre completo no puede ser vacio");
        }
        validarSaldo();
    }

    public abstract void validarSaldo() throws UsuarioInvalidoException;

    
}
