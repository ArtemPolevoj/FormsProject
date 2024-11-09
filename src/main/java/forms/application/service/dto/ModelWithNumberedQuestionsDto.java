package forms.application.service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Дто для выдачи модели со списком пронумированных вопросов
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModelWithNumberedQuestionsDto {
    @NotNull
    private String model;

    @NotNull
    private List<QuestionNumberedByMachineType> questions;
}
