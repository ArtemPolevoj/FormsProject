package forms.application.service.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {
    @NotNull
    private Integer number;

    @NotNull
    private String title;

    @Override
    @JsonValue
    public String toString() {
        return "question {" +
                "number=" + number +
                ", title='" + title + '\'' +
                '}';
    }
}
