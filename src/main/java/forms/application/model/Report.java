package forms.application.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import forms.application.service.dto.QuestionNumberedByMachineType;
import forms.application.util.QuestionDtoKeyDeserializer;
import forms.application.util.QuestionDtoKeySerializer;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Report {
    @NotNull
    private OrganizationEntity organization;

    @NotNull
    private MachineEntity machinery;

    @NotNull
    private ImplementerEntity implementer;

    //путь до картинки в minio или на диске
    private String pathToImage;

    //https://www.baeldung.com/jackson-map - сериализация/десериализация мап
    @JsonDeserialize(keyUsing = QuestionDtoKeyDeserializer.class)
    @JsonSerialize(keyUsing = QuestionDtoKeySerializer.class)
    @JsonProperty("questions")
    private Map<QuestionNumberedByMachineType, Answer> questions;

    @NotNull
    private LocalDate date;
}
