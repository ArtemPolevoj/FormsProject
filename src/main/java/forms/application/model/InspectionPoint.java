package forms.application.model;

import forms.application.util.InspectionPointStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InspectionPoint {
    private int number;

    private InspectionPointStatus status;

    private String title;
}
