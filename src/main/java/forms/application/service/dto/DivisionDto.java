package forms.application.service.dto;

import forms.application.model.DivisionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Подразделение
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DivisionDto {
    private Long id;

    private String name;

    public DivisionDto(String name) {
        this.name = name;
    }

    public static DivisionEntity convert(DivisionDto division) {
        return new DivisionEntity(division.getId(), division.getName());
    }
}
