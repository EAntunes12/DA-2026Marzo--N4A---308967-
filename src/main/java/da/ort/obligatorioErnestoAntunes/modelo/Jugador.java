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
    public void validarSaldo() throws UsuarioInvalidoException {
         if(this.getSaldo() < 0){
            throw new UsuarioInvalidoException("El saldo no puede ser menor a 0");
        }
    }
}
