package forms.application.service.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import forms.application.model.QuestionEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto пронумированного вопроса, относительно конкретной модели
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionNumberedByMachineType {
    @NotNull
    private Integer number;

    @NotNull
    private String description;

    @Override
    @JsonValue
    public String toString() {
        return "question {" +
                "number=" + number +
                ", description ='" + description + '\'' +
                '}';
    }

    public QuestionNumberedByMachineType(QuestionEntity question, int questionIndexByLevel) {
        this.number = question.getLevel() + questionIndexByLevel;
        this.description = question.getDescription();
    }
}
