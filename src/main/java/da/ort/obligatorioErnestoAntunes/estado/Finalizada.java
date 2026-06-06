package da.ort.obligatorioErnestoAntunes.estado;

import da.ort.obligatorioErnestoAntunes.excepciones.CarreraNoValidaException;
import da.ort.obligatorioErnestoAntunes.modelo.Carrera;

public class Finalizada implements EstadoCarrera{

    @Override
    public void abrir(Carrera carrera) throws CarreraNoValidaException {
        throw new CarreraNoValidaException("No se puede abrir una carrera finalizada");
    }
/*
    @Override
    public void hacerEstable(Carrera carrera) throws CarreraNoValidaException {
        throw new CarreraNoValidaException("No se puede hacer estable una carrera finalizada");
    }
*/
    @Override
    public void cerrar(Carrera carrera) throws CarreraNoValidaException {
        throw new CarreraNoValidaException("No se puede cerrar una carrera finalizada");
    }

    @Override
    public void finalizar(Carrera carrera) throws CarreraNoValidaException {
        throw new CarreraNoValidaException("La carrera ya está finalizada");
    }

    @Override
    public boolean sePuedeApostar() {
        return false;
    }

    @Override
    public boolean esFinalizada() {
        return true;
    }

    @Override
    public String getNombre() {
        return "Finalizada";
    }

}
