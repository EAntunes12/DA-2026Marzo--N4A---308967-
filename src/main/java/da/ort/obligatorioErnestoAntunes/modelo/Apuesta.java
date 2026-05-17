package da.ort.obligatorioErnestoAntunes.modelo;

import da.ort.obligatorioErnestoAntunes.excepciones.ApuestaNoValidaException;

public class Apuesta {
    private double valor;
    private Jugador jugador;
    private Participacion participacion;
    private Modalidad modalidad;    

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Participacion getParticipacion() {
        return participacion;
    }

    public void setParticipacion(Participacion participacion) {
        this.participacion = participacion;
    }

    public Modalidad getModalidad() {
        return modalidad;
    }

    public void setModalidad(Modalidad modalidad) {
        this.modalidad = modalidad;
    }

    public Apuesta(double valor, Jugador jugador, Participacion participacion, Modalidad modalidad) {
        this.valor = valor;
        this.jugador = jugador;
        this.participacion = participacion;
        this.modalidad = modalidad;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void validar() throws ApuestaNoValidaException{
        if(valor < 1){
            throw new ApuestaNoValidaException("El valor de la apuesta no puede ser 0 o negativo");
        }
        if(jugador == null){
            throw new ApuestaNoValidaException("El jugador no puede ser vacio");
        }
        if(participacion == null){
            throw new ApuestaNoValidaException("La participacion no puede ser vacia");
        }
        if(modalidad == null){
            throw new ApuestaNoValidaException("La modalidad no puede ser vacia");
        }
    }
}
