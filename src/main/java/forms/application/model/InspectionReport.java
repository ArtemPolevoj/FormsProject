package forms.application.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InspectionReport {
    @NotNull
    private OrganizationEntity organization;

    @NotNull
    private MachineryEntity machinery;

    @NotNull
    private ImplementerEntity implementer;

    //путь до картинки в minio или на диске
    private String pathToImage;

    private Map<InspectionPoint, List<Defect>> inspectionPoints;

    @NotNull
    private LocalDateTime time;
}
