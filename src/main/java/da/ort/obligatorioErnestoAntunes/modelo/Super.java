package da.ort.obligatorioErnestoAntunes.modelo;

public class Super extends Modalidad {
    public Super() {
        super("Super");
    }

    @Override
    public double calcularCosto(double monto) {
        return monto * 2;
    }

    @Override
    public double calcularPago(double monto,
                               double dividendo,
                               double totalApostadoCaballo) {

        if (dividendo >= 2) {
            return monto * dividendo * 3;
        }

        return monto * dividendo * 4;
    }
}
