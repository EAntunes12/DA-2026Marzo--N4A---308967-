package da.ort.obligatorioErnestoAntunes.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import da.ort.obligatorioErnestoAntunes.dto.ApuestaDTO;
import da.ort.obligatorioErnestoAntunes.dto.CarreraDTO;
import da.ort.obligatorioErnestoAntunes.excepciones.CarreraNoValidaException;
import da.ort.obligatorioErnestoAntunes.excepciones.JornadaNoValidaException;

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

        //Ordeno las jornadas dde mas vieja a mas nueva.
        jornadas.sort((j1, j2) -> j1.getFecha().compareTo(j2.getFecha())); 
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

    public List<Carrera> getCarrerasDisponibles(){
        List<Carrera> listaRet = new ArrayList<>();

        for(Jornada j : this.jornadas){
            listaRet.addAll(j.getCarrerasDisponibles());            
        }

        return listaRet;
    }

    public List<Apuesta> getTodasLasApuestas(){
        List<Apuesta> listaRet = new ArrayList<>();

        for(Jornada j : this.jornadas){
            listaRet.addAll(j.getApuestas());
        }

        return listaRet;
    }

    public List<Apuesta> getApuestasPorJugador(String jugador){
        List<Apuesta> listaRet = new ArrayList<>();
        List<Apuesta> apuestas = getTodasLasApuestas();

        for(Apuesta a : apuestas){
            if(a.getJugador().getNombre().equals(jugador)){
                listaRet.add(a);
            }
        }
        return listaRet;
    }

}
