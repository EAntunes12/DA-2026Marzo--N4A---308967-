package da.ort.obligatorioErnestoAntunes.modelo;

import da.ort.obligatorioErnestoAntunes.excepciones.ParticipacionNoValidaException;

public class Participacion {
    private int numeroRegistro;
    private double dividendo;
    private Caballo caballo;

    public Caballo getCaballo() {
        return caballo;
    }

    public void setCaballo(Caballo caballo) {
        this.caballo = caballo;
    }

    public Participacion(int numeroRegistro, Caballo c) {
        this.numeroRegistro = numeroRegistro;
        this.dividendo = calcularDividendo();
        this.caballo = c;
    }

    private double calcularDividendo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calcularDividendo'");
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

    public void validar() throws ParticipacionNoValidaException{
        if(numeroRegistro < 1){
            throw new ParticipacionNoValidaException("El numero de registro no es valido");
        }
        if(caballo == null){
            throw new ParticipacionNoValidaException("El caballo asociado no es valido");
        }
    }
}
