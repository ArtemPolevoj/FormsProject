package forms.application.model;

import forms.application.util.QuestionStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    @NotNull
    private QuestionStatus status;

    private List<Defect> defects;
}
