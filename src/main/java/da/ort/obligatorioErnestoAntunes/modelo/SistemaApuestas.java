package da.ort.obligatorioErnestoAntunes.modelo;

import java.util.ArrayList;
import java.util.List;

import da.ort.obligatorioErnestoAntunes.excepciones.ApuestaNoValidaException;
import da.ort.obligatorioErnestoAntunes.excepciones.CaballoNoValidoException;
import da.ort.obligatorioErnestoAntunes.excepciones.CarreraNoValidaException;
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

    public Apuesta crearApuesta(double valorApuesta, Jugador jugador, Participacion part, String mod, Carrera carrera) throws ModalidadNoValidaException, ApuestaNoValidaException, CarreraNoValidaException {
        Modalidad modalidad = buscarModalidad(mod);
        Apuesta apuesta = new Apuesta(valorApuesta, jugador, part, modalidad);
        carrera.agregarApuesta(apuesta);
        jugador.descontarSaldo(valorApuesta);
        carrera.recalcularDividendos();               
        return apuesta;
    }

    public Modalidad buscarModalidad(String mod) throws ModalidadNoValidaException {
        for(Modalidad m : this.modalidades){
            if(m.getNombre().equals(mod)){
                return m;
            }
        }
        throw new ModalidadNoValidaException("No existe la modalidad");
    }

    public double getPremioSiEsGanadora(Carrera c, Modalidad m, double valor, Participacion p) {
        return m.calcularPago(valor, p.getDividendo(), c.totalApostadoPorCaballo(p));
    }
}
