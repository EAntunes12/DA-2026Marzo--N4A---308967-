package da.ort.obligatorioErnestoAntunes.modelo;

import da.ort.obligatorioErnestoAntunes.excepciones.ModalidadNoValidaException;

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

    @Override
    public void validar() throws ModalidadNoValidaException {
        if(this.getNombre() == null || this.getNombre().isBlank() || !this.getNombre().equals("Super")){
            throw new ModalidadNoValidaException("El nombre ingresado no es correcto");
        }
    }
}
