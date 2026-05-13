package da.ort.obligatorioErnestoAntunes.modelo;

import java.util.Date;

public class Carrera {
    private int numero;
    private String nombre;
    private Participacion ganador;
    private Date fecha;
    private Estado estado;

    public Carrera(int numero, String nombre, Participacion ganador, Date fecha, Estado estado) {
        this.numero = numero;
        this.nombre = nombre;
        this.ganador = ganador;
        this.fecha = fecha;
        this.estado = estado;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Participacion getGanador() {
        return ganador;
    }

    public void setGanador(Participacion ganador) {
        this.ganador = ganador;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
