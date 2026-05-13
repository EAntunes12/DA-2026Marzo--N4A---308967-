package da.ort.obligatorioErnestoAntunes.modelo;

public class Simple extends Modalidad {
    public Simple() {
        super("Simple");
    }

    @Override
    public double calcularCosto(double monto) {
        return monto;
    }

    @Override
    public double calcularPago(double monto,
                               double dividendo,
                               double totalApostadoCaballo) {

        return monto * dividendo;
    }
}
