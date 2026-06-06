package da.ort.obligatorioErnestoAntunes.estado;

import da.ort.obligatorioErnestoAntunes.excepciones.CarreraNoValidaException;
import da.ort.obligatorioErnestoAntunes.modelo.Carrera;

public interface EstadoCarrera {
    
    void abrir(Carrera carrera) throws CarreraNoValidaException;
    void hacerEstable(Carrera carrera) throws CarreraNoValidaException;
    void cerrar(Carrera carrera) throws CarreraNoValidaException;
    void finalizar(Carrera carrera) throws CarreraNoValidaException;
    boolean sePuedeApostar();
    boolean esFinalizada();
    String getNombre();
}
