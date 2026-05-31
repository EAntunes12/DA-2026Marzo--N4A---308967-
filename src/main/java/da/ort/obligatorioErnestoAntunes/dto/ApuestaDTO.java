package da.ort.obligatorioErnestoAntunes.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import da.ort.obligatorioErnestoAntunes.modelo.Apuesta;
import da.ort.obligatorioErnestoAntunes.modelo.Apuesta;
import da.ort.obligatorioErnestoAntunes.modelo.Modalidad;
import da.ort.obligatorioErnestoAntunes.modelo.Participacion;

public class ApuestaDTO {
    private double valor;
    private String jugador;
    private Participacion participacion;
    private Modalidad modalidad;
    private LocalDate fecha;

    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public String getJugador() {
        return jugador;
    }
    public void setJugador(String jugador) {
        this.jugador = jugador;
    }
    public Participacion getParticipacion() {
        return participacion;
    }
    public void setParticipacion(Participacion participacion) {
        this.participacion = participacion;
    }
    public Modalidad getModalidad() {
        return modalidad;
    }
    public void setModalidad(Modalidad modalidad) {
        this.modalidad = modalidad;
    }

    public ApuestaDTO(){}

    public ApuestaDTO(Apuesta a){
        this.valor = a.getValor();
        this.jugador = a.getJugador().getNombre();
        this.participacion = a.getParticipacion();
        this.modalidad = a.getModalidad();
        this.fecha = a.getFecha();
    }

    public static List<ApuestaDTO> fromList(List<Apuesta> apuestas) {
        List<ApuestaDTO> result = new ArrayList<>();
        for (Apuesta a : apuestas) {
            result.add(new ApuestaDTO(a));
        }
        return result;
    }




}
