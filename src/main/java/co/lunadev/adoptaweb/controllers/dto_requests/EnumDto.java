package co.lunadev.adoptaweb.controllers.dto_requests;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnumDto {

    private int code;
    private String name;
    private String description;

    public EnumDto(int code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }


}
