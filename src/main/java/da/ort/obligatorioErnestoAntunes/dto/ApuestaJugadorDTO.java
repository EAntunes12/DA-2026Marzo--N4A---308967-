package da.ort.obligatorioErnestoAntunes.dto;

import java.util.ArrayList;
import java.util.List;

import da.ort.obligatorioErnestoAntunes.modelo.Apuesta;
import da.ort.obligatorioErnestoAntunes.modelo.Carrera;
import da.ort.obligatorioErnestoAntunes.modelo.Participacion;

public class ApuestaJugadorDTO {
    private ApuestaDTO apuesta;
    private String estadoCarrera;

    public ApuestaDTO getApuesta() {
        return apuesta;
    }

    public void setApuesta(ApuestaDTO apuesta) {
        this.apuesta = apuesta;
    }

    public String getEstadoCarrera() {
        return estadoCarrera;
    }

    public void setEstadoCarrera(String estadoCarrera) {
        this.estadoCarrera = estadoCarrera;
    }

    public ApuestaJugadorDTO(Apuesta a) {
        this.apuesta = ApuestaDTO.from(a);
        this.estadoCarrera = a.getParticipacion().getCarrera().getEstado().getNombre(); //No creo que esto sea muy experto, tendria que revisarlo
    }

    public static List<ApuestaJugadorDTO> fromList(List<Apuesta> apuestas) {
        List<ApuestaJugadorDTO> result = new ArrayList<>();

        for (Apuesta a : apuestas) {
            result.add(new ApuestaJugadorDTO(a));
        }

        return result;
    }
}
