package da.ort.obligatorioErnestoAntunes.modelo;

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

}
