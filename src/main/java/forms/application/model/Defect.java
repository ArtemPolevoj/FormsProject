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

    @NotNull
    private LocalDate discoveryDate;

    private LocalDate eliminationDate;

    private boolean isEliminated;

    //путь до картинки в minio или на диске
    private String pathToImage;

    @NotNull
    private Set<String> necessarySpareParts;
}
