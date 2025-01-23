package co.lunadev.adoptaweb.utils;

import co.lunadev.adoptaweb.exceptions.UnauthorizedException;
import co.lunadev.adoptaweb.models.User;
import lombok.extern.java.Log;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.HttpClientErrorException;

@Log
public class UtilSecurity {


    private UtilSecurity() {
    }

    public static void checkEqual(Object origin, Object target){
        try {
            if(origin!=target){
                throw new UnauthorizedException("You are not allowed to access this object");
            }
        } catch (SecurityException e) {
            log.severe("fail to check equal security");
            throw new UnauthorizedException();
        }
    }
    public static User getUser(){
        try {
            SecurityContext context = SecurityContextHolder.getContext();
            return (User) context.getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }
}
