package da.ort.obligatorioErnestoAntunes.modelo;

import da.ort.obligatorioErnestoAntunes.excepciones.ApuestaNoValidaException;
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
    
    public void pagarPremio(double premio){
        this.saldo += premio;
    }

    @Override
    public void validarSaldo() throws UsuarioInvalidoException {
         if(this.getSaldo() < 0){
            throw new UsuarioInvalidoException("El saldo no puede ser menor a 0");
        }
    }

    public void descontarSaldo(double valorApuesta) throws ApuestaNoValidaException {
        if (valorApuesta <= 0) {
            throw new ApuestaNoValidaException("El monto debe ser mayor a 0");
        }

        if (this.saldo < valorApuesta) {
            throw new ApuestaNoValidaException("Saldo insuficiente");
        }

        this.saldo -= valorApuesta;
    }
}
