package forms.application.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

/**
 * Неисправность
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Defect {
    @NotNull
    private String description;

    //путь до картинки в minio или на диске
    private byte [] image;

    @NotNull
    private String necessarySpareParts;

    //работы по устранению
    @NotNull
    private String eliminationWorks;
}
