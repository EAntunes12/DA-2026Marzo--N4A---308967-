package da.ort.obligatorioErnestoAntunes.dto;

import java.util.ArrayList;
import java.util.List;

import da.ort.obligatorioErnestoAntunes.modelo.Caballo;

public class CaballoDTO {
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public CaballoDTO(Caballo c){
        this.nombre = c.getNombre();
    }

    public static List<CaballoDTO> fromList(List<Caballo> caballos) {
        List<CaballoDTO> result = new ArrayList<>();
        for (Caballo c : caballos) {
            result.add(new CaballoDTO(c));
        }
        return result;
    }

    public static CaballoDTO from(Caballo caballo) {
        return new CaballoDTO(caballo);
    }
}
