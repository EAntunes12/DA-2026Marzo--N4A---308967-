package da.ort.obligatorioErnestoAntunes.modelo;

public abstract class Modalidad {
    private String nombre;

    public Modalidad(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract double calcularCosto(double monto);

    public abstract double calcularPago(
            double monto,
            double dividendo,
            double totalApostadoCaballo
    );
}
