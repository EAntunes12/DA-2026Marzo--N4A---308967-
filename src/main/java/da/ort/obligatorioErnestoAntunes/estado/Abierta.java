package da.ort.obligatorioErnestoAntunes.estado;

import da.ort.obligatorioErnestoAntunes.excepciones.CarreraNoValidaException;
import da.ort.obligatorioErnestoAntunes.modelo.Carrera;

public class Abierta implements EstadoCarrera{

    @Override
    public void abrir(Carrera carrera) throws CarreraNoValidaException {
        throw new CarreraNoValidaException("La carrera ya esta abierta");
    }

    @Override
    public void cerrar(Carrera carrera) throws CarreraNoValidaException {
        throw new CarreraNoValidaException("La carrera debe ser estable para cerrarse.");
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
        return "Abierta";
    }

    /* 
    @Override
    public void hacerEstable(Carrera carrera) throws CarreraNoValidaException {
        carrera.setEstado(new Estable());
    }
*/
    @Override
    public void finalizar(Carrera carrera) throws CarreraNoValidaException {
        throw new CarreraNoValidaException("La carrera debe estar cerrada para finalizar.");
    }

}
