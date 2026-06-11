package da.ort.obligatorioErnestoAntunes.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import da.ort.obligatorioErnestoAntunes.dto.ApuestaDTO;
import da.ort.obligatorioErnestoAntunes.dto.CarreraDTO;
import da.ort.obligatorioErnestoAntunes.excepciones.ApuestaNoValidaException;
import da.ort.obligatorioErnestoAntunes.excepciones.CaballoNoValidoException;
import da.ort.obligatorioErnestoAntunes.excepciones.CarreraNoValidaException;
import da.ort.obligatorioErnestoAntunes.excepciones.JornadaNoValidaException;
import da.ort.obligatorioErnestoAntunes.excepciones.ParticipacionNoValidaException;

public class SistemaJornadas {
    List<Jornada> jornadas = new ArrayList<>();

    public List<Jornada> getJornadas() {
        return jornadas;
    }

    public void agregarJornada(Jornada j) throws JornadaNoValidaException {
        if (j == null)
            throw new JornadaNoValidaException("La jornada no es valida.");

        j.validar();
        jornadas.add(j);

        // Ordeno las jornadas dde mas vieja a mas nueva.
        jornadas.sort((j1, j2) -> j1.getFecha().compareTo(j2.getFecha()));
    }

    public Jornada obtenerJornadaActual() {
        LocalDate hoy = LocalDate.now();
        Jornada mejor = null;

        for (Jornada j : this.jornadas) {
            if (!j.getFecha().isAfter(hoy)) {
                if (mejor == null || j.getFecha().isAfter(mejor.getFecha())) {
                    mejor = j;
                }
            }
        }
        return mejor;
    }

    public Jornada siguienteJornada(Jornada actual) {
        int posicion = this.jornadas.indexOf(actual);

        if (posicion < jornadas.size() - 1) {
            return jornadas.get(posicion + 1);
        }

        return actual;
    }

    public Jornada anteriorJornada(Jornada actual) {
        int pos = jornadas.indexOf(actual);

        if (pos > 0) {
            return jornadas.get(pos - 1);
        }
        return actual;
    }

    public List<Carrera> getCarrerasDisponibles(){
        List<Carrera> listaRet = new ArrayList<>();

        for (Jornada j : this.jornadas) {
            listaRet.addAll(j.getCarrerasDisponibles());
        }
        return listaRet;
    }

    public List<Apuesta> getTodasLasApuestas() {
        List<Apuesta> listaRet = new ArrayList<>();

        for (Jornada j : this.jornadas) {
            listaRet.addAll(j.getApuestas());
        }

        return listaRet;
    }

    public List<Apuesta> getApuestasPorJugador(String jugador){
        List<Apuesta> listaRet = new ArrayList<>();
        List<Apuesta> apuestas = getTodasLasApuestas();
        for (Apuesta a : apuestas) {
            if (a.getJugador().getNombre().equalsIgnoreCase(jugador)) {
                listaRet.add(a);
            }
        }

        listaRet.sort((a1, a2) -> a1.getFecha().compareTo(a2.getFecha()));
        return listaRet;
    }

    public List<Carrera> getCarrerasFinalizadasPorJornada(LocalDate fecha) throws JornadaNoValidaException {
        List<Carrera> carreras = new ArrayList<>();
        Jornada j = buscarJornadaPorFecha(fecha);
        carreras.addAll(j.getCarrerasFinalizadas());
        return carreras;

    }

    public List<Carrera> getCarrerasDisponiblesPorJornada(LocalDate fecha) throws JornadaNoValidaException {
        List<Carrera> carreras = new ArrayList<>();
        Jornada j = buscarJornadaPorFecha(fecha);
        carreras.addAll(j.getCarrerasNoFinalizadas());
        return carreras;
    }

    public int getCantCarrerasJornada(LocalDate fecha) throws JornadaNoValidaException {
        return getCarrerasDisponiblesPorJornada(fecha).size() + getCarrerasFinalizadasPorJornada(fecha).size();
    }

