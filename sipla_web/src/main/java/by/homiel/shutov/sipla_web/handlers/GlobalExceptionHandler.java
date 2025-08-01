package by.homiel.shutov.sipla_web.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handle(IOException ex) {
        if (ex != null) {
            return ResponseEntity.badRequest().body("We have some problems: " + ex.getLocalizedMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handle(IllegalArgumentException ex) {
        if (ex != null) {
            return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
    }
}
