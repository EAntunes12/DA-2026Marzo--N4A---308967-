package da.ort.obligatorioErnestoAntunes.estado;

import da.ort.obligatorioErnestoAntunes.excepciones.CarreraNoValidaException;
import da.ort.obligatorioErnestoAntunes.modelo.Carrera;

public class Cerrada implements EstadoCarrera{

    @Override
    public void abrir(Carrera carrera) throws CarreraNoValidaException {
        throw new CarreraNoValidaException("No se puede abrir una carrera que fue cerrada");
    }

    @Override
    public void cerrar(Carrera carrera) throws CarreraNoValidaException {
        throw new CarreraNoValidaException("La carrera ya está cerrada.");
    }

    @Override
    public boolean sePuedeApostar() {
        return false;
    }

    @Override
    public boolean esFinalizada() {
        return false;
    }

    @Override
    public String getNombre() {
        return "Cerrada";
    }

    @Override
    public void hacerEstable(Carrera carrera) throws CarreraNoValidaException {
        throw new CarreraNoValidaException("La carrera no se puede hacer estable si ya fue cerrada.");
    }

    @Override
    public void finalizar(Carrera carrera) throws CarreraNoValidaException {
        carrera.setEstado(new Finalizada());
    }

}
