package med.voll.api.domain.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> handleError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleError400(MethodArgumentNotValidException e){
        var errors = e.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(DataValidationError::new).toList());
    }

    private record DataValidationError(String campo, String message){
        public DataValidationError(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
