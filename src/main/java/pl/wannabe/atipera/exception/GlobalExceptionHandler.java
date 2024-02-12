package pl.wannabe.atipera.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.fasterxml.jackson.core.JsonProcessingException;

import pl.wannabe.atipera.dto.ExceptionResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<ExceptionResponse> handleWebClientResponseException(WebClientResponseException ex) {
        ExceptionResponse exceptionResponse;
        if (ex.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            exceptionResponse = new ExceptionResponse(
                    ex.getStatusCode().value(),
                    "User not found");
        } else if (ex.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
            exceptionResponse = new ExceptionResponse(
                    ex.getStatusCode().value(),
                    "Unauthorized acces - check your token");
        } else {
            exceptionResponse = new ExceptionResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Internal server error - try again later");
        }
        return new ResponseEntity<>(exceptionResponse, HttpStatus.valueOf(exceptionResponse.status()));
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<ExceptionResponse> handleJsonProcessingException(JsonProcessingException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGeneralException(Exception ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected error occurred: " + ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
