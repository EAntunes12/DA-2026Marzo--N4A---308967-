package da.ort.obligatorioErnestoAntunes.presentadores;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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


}
