package da.ort.obligatorioErnestoAntunes.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import da.ort.obligatorioErnestoAntunes.excepciones.CarreraNoValidaException;
import da.ort.obligatorioErnestoAntunes.excepciones.JornadaNoValidaException;

public class Jornada {
    private LocalDate fecha;
    List<Carrera> carreras = new ArrayList<>();

    public List<Carrera> getCarreras() {
        return carreras;
    }

    public Jornada(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void agregarCarrera(Carrera c) throws CarreraNoValidaException {
        if (c == null) throw new CarreraNoValidaException("La carrera no es valida");

        c.setNumero(carreras.size() + 1);
        c.validar();
        existeCarrera(c);

        carreras.add(c);
    }      

    private void existeCarrera(Carrera c) throws CarreraNoValidaException {
        for (Carrera carrera : carreras) {
            boolean mismoNumero = carrera.getNumero() == c.getNumero();
            boolean mismaFecha = carrera.getFecha().equals(c.getFecha());

            if (mismoNumero && mismaFecha) {
                throw new CarreraNoValidaException("Ya existe una carrera con ese numero en la jornada.");
            }
        }
    }

    public void validar() throws JornadaNoValidaException{
        if(fecha == null){
            throw new JornadaNoValidaException("La fecha no puede ser vacia");
        }
    }

    public List<Carrera> getCarrerasDisponibles() {
        List<Carrera> listaRet = new ArrayList<>();
        
        for(Carrera c : this.carreras){
            if(c.sePuedeApostar()){
                listaRet.add(c);
            }
        }

        return listaRet;
    }

    public List<Apuesta> getApuestas() {
        List<Apuesta> listaRet = new ArrayList<>();

        for(Carrera c : this.carreras){
            c.getApuestas();
        }

        return listaRet;
    }

    public List<Carrera> getCarrerasNoFinalizadas() {
        List<Carrera> listaRet = new ArrayList<>();

        for(Carrera c : this.carreras){
            if(!c.estaFinalizada()){
                listaRet.add(c);
            }
        }

        return listaRet;
    }

    public List<Carrera> getCarrerasFinalizadas() {
        List<Carrera> listaRet = new ArrayList<>();

        for(Carrera c : this.carreras){
            if(c.estaFinalizada()){
                listaRet.add(c);
            }
        }

        return listaRet;
    }

    public Carrera buscarCarrera(int id){
        for(Carrera c : this.carreras){
            if(c.getId() == id){
                return c;
            }
        }
        return null;
    }

    public Participacion buscarParticipacion(int nro) {
        for(Carrera c : this.carreras){
            Participacion p = c.buscarParticipacion(nro);

            if(p!=null){
                return p;
            }
        }

        return null;
    }

    public double getTotalApostado(){
        double total = 0;
        for(Carrera c : this.carreras){
            total += c.getTotalApostado();
        }

        return total;
    }

    public double getTotalPagado(){
        double total = 0;
        for(Carrera c : this.carreras){
            total += c.getTotalPagado();
        }

        return total;
    }

    public double getTotalComision(){
        double total = 0;
        for(Carrera c : this.carreras){
            total += c.getComision();
        }
        return total;
    }

    public double getTotalApostadoPorJugador(String nombreCompleto) {
        double total = 0;
        for(Carrera c : this.carreras){
            total += c.getTotalApostadoPorJugador(nombreCompleto);
        }
        return total;
    }

    public double getTotalGanadoPorJugador(String nombreCompleto) {
        double total = 0;
        for(Carrera c : this.carreras){
            total += c.getTotalGanadoPorJugador(nombreCompleto);
        }
        return total;
    }

}
