package da.ort.obligatorioErnestoAntunes.modelo;

import da.ort.obligatorioErnestoAntunes.excepciones.ModalidadNoValidaException;

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

    @Override
    public void validar() throws ModalidadNoValidaException {
        if(this.getNombre() == null || this.getNombre().isBlank() || !this.getNombre().equals("Simple")){
            throw new ModalidadNoValidaException("El nombre ingresado no es correcto");
        }
    }


}
