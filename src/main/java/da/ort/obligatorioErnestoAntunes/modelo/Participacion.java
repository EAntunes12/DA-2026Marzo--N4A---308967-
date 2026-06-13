package da.ort.obligatorioErnestoAntunes.modelo;

import da.ort.obligatorioErnestoAntunes.excepciones.ParticipacionNoValidaException;

public class Participacion {
    private int numeroRegistro;
    private double dividendo;
    private Caballo caballo;
    private Carrera carrera;

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }
    public Caballo getCaballo() {
        return caballo;
    }

    public void setCaballo(Caballo caballo) {
        this.caballo = caballo;
    }

    public Participacion(int numeroRegistro, Caballo c) {
        this.numeroRegistro = numeroRegistro;
        this.dividendo = -1; // Se inicia sin un valor valido
        this.caballo = c;
    }

    public void calcularDividendo(double pozo, double totalCaballo) {
        if (totalCaballo == 0) {
            this.dividendo = 0;
        } else {
            this.dividendo = pozo / totalCaballo;
        }
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

    public void validar() throws ParticipacionNoValidaException {
        if (numeroRegistro < 1) {
            throw new ParticipacionNoValidaException("El numero de registro no es valido");
        }
        if (caballo == null) {
            throw new ParticipacionNoValidaException("El caballo asociado no es valido");
        }
    }

}
