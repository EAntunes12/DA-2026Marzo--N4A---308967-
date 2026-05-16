package da.ort.obligatorioErnestoAntunes.modelo;

import da.ort.obligatorioErnestoAntunes.excepciones.UsuarioInvalidoException;

public class Jugador extends Usuario {
    private double saldo;

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Jugador(String nombre, String password, String nombreCompleto, double saldo){
        super(nombre, password, nombreCompleto);
        this.saldo = saldo;
    }

    @Override
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
        if(this.getSaldo() < 0){
            throw new UsuarioInvalidoException("El saldo no puede ser menor a 0");
        }
    }

}
