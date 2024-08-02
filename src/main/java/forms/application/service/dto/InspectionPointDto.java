package forms.application.service.dto;

import forms.application.util.InspectionPointStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InspectionPointDto {
    @NotNull
    private Long machinerySerialNumber; //серийный номер машины - он же id

    @NotNull
    private Integer number;

    @NotNull
    private InspectionPointStatus status;
}
