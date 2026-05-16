package da.ort.obligatorioErnestoAntunes.modelo;

import java.util.ArrayList;
import java.util.List;

import da.ort.obligatorioErnestoAntunes.excepciones.CaballoNoValidoException;
import da.ort.obligatorioErnestoAntunes.excepciones.ModalidadNoValidaException;

public class SistemaApuestas {
    private double comision;

    List<Modalidad> modalidades = new ArrayList<>();
    List<Caballo> caballos = new ArrayList<>();

    public List<Caballo> getCaballos() {
        return caballos;
    }

    public List<Modalidad> getModalidades() {
        return modalidades;
    }

    public SistemaApuestas(double comision) {
        this.comision = comision;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    public void agregarModalidad(Modalidad m) throws ModalidadNoValidaException{
        if(m == null) throw new ModalidadNoValidaException("La modalidad no puede ser vacia.");

        m.validar();
        modalidades.add(m);
    }

    private void existeCaballo(Caballo c) throws CaballoNoValidoException{
        for(Caballo caballo : caballos){
            if(caballo.getNombre().equals(c.getNombre())){
                throw new CaballoNoValidoException("El caballo ya existe");
            }
        }
    }

    public void agregarCaballos(Caballo c) throws CaballoNoValidoException{
        if(c == null) throw new CaballoNoValidoException("EL caballo no es valido.");

        c.validar();
        existeCaballo(c);
        caballos.add(c);
    }
}
