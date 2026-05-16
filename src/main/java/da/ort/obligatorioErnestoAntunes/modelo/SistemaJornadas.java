package da.ort.obligatorioErnestoAntunes.modelo;

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
    };

    public void agregarJornada(Jornada j) throws JornadaNoValidaException {
        if (j == null)
            throw new JornadaNoValidaException("La jornada no es valida.");

        j.validar();
        jornadas.add(j);
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

}
