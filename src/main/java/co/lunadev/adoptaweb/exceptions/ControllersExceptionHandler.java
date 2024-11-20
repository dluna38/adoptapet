package co.lunadev.adoptaweb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllersExceptionHandler {

    @ExceptionHandler(FieldRequiredException.class)
    public ResponseEntity<Object> fieldRequiredResponse(FieldRequiredException ex) {
        return ex.generateResponse();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> resourceNotFoundResponse(ResourceNotFoundException ex){
        return ex.generateResponse();
    }
    @ExceptionHandler(UnknownException.class)
    public ResponseEntity<Object> unknownExceptionResponse(UnknownException ex){
        return ex.generateResponse();
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> validationExceptionResponse(ValidationException ex){
        return ex.generateResponse();
    }
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> authenticationExceptionResponse(AuthenticationException ex){
        return new AppHttpException("No se pudo autenticar - revisar credenciales",HttpStatus.BAD_REQUEST).generateResponse();
    }
    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<Object> disableExceptionResponse(DisabledException ex){
        return new AppHttpException("Cuenta desactivada - contacte al administrador",HttpStatus.BAD_REQUEST).generateResponse();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNotValidExceptionResponse(MethodArgumentNotValidException ex){

        String errorsString = ex.getBindingResult().getAllErrors().stream()
                .map(error-> ((FieldError) error).getField() +": "+error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return new AppHttpException(errorsString,HttpStatus.BAD_REQUEST).generateResponse();
    }

   /* @ExceptionHandler(SqlEx.class)
    public ResponseEntity<Object> sqlExceptionResponse(PSQLException ex){
        return new DatabaseHandleExceptions(ex).generateResponse();
    }*/

}
