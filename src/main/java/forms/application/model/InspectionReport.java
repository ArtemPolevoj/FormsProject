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

    //путь до картинки в minio или на диске
    private String pathToImage;

    private Map<String, Remark> inspectionPoints;
}
