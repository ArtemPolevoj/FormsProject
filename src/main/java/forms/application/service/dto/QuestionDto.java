package forms.application.service.dto;

import forms.application.model.QuestionEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {
    @NotNull
    private Integer level;

    @NotNull
    private String description;

    public static QuestionEntity convert(QuestionDto question) {
        return QuestionEntity.builder()
                .level(question.level)
                .description(question.getDescription())
                .build();
    }
}
