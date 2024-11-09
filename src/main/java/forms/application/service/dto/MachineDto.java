package forms.application.service.dto;

import forms.application.model.MachineEntity;
import forms.application.model.TypeMachineEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MachineDto {
    @NotNull
    private String serialNumber;

    @NotNull
    private String manufacturer;

    @NotNull
    private String model;

    @NotNull
    private String businessNumber;

    @NotNull
    private Integer operatingTime;

    @NotNull
    private OrganizationDto organization;

    @NotNull
    private DivisionDto division;

    public static MachineEntity convert(MachineDto dto, TypeMachineEntity type) {
        return MachineEntity.builder()
                .serialNumber(dto.serialNumber)
                .operatingTime(dto.operatingTime)
                .manufacturer(dto.manufacturer)
                .typeMachine(type)
                .businessNumber(dto.businessNumber)
                .organization(OrganizationDto.convert(dto.getOrganization()))
                .division(DivisionDto.convert(dto.getDivision()))
                .build();
    }
}
