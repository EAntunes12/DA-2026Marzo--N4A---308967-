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
    private double totalApostado;
    private int cantApuestas;
    private int numeroCarrera;

    public int getNumeroCarrera() {
        return numeroCarrera;
    }
    public void setNumeroCarrera(int nroCarrera) {
        this.numeroCarrera = nroCarrera;
    }
    public double getTotalApostado() {
        return totalApostado;
    }
    public int getCantApuestas() {
        return cantApuestas;
    }
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

    public ParticipacionDTO(Participacion p, Carrera c){
        this.nroRegistro = p.getNumeroRegistro();
        this.dividendo = p.getDividendo();
        this.caballo = p.getCaballo();
        this.totalApostado = c.totalApostadoPorCaballo(p);
        this.cantApuestas = c.cantApuestasPorParticipacion(p);
        this.numeroCarrera = c.getNumero();
    }

    public static List<ParticipacionDTO> fromList(List<Participacion> participaciones,Carrera carrera) {
        List<ParticipacionDTO> result = new ArrayList<>();
        for (Participacion p : participaciones) {
            result.add(new ParticipacionDTO(p, carrera));
        }
        return result;
    }

    public static ParticipacionDTO from(Participacion p, Carrera c) {
        return new ParticipacionDTO(p, c);
    }
}
