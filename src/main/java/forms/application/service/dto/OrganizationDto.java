package forms.application.service.dto;

import forms.application.model.OrganizationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDto {
    private String name;

    private String email;

    private String phone;

    public static OrganizationEntity convert(OrganizationDto dto) {
        return OrganizationEntity.builder()
                .name(dto.name)
                .phone(dto.phone)
                .email(dto.email)
                .build();
    }
}
