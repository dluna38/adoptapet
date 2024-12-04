package co.lunadev.adoptaweb.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class ValidationException extends AppHttpException{
    private static final String GENERAL_MESSAGE = "Error en la validación de datos";
    public ValidationException(String campo, String error) {
        super(GENERAL_MESSAGE, HttpStatus.BAD_REQUEST);
        addToExtraContent(Map.of("campo",campo,"error",error));
    }

    public ValidationException(String error) {
        super(GENERAL_MESSAGE, HttpStatus.BAD_REQUEST);
        addToExtraContent(Map.of("Error",error));
    }
    public ValidationException() {
        super(GENERAL_MESSAGE, HttpStatus.BAD_REQUEST);
    }
}
