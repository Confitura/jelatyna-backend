package pl.confitura.jelatyna.infrastructure;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@Component
public class ResourceSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String resourcePath, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeObject("resources/" + resourcePath);

    }
}
