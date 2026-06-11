package da.ort.obligatorioErnestoAntunes.dto;

import java.util.ArrayList;
import java.util.List;

import da.ort.obligatorioErnestoAntunes.modelo.Administrador;
import da.ort.obligatorioErnestoAntunes.modelo.Carrera;
import da.ort.obligatorioErnestoAntunes.modelo.Jugador;

public class JugadorDTO {
    private String nombre;
    private String nombreCompleto;
    private double saldo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public JugadorDTO(Jugador j){
        this.nombre = j.getNombre();
        this.nombreCompleto = j.getNombreCompleto();
        this.saldo = j.getSaldo();
    }

    public static List<JugadorDTO> fromList(List<Jugador> jugadores) {
        List<JugadorDTO> result = new ArrayList<>();
        for (Jugador jug : jugadores) {
            result.add(new JugadorDTO(jug));
        }
        return result;
    }

    public static JugadorDTO from(Jugador j) {
        return new JugadorDTO(j);
    }
}
