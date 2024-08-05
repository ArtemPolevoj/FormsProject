package forms.application.service.dto;

import forms.application.model.ImplementerEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ImplementerDto {
    @NotNull
    private String fullName;

    private String email;

    private String phone;

    public static ImplementerEntity convert(ImplementerDto dto) {
        return ImplementerEntity.builder()
                .fullName(dto.getFullName())
                .email(dto.email)
                .phone(dto.phone)
                .build();
    }
}
