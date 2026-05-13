package da.ort.obligatorioErnestoAntunes.modelo;

public class Participacion {
    private int numeroRegistro;
    private double dividendo;

    public Participacion(int numeroRegistro, double dividendo) {
        this.numeroRegistro = numeroRegistro;
        this.dividendo = dividendo;
    }

    public int getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(int numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public double getDividendo() {
        return dividendo;
    }

    public void setDividendo(double dividendo) {
        this.dividendo = dividendo;
    }
}
