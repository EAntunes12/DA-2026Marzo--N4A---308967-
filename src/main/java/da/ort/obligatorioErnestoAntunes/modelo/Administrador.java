package da.ort.obligatorioErnestoAntunes.modelo;

import da.ort.obligatorioErnestoAntunes.excepciones.UsuarioInvalidoException;

public class Administrador extends Usuario {

    public Administrador(String nombre, String password, String nombreCompleto) {
        super(nombre, password, nombreCompleto);
    }

    @Override
    public void validarSaldo() throws UsuarioInvalidoException {
        //El admin no tiene saldo.
    }


    
}
