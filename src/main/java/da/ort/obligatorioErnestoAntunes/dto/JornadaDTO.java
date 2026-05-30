package da.ort.obligatorioErnestoAntunes.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import da.ort.obligatorioErnestoAntunes.modelo.Administrador;
import da.ort.obligatorioErnestoAntunes.modelo.Jornada;

public class JornadaDTO {
    private LocalDate fecha;

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public JornadaDTO() {
    }

    public JornadaDTO(Jornada j) {
        this.fecha = j.getFecha();
    }
    
    public static List<JornadaDTO> fromList(List<Jornada> jornadas) {
        List<JornadaDTO> result = new ArrayList<>();
        for (Jornada j : jornadas) {
            result.add(new JornadaDTO(j));
        }
        return result;
    }
}
