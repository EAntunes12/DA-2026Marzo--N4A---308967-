package da.ort.obligatorioErnestoAntunes.modelo;

public class Triple extends Modalidad {
    public Triple() {
        super("Triple");
    }

    @Override
    public double calcularCosto(double monto) {
        return monto * 1.5;
    }

    @Override
    public double calcularPago(double monto,
                               double dividendo,
                               double totalApostadoCaballo) {

        if (totalApostadoCaballo >= 100000) {
            return monto * dividendo * 3;
        }

        return monto * dividendo * 2;
    }
}
