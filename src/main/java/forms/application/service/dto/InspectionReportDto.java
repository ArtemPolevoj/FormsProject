package forms.application.service.dto;

import forms.application.model.Defect;
import forms.application.model.InspectionPoint;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InspectionReportDto {
    @NotNull
    private Long organizationId;

    @NotNull
    private Long machinerySerialNumber;

    @NotNull
    private Long implementerId;

    //путь до картинки в minio или на диске
    private byte[] image;

    private Map<InspectionPoint, List<Defect>> inspectionPoints;
}
