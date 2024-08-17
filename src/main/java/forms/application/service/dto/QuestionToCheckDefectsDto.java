package forms.application.service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionToCheckDefectsDto {
    @NotNull
    private Long machinerySerialNumber; //серийный номер машины - он же id

    @NotNull
    private Integer number;
}
