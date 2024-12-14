package co.lunadev.adoptaweb.models.serializers;

import co.lunadev.adoptaweb.models.HistoriaClinica;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class EstadoGeneralAnimalSerializer extends JsonSerializer<HistoriaClinica.EstadoGeneralAnimal> {
    @Override
    public void serialize(HistoriaClinica.EstadoGeneralAnimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("nombre", value.name().toLowerCase());
        gen.writeStringField("descripcion", value.getDescripcion());
        gen.writeEndObject();
    }
}
