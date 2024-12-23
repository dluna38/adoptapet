package co.lunadev.adoptaweb.models.serializers;

import co.lunadev.adoptaweb.models.EnumBase;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class EnumBaseSerializer extends JsonSerializer<EnumBase> {
    @Override
    public void serialize(EnumBase value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(value.normalName());
    }
}
