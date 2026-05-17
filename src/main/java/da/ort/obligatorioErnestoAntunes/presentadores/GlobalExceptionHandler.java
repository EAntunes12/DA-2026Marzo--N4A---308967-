package da.ort.obligatorioErnestoAntunes.presentadores;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import da.ort.obligatorioErnestoAntunes.excepciones.ApuestaNoValidaException;
import da.ort.obligatorioErnestoAntunes.excepciones.CaballoNoValidoException;
import da.ort.obligatorioErnestoAntunes.excepciones.CarreraException;
import da.ort.obligatorioErnestoAntunes.excepciones.CarreraNoValidaException;
import da.ort.obligatorioErnestoAntunes.excepciones.JornadaNoValidaException;
import da.ort.obligatorioErnestoAntunes.excepciones.ModalidadNoValidaException;
import da.ort.obligatorioErnestoAntunes.excepciones.UsuarioExistenteException;
import da.ort.obligatorioErnestoAntunes.excepciones.UsuarioInvalidoException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UsuarioExistenteException.class)
    public ResponseEntity<String> manejarException(UsuarioExistenteException ex){
        return ResponseEntity.status(299).body(ex.getMessage());
    }

    @ExceptionHandler(UsuarioInvalidoException.class)
    public ResponseEntity<String> manejarException(UsuarioInvalidoException ex){
        return ResponseEntity.status(299).body(ex.getMessage());
    }
    
    @ExceptionHandler(ModalidadNoValidaException.class)
    public ResponseEntity<String> manejarException(ModalidadNoValidaException ex){
        return ResponseEntity.status(299).body(ex.getMessage());
    }

    @ExceptionHandler(CaballoNoValidoException.class)
    public ResponseEntity<String> manejarException(CaballoNoValidoException ex){
        return ResponseEntity.status(299).body(ex.getMessage());
    }

    @ExceptionHandler(JornadaNoValidaException.class)
    public ResponseEntity<String> manejarException(JornadaNoValidaException ex){
        return ResponseEntity.status(299).body(ex.getMessage());
    }

    @ExceptionHandler(CarreraNoValidaException.class)
    public ResponseEntity<String> manejarException(CarreraNoValidaException ex){
        return ResponseEntity.status(299).body(ex.getMessage());
    }
    @ExceptionHandler(ApuestaNoValidaException.class)
    public ResponseEntity<String> manejarException(ApuestaNoValidaException ex){
        return ResponseEntity.status(299).body(ex.getMessage());
    }

    @ExceptionHandler(CarreraException.class)
    public ResponseEntity<String> manejarException(CarreraException ex){
        return ResponseEntity.status(299).body(ex.getMessage());
    }

}
