package da.ort.obligatorioErnestoAntunes.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import da.ort.obligatorioErnestoAntunes.excepciones.CarreraNoValidaException;
import da.ort.obligatorioErnestoAntunes.excepciones.JornadaNoValidaException;

public class SistemaJornadas {
    List<Jornada> jornadas = new ArrayList<>();
    List<Carrera> carreras = new ArrayList<>();

    public List<Carrera> getCarreras() {
        return carreras;
    }

    public List<Jornada> getJornadas() {
        return jornadas;
    }

    public void agregarJornada(Jornada j) throws JornadaNoValidaException {
        if (j == null)
            throw new JornadaNoValidaException("La jornada no es valida.");

        j.validar();
        jornadas.add(j);

        //Ordeno las jornadas dde mas vieja a mas nueva.
        jornadas.sort((j1, j2) -> j1.getFecha().compareTo(j2.getFecha())); 
    }

    public void agregarCarrera(Carrera c) throws CarreraNoValidaException {
        if (c == null)
            throw new CarreraNoValidaException("La carrera no es valida");

        c.validar();
        existeCarrera(c);

        carreras.add(c);
    }

    private void existeCarrera(Carrera c) throws CarreraNoValidaException {
        for (Carrera carrera : carreras) {
            boolean mismoNumero = carrera.getNumero() == c.getNumero();
            boolean mismaFecha = carrera.getFecha()
                    .equals(c.getFecha());
            if (mismoNumero && mismaFecha) {
                throw new CarreraNoValidaException(
                        "Ya existe una carrera con ese numero en la jornada.");
            }
        }
    }

    public Jornada obtenerJornadaActual(){
        LocalDate hoy = LocalDate.now();
        Jornada mejor = null;

        for(Jornada j : this.jornadas){
            if(!j.getFecha().isAfter(hoy)){
                if(mejor == null || j.getFecha().isAfter(mejor.getFecha())){
                    mejor = j;
                }
            }
        }
        return mejor;
    }

    public Jornada siguienteJornada(Jornada actual){
        int posicion = this.jornadas.indexOf(actual);

        if(posicion < jornadas.size() - 1){
            return jornadas.get(posicion + 1);
        }

        return actual;
    }

    public Jornada anteriorJornada(Jornada actual) {
        int pos = jornadas.indexOf(actual);
        
        if(pos > 0) {
            return jornadas.get(pos - 1);
        }
        return actual;
    }

}
