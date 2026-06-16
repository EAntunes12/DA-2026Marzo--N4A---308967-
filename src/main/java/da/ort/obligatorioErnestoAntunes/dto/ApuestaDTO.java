package da.ort.obligatorioErnestoAntunes.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import da.ort.obligatorioErnestoAntunes.estado.EstadoCarrera;
import da.ort.obligatorioErnestoAntunes.modelo.Apuesta;
import da.ort.obligatorioErnestoAntunes.modelo.Caballo;
import da.ort.obligatorioErnestoAntunes.modelo.Apuesta;
import da.ort.obligatorioErnestoAntunes.modelo.Modalidad;
import da.ort.obligatorioErnestoAntunes.modelo.Participacion;

public class ApuestaDTO {
    private int id;
    private double valor;
    private String jugador;
    private ParticipacionDTO participacion;
    private Modalidad modalidad;
    private LocalDate fecha;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public ParticipacionDTO getParticipacion() {
        return participacion;
    }

    public void setParticipacion(ParticipacionDTO participacion) {
        this.participacion = participacion;
    }

    public Modalidad getModalidad() {
        return modalidad;
    }

    public void setModalidad(Modalidad modalidad) {
        this.modalidad = modalidad;
    }

    public ApuestaDTO() {
    }

    public ApuestaDTO(Apuesta a) {
        this.id = a.getId();
        this.valor = a.getValor();
        this.jugador = a.getJugador().getNombre();
        this.participacion = ParticipacionDTO.from(a.getParticipacion(), a.getParticipacion().getCarrera());
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

    public static ApuestaDTO from(Apuesta apuesta) {
        return new ApuestaDTO(apuesta);
    }

}
