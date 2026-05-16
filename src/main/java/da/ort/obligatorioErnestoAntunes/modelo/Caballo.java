package da.ort.obligatorioErnestoAntunes.modelo;

import da.ort.obligatorioErnestoAntunes.excepciones.CaballoNoValidoException;

public class Caballo {
    private String nombre;

    public Caballo(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void validar() throws CaballoNoValidoException{
        if(nombre == null || nombre.isBlank()){
            throw new CaballoNoValidoException("El nombre no puede ser vacio");
        }
    }
}
