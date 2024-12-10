package co.lunadev.adoptaweb.utils;

import java.text.Normalizer;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class UtilString {
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    private UtilString() {
    }

    public static boolean isEmailValid(String email) {
        if (stringIsEmptyOrNull(email)) return false;
        email = email.trim();
        return email.matches(EMAIL_REGEX);
    }

    public static boolean isStringEmpty(String cadena) {
        return cadena.trim().isBlank();
    }

    public static boolean stringIsEmptyOrNull(String cadena) {
        return cadena == null || isStringEmpty(cadena);
    }

    public static String rodearDePorcentaje(String cadena) {
        return "%" + cadena + "%";
    }

    public static String formatDate(LocalDateTime date){
        try {
            return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a"));
        } catch (Exception e) {
            return "";
        }
    }
    public static String formatCurrencyNumberToCO(Number numero){
        try {
            return NumberFormat.getCurrencyInstance(new Locale("es", "CO")).format(numero);
        } catch (Exception e) {
            return "";
        }
    }

    public static String makeSlug(String cadena,int maxLength){
        // Normalize the string to decompose accented characters
        String normalized = Normalizer.normalize(cadena.trim().toLowerCase(), Normalizer.Form.NFD);

        // Remove accents and special characters
        String stripped = normalized.replaceAll("[^\\p{L}\\p{N}\\s]+", "");
        stripped = stripped.replaceAll("\\s+", "-");

        if(stripped.length() > maxLength){
            stripped = stripped.substring(0, maxLength);
        }
        // Replace spaces and multiple hyphens with single hyphens
        return stripped;

    }

}
