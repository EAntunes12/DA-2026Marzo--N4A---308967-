package da.ort.obligatorioErnestoAntunes.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import da.ort.obligatorioErnestoAntunes.estado.EstadoCarrera;
import da.ort.obligatorioErnestoAntunes.modelo.Carrera;
import da.ort.obligatorioErnestoAntunes.modelo.Participacion;

public class CarreraDTO {
    private int id;   
    private LocalDate fecha;
    private int numero;
    private String nombre;
    private EstadoCarrera estado;
    private List<ParticipacionDTO> participaciones;
    private double totalApostado;
    private double totalPagado;
    private ParticipacionDTO ganador;
    private int cantApuestas;
    
    public int getCantApuestas() {
        return cantApuestas;
    }

    public double getTotalApostado() {
        return totalApostado;
    }

    public void setTotalApostado(double totalApostado) {
        this.totalApostado = totalApostado;
    }

    public double getTotalPagado() {
        return totalPagado;
    }

    public void setTotalPagado(double totalPagado) {
        this.totalPagado = totalPagado;
    }

    public ParticipacionDTO getGanador() {
        return ganador;
    }

    public void setGanador(ParticipacionDTO ganador) {
        this.ganador = ganador;
    }

    public CarreraDTO() {
    }

    public CarreraDTO(Carrera c) {
        this.id = c.getId();
        this.fecha = c.getFecha();
        this.numero = c.getNumero();
        this.nombre = c.getNombre();
        this.estado = c.getEstado();
        this.participaciones = ParticipacionDTO.fromList(c.getParticipaciones(), c);
        this.totalApostado = c.getTotalApostado();
        this.totalPagado = c.getTotalPagado();
        this.ganador = c.getGanador() != null ? new ParticipacionDTO(c.getGanador(), c) : null;
        this.cantApuestas = c.getApuestas().size();
    }
    public LocalDate getFecha() {
        return fecha;
    }

    public int getId() {
        return id;
    }    
    public void setId(int id) {
        this.id = id;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public List<ParticipacionDTO> getParticipaciones() {
        return participaciones;
    }
    public void setParticipaciones(List<ParticipacionDTO> participaciones) {
        this.participaciones = participaciones;
    }

    public String getNombreParticipacion(Participacion p){
        return p.getCaballo().getNombre();
    }

    public EstadoCarrera getEstado() {
        return estado;
    }

    public void setEstado(EstadoCarrera estado) {
        this.estado = estado;
    }
    

    public int getNroParticipacion(Participacion p){
        return p.getNumeroRegistro();
    }

    public double getDividendoParticipacion(Participacion p){
        return p.getDividendo();
    }

    public static List<CarreraDTO> fromList(List<Carrera> carreras) {
        List<CarreraDTO> result = new ArrayList<>();
        for (Carrera c : carreras) {
            result.add(new CarreraDTO(c));
        }
        return result;
    }

    public static CarreraDTO from(Carrera carrera) {
        return new CarreraDTO(carrera);
    }
}
