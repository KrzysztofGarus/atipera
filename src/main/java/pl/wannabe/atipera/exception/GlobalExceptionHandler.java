package pl.wannabe.atipera.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.fasterxml.jackson.core.JsonProcessingException;

import pl.wannabe.atipera.dto.ExceptionResponse;

/**
 * Handles exceptions thrown by the application.
 * It provides methods to handle specific types of exceptions and return appropriate responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles WebClientRequestException and returns a ResponseEntity with an ExceptionResponse.
     * This method is responsible for handling exceptions that occur when making requests to the GitHub API.
     *
     * @param ex The WebClientRequestException that occurred.
     * @return A ResponseEntity containing an ExceptionResponse with the appropriate status code and error message.
     */
    @ExceptionHandler(WebClientRequestException.class)
    public ResponseEntity<ExceptionResponse> handleWebClientRequestException(WebClientRequestException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Failed to connect to GitHub API");
        return new ResponseEntity<>(exceptionResponse, HttpStatus.valueOf(exceptionResponse.status()));
    }

    /**
     * Handles WebClientResponseException and returns an appropriate ResponseEntity with an ExceptionResponse.
     *
     * @param ex The WebClientResponseException to handle.
     * @return A ResponseEntity containing the ExceptionResponse and the corresponding HttpStatus.
     */
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

    /**
     * Handles the exception thrown when there is a failure in processing JSON data.
     * 
     * @param ex The JsonProcessingException that was thrown
     * @return A ResponseEntity containing an ExceptionResponse object and the HTTP status code
     */
    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<ExceptionResponse> handleJsonProcessingException(JsonProcessingException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Failed to process data");
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles general exceptions and returns an appropriate response.
     * 
     * @param ex The exception that occurred.
     * @return A ResponseEntity containing the exception response and HTTP status code.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGeneralException(Exception ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected error occurred: " + ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
