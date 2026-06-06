package da.ort.obligatorioErnestoAntunes.estado;

import da.ort.obligatorioErnestoAntunes.excepciones.CarreraNoValidaException;
import da.ort.obligatorioErnestoAntunes.modelo.Carrera;

public class Definida implements EstadoCarrera{

    @Override
    public void abrir(Carrera carrera) throws CarreraNoValidaException {
        carrera.setEstado(new Abierta());
    }

    /*
    @Override
    public void hacerEstable(Carrera carrera) throws CarreraNoValidaException {
        throw new CarreraNoValidaException("No se puedde hacer estable una carrera definida");
    }
*/
    @Override
    public void cerrar(Carrera carrera) throws CarreraNoValidaException {
        throw new CarreraNoValidaException("No se puede cerrar una carrera definida.");
    }

    @Override
    public void finalizar(Carrera carrera) throws CarreraNoValidaException {
        throw new CarreraNoValidaException("No se puedde finalizar una carrera definida");
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
        return "Definida";
    }

}
