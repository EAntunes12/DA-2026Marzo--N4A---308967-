package da.ort.obligatorioErnestoAntunes.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.cglib.core.Local;

import da.ort.obligatorioErnestoAntunes.excepciones.ApuestaNoValidaException;
import da.ort.obligatorioErnestoAntunes.excepciones.CarreraNoValidaException;
import da.ort.obligatorioErnestoAntunes.excepciones.ParticipacionNoValidaException;

public class Carrera {
    private int numero;
    private String nombre;
    private Participacion ganador;
    private LocalDate fecha;
    private Estado estado;
    private List<Participacion> participaciones;
    private List<Apuesta> apuestas;

    public List<Apuesta> getApuestas() {
        return apuestas;
    }

    public List<Participacion> getParticipaciones() {
        return participaciones;
    }

    public Carrera(int numero, String nombre, LocalDate fecha) {
        this.numero = numero;
        this.nombre = nombre;
        this.ganador = null;
        this.fecha = fecha;
        this.estado = Estado.DEFINIDA;
        this.participaciones = new ArrayList<>();
        this.apuestas = new ArrayList<>();
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    private void existeParticipacion(Participacion p) throws ParticipacionNoValidaException {
        for (Participacion actual : participaciones) {
            if (actual.getNumeroRegistro() == p.getNumeroRegistro()) {
                throw new ParticipacionNoValidaException("Ya existe una participacion con ese numero.");
            }
        }
    }

    public void agregarParticipacion(Participacion p) throws ParticipacionNoValidaException{
        if (p == null)throw new ParticipacionNoValidaException("La participacion no es valida.");
        
        p.validar();
        existeParticipacion(p);

        participaciones.add(p);
    }

    public void agregarApuesta(Apuesta a) throws ApuestaNoValidaException{
        if(a == null) throw new ApuestaNoValidaException("La apuesta no es valida.");

        a.validar();
        apuestas.add(a);
    }

    public void validar()
            throws CarreraNoValidaException {
        if (numero <= 0) {
            throw new CarreraNoValidaException(
                    "Numero de carrera invalido.");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new CarreraNoValidaException(
                    "El nombre es obligatorio.");
        }
        if (participaciones == null || participaciones.size() < 2) {
            throw new CarreraNoValidaException(
                    "La carrera debe tener al menos 2 caballos.");
        }
    }

    public void abrir() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'abrir'");
    }

    public void cerrar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cerrar'");
    }

    public void recalcularDividendos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'recalcularDividendos'");
    }
}
