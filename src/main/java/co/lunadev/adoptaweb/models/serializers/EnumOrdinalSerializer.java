package co.lunadev.adoptaweb.models.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class EnumOrdinalSerializer extends JsonSerializer<Enum> {
    @Override
    public void serialize(Enum anEnum, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        gen.writeNumber(anEnum.ordinal());
    }
}
