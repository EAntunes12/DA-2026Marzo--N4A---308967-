package da.ort.obligatorioErnestoAntunes.dto;

import java.util.ArrayList;
import java.util.List;

import da.ort.obligatorioErnestoAntunes.modelo.Administrador;

public class AdminDTO {
    private String nombre;
    private String nombreCompleto;

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

    public AdminDTO(){}

    public AdminDTO(Administrador admin){
        this.nombre = admin.getNombre();
        this.nombreCompleto = admin.getNombreCompleto();
    }

      public static List<AdminDTO> fromList(List<Administrador> admins) {
        List<AdminDTO> result = new ArrayList<>();
        for (Administrador admin : admins) {
            result.add(new AdminDTO(admin));
        }
        return result;
    }
}
