package da.ort.obligatorioErnestoAntunes.modelo;

import java.time.LocalDate;

import da.ort.obligatorioErnestoAntunes.excepciones.JornadaNoValidaException;

public class Jornada {
    private LocalDate fecha;

    public Jornada(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void validar() throws JornadaNoValidaException{
        if(fecha == null){
            throw new JornadaNoValidaException("La fecha no puede ser vacia");
        }
    }
}
