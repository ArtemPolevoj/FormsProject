package forms.application.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import forms.application.service.dto.QuestionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Туториал по кастомным сериализаторам
 *
 * https://www.baeldung.com/jackson-custom-serialization
 */
@Slf4j
public class QuestionDtoKeySerializer extends JsonSerializer<QuestionDto> {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void serialize(QuestionDto value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        log.info("начало работы сериализатора");
        StringWriter writer = new StringWriter();
        mapper.writeValue(writer, value);
        gen.writeFieldName(writer.toString());
        log.info("конец работы сериализатора: " + writer.toString());
    }
}
