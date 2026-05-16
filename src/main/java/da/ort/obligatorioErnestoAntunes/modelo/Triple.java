package da.ort.obligatorioErnestoAntunes.modelo;

import da.ort.obligatorioErnestoAntunes.excepciones.ModalidadNoValidaException;

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

    @Override
    public void validar() throws ModalidadNoValidaException {
        if(this.getNombre() == null || this.getNombre().isBlank() || !this.getNombre().equals("Triple")){
            throw new ModalidadNoValidaException("El nombre ingresado no es correcto");
        }
    }
}