    public Carrera buscarCarrera(int id) throws CarreraNoValidaException {
        for (Jornada j : this.jornadas) {
            Carrera carrera = j.buscarCarrera(id);

            if (carrera != null) {
                return carrera;
            }
        }
        throw new CarreraNoValidaException("No existe una carrera con ese ID");
    }

    public Participacion buscarParticipacion(int nro) throws ParticipacionNoValidaException {
        for (Jornada j : this.jornadas) {
            Participacion p = j.buscarParticipacion(nro);

            if (p != null) {
                return p;
            }
        }
        throw new ParticipacionNoValidaException("No existe una participoacion con ese nro");
    }

    public void abrirCarrera(int id) throws CarreraNoValidaException {
        Carrera c = buscarCarrera(id);
        c.abrir();
    }

    public void cerrarCarrera(int id) throws CarreraNoValidaException {
        Carrera c = buscarCarrera(id);
        c.cerrar();
    }

    public void finalizarCarrera(int id, int nroRegistroPart)
            throws ParticipacionNoValidaException, CarreraNoValidaException {
        Carrera carrera = buscarCarrera(id);
        Participacion ganador = buscarParticipacion(nroRegistroPart);
        carrera.finalizar(ganador);

        // Le paga a los jugadores que apostaron al ganador de la carrera, pero no estoy
        // 100% seguro de que funcione bien.
        for (Apuesta a : carrera.getApuestas()) {
            if (a.esGanadora(ganador)) {

                double premio = a.calcularPremio(
                        carrera.totalApostadoPorCaballo(ganador));

                a.getJugador().pagarPremio(premio);
            }
        }
    }

    private Jornada buscarJornadaPorFecha(LocalDate fecha) throws JornadaNoValidaException {
        for (Jornada j : this.jornadas) {
            if (j.getFecha().equals(fecha)) {
                return j;
            }
        }
        throw new JornadaNoValidaException("No existe una jornada con esa fecha.");
    }

    public double getTotalApostadoPorJornada(LocalDate fechaJornada) throws JornadaNoValidaException {
        Jornada j = buscarJornadaPorFecha(fechaJornada);
        return j.getTotalApostado();
    }

    public double getTotalPagadoPorJornada(LocalDate fechaJornada) throws JornadaNoValidaException {
        Jornada j = buscarJornadaPorFecha(fechaJornada);
        return j.getTotalPagado();
    }

    public double getBalanceJornada(LocalDate fechaJornada) throws JornadaNoValidaException {
        return getTotalApostadoPorJornada(fechaJornada) - getTotalPagadoPorJornada(fechaJornada);
    }

    public double getTotalComisionJornada(LocalDate fechaJornada) throws JornadaNoValidaException {
        Jornada j = buscarJornadaPorFecha(fechaJornada);
        return j.getTotalComision();
    }

    public double getTotalApostadoPorJugador(String nombreCompleto) {
        double total = 0;
        for (Jornada j : this.jornadas) {
            total += j.getTotalApostadoPorJugador(nombreCompleto);
        }

        return total;
    }


    //aca repito mucho coddigo pero no se como implementarlo de otra manera
    public double getTotalGanadoPorJugador(String nombreCompleto) {
        double total = 0;
            for (Jornada j : this.jornadas) {
                total += j.getTotalGanadoPorJugador(nombreCompleto);
            }
        return total;
    }

    public Participacion buscarParticipacion(String nombreCaballo) throws ParticipacionNoValidaException{
        for(Jornada j : this.jornadas){
            Participacion p = j.buscarParticipacion(nombreCaballo);

            if(p != null){
                return p;
            }
        }
        throw new ParticipacionNoValidaException("No existe un caballo con ese nombre");
    }

    public Apuesta buscarApuesta(int idApuesta) throws ApuestaNoValidaException{
        for(Jornada j : this.jornadas){
            Apuesta apuesta = j.buscarApuesta(idApuesta);

            if(apuesta != null){
                return apuesta;
            }
        }
        throw new ApuestaNoValidaException("No existe la apuesta");
    }
}
