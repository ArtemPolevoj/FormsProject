package forms.application.service.dto;

import forms.application.model.Defect;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Неисправность
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DefectDto {
    @NotNull
    private String description;

    //путь до картинки в minio или на диске
    private byte [] image;

    @NotNull
    private String necessarySpareParts;

    //работы по устранению
    @NotNull
    private String eliminationWorks;

    public static Defect convert(DefectDto dto, String pathToImage) {
        return Defect.builder()
                .description(dto.getDescription())
                .eliminationWorks(dto.getEliminationWorks())
                .necessarySpareParts(dto.getNecessarySpareParts())
                .pathToImage(pathToImage)
                .build();
    }
}
