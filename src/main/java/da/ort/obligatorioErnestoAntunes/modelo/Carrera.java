package da.ort.obligatorioErnestoAntunes.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.cglib.core.Local;

import da.ort.obligatorioErnestoAntunes.estado.Abierta;
import da.ort.obligatorioErnestoAntunes.estado.Definida;
import da.ort.obligatorioErnestoAntunes.estado.Estable;
import da.ort.obligatorioErnestoAntunes.estado.EstadoCarrera;
import da.ort.obligatorioErnestoAntunes.excepciones.ApuestaNoValidaException;
import da.ort.obligatorioErnestoAntunes.excepciones.CarreraNoValidaException;
import da.ort.obligatorioErnestoAntunes.excepciones.ParticipacionNoValidaException;

public class Carrera {
    private static int ultimoId = 0; // agregue un id a la carrera para la busqueda
    private int id;
    private int numero;
    private String nombre;
    private Participacion ganador;
    private LocalDate fecha;
    private EstadoCarrera estado;
    private List<Participacion> participaciones;
    private List<Apuesta> apuestas;

    public List<Apuesta> getApuestas() {
        return apuestas;
    }

    public List<Participacion> getParticipaciones() {
        return participaciones;
    }

    public Carrera(String nombre, LocalDate fecha) {
        this.id = ++ultimoId;
        this.nombre = nombre;
        this.ganador = null;
        this.fecha = fecha;
        this.estado = new Definida();
        this.participaciones = new ArrayList<>();
        this.apuestas = new ArrayList<>();
    }

    public int getId() {
        return id;
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

    public EstadoCarrera getEstado() {
        return estado;
    }

    public void setEstado(EstadoCarrera estado) {
        this.estado = estado;
    }

    private void existeParticipacion(Participacion p) throws ParticipacionNoValidaException {
        for (Participacion actual : participaciones) {
            if (actual.getNumeroRegistro() == p.getNumeroRegistro()) {
                throw new ParticipacionNoValidaException("Ya existe una participacion con ese numero.");
            }
        }
    }

    public void agregarParticipacion(Participacion p) throws ParticipacionNoValidaException {
        if (p == null)
            throw new ParticipacionNoValidaException("La participacion no es valida.");

        p.validar();
        existeParticipacion(p);

        participaciones.add(p);
    }

    public void agregarApuesta(Apuesta a) throws ApuestaNoValidaException, CarreraNoValidaException {
        if (a == null)
            throw new ApuestaNoValidaException("La apuesta no es valida.");

        a.validar();
        apuestas.add(a);
        recalcularDividendos();
    }

    public void validar() throws CarreraNoValidaException {
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

    public void abrir() throws CarreraNoValidaException {
        estado.abrir(this);
    }

    private void actualizarEstado() {
        boolean todosValidos = true;

        for (Participacion p : participaciones) {
            if (!dividendoValido(p)) {
                todosValidos = false;
                break;
            }
        }

        if (todosValidos) {
            setEstado(new Estable());
        } else {
            setEstado(new Abierta());
        }
    }

    public void cerrar() throws CarreraNoValidaException {
        estado.cerrar(this);
    }

    public void finalizar(Participacion ganador) throws CarreraNoValidaException {
        this.ganador = ganador;
        estado.finalizar(this);
    }

    public int cantApuestasPorParticipacion(Participacion p) {
        int cant = 0;
        for (Apuesta a : this.apuestas) {
            if (a.getParticipacion() == p) {
                cant++;
            }
        }
        return cant;
    }

    public double totalApostadoPorCaballo(Participacion p) {
        double total = 0;
        for (Apuesta a : this.apuestas) {
            if (a.getParticipacion() == p) {
                total += a.getValor();
            }
        }

        return total;
    }

    public int cantidadApuestasPorCaballo(Participacion p) {
        int cantidad = 0;

        for (Apuesta a : this.apuestas) {
            if (a.getParticipacion() == p) {
                cantidad++;
            }
        }

        return cantidad;
    }

    public boolean dividendoValido(Participacion p) {
        return p.getDividendo() > 1 && cantApuestasPorParticipacion(p) > 0;
    }

    public void recalcularDividendos() throws CarreraNoValidaException {
        double totalCarrera = 0;
        for (Apuesta a : apuestas) {
            totalCarrera += a.getValor();
        }

        double comision = totalCarrera * Fachada.getInstancia().getComision();
        double pozo = totalCarrera - comision;

        for (Participacion p : participaciones) {
            double totalCaballo = totalApostadoPorCaballo(p);
            p.calcularDividendo(pozo, totalCaballo);
        }
        actualizarEstado();
    }

    public boolean sePuedeApostar() {
        return estado.sePuedeApostar();
    }

    public boolean estaFinalizada() {
        return estado.esFinalizada();
    }

    public Participacion buscarParticipacion(int nro) {
        for (Participacion p : this.participaciones) {
            if (p.getNumeroRegistro() == nro) {
                return p;
            }
        }
        return null;
    }

    public double getTotalApostado() {
        double total = 0;
        for (Apuesta a : this.apuestas) {
            total += a.getValor();
        }

        return total;
    }

    public double getTotalPagado() {
        if (!estaFinalizada())
            return 0; // pongo esto para que no recorra carreras qe no tienen ganaador

        double total = 0;

        for (Apuesta a : this.apuestas) {
            if (a.esGanadora(this.ganador)) {
                total += a.calcularPremio(totalApostadoPorCaballo(a.getParticipacion()));
            }
        }

        return total;
    }

    public double getComision() {
        return getTotalApostado() * Fachada.getInstancia().getComision();
    }
}
