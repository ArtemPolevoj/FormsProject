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

    private byte[] remarkImage;

    private Set<String> necessarySpareParts;
}
