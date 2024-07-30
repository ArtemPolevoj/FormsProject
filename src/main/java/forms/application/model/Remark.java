package forms.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Замечание
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Remark {
    private String text;

    //путь до картинки в minio или на диске
    private String pathToImage;

    private Set<String> necessarySpareParts;
}
