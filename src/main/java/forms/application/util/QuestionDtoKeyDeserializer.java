package forms.application.util;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.TextNode;
import forms.application.service.dto.QuestionDto;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Туториал по кастомным десериализаторам
 * <p>
 * https://www.baeldung.com/jackson-deserialization
 */
@Slf4j
public class QuestionDtoKeyDeserializer extends KeyDeserializer {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public QuestionDto deserializeKey(String key, DeserializationContext ctxt) throws IOException {
        // Разбираем JSON-строку с помощью Jackson
        JsonNode node = mapper.readTree(key);

        // Извлекаем значения полей number и title
        int number = (node.path("question.number")).asInt();
        String title = (node.path("question.title")).asText();

        // Вызываем конструктор QuestionDto с полученными значениями
        return new QuestionDto(number, title);
    }
}
