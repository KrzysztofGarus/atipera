package pl.wannabe.atipera.dto;

/**
 * Represents an exception response.
 *
 * @param status  the status code of the exception
 * @param message the error message of the exception
 */
public record ExceptionResponse(

    int status,
    String message
){
    
}
