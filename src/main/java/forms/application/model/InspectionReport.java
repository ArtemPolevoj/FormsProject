package forms.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InspectionReport {
    private Machinery machinery;

    private Implementer implementer;

    private byte[] image;

    private Map<String, Remark> inspectionPoints;
}
