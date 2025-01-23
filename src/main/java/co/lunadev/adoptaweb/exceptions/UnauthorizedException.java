package co.lunadev.adoptaweb.exceptions;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends AppHttpException {

    public UnauthorizedException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
    public UnauthorizedException() {
        super("UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
    }
}
