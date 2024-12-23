package co.lunadev.adoptaweb.utils;

import org.springframework.data.domain.PageRequest;

import java.util.Map;

public class UtilPage {
    public static final int DEFAULT_SIZE_PAGE = 20;
    public static final int DEFAULT_MAX_SIZE_PAGE = 50;

    private UtilPage() {
    }
    public static PageRequest paramsToPageRequest(Map<String, String> params) {
        return paramsToPageRequest(params, DEFAULT_MAX_SIZE_PAGE);
    }

    public static PageRequest paramsToPageRequest(Map<String, String> params, int maxPageSize) {
        int pageNumber = parseOrDefault(params.get("page"), 0, 0) ;

        int pageSize = parseOrDefault(params.get("pSize"), DEFAULT_SIZE_PAGE, 1, maxPageSize);

        return PageRequest.of(pageNumber, pageSize);
    }

    private static int parseOrDefault(String value, int defaultValue, int minValue) {
        return parseOrDefault(value, defaultValue, minValue, Integer.MAX_VALUE);
    }

    private static int parseOrDefault(String value, int defaultValue, int minValue, int maxValue) {
        try {
            int parsedValue = Integer.parseInt(value);
            if (parsedValue >= minValue && parsedValue <= maxValue) {
                return parsedValue;
            }
        } catch (NumberFormatException ignored) {
            // Ignoramos excepciones de formato
        }
        return defaultValue;
    }

}
