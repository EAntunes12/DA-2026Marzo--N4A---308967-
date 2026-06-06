package da.ort.obligatorioErnestoAntunes.dto;

import java.util.ArrayList;
import java.util.List;

import da.ort.obligatorioErnestoAntunes.modelo.Caballo;
import da.ort.obligatorioErnestoAntunes.modelo.Carrera;
import da.ort.obligatorioErnestoAntunes.modelo.Participacion;

public class ParticipacionDTO {
    private int nroRegistro;
    private double dividendo;
    private Caballo caballo;

    public int getNroRegistro() {
        return nroRegistro;
    }
    public void setNroRegistro(int nroRegistro) {
        this.nroRegistro = nroRegistro;
    }
    public double getDividendo() {
        return dividendo;
    }
    public void setDividendo(double dividendo) {
        this.dividendo = dividendo;
    }
    public Caballo getCaballo() {
        return caballo;
    }
    public void setCaballo(Caballo caballo) {
        this.caballo = caballo;
    }

    public ParticipacionDTO(Participacion p){
        this.nroRegistro = p.getNumeroRegistro();
        this.dividendo = p.getDividendo();
        this.caballo = p.getCaballo();
    }

    public static List<ParticipacionDTO> fromList(List<Participacion> participaciones) {
        List<ParticipacionDTO> result = new ArrayList<>();
        for (Participacion p : participaciones) {
            result.add(new ParticipacionDTO(p));
        }
        return result;
    }

}
