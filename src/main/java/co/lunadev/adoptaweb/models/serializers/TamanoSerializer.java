package co.lunadev.adoptaweb.models.serializers;

import co.lunadev.adoptaweb.models.Animal;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class TamanoSerializer extends StdSerializer<Animal.Tamano> {


    public TamanoSerializer() {
        super(Animal.Tamano.class);
    }

    @Override
    public void serialize(Animal.Tamano value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(value.getNormalName());
    }
}
