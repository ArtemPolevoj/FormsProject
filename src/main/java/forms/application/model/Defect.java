package forms.application.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Неисправность
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Defect {
    private String description;

    //путь до картинки в minio или на диске
    private String pathToImage;

    private String necessarySpareParts;

    //работы по устранению
    private String eliminationWorks;
}
