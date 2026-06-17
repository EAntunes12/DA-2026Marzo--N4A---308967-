package da.ort.obligatorioErnestoAntunes.estado;

import da.ort.obligatorioErnestoAntunes.excepciones.CarreraNoValidaException;
import da.ort.obligatorioErnestoAntunes.modelo.Carrera;

public class Estable implements EstadoCarrera{
    @Override
    public void abrir(Carrera carrera) throws CarreraNoValidaException {
        throw new CarreraNoValidaException("No se puede abrir una carrera estable");
    }
/* 
    @Override
    public void hacerEstable(Carrera carrera) throws CarreraNoValidaException {
        throw new CarreraNoValidaException("La carrera ya es estable");
    }
*/
    @Override
    public void cerrar(Carrera carrera) throws CarreraNoValidaException {
        carrera.setEstado(new Cerrada());
    }

    @Override
    public void finalizar(Carrera carrera) throws CarreraNoValidaException {
        throw new CarreraNoValidaException("No se puedde finalizar una carrera estable");
    }

    @Override
    public boolean sePuedeApostar() {
        return true;
    }

    @Override
    public boolean esFinalizada() {
        return false;
    }

    @Override
    public String getNombre() {
        return "Estable";
    }
}
