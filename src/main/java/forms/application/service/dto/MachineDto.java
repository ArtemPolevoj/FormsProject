package forms.application.service.dto;

import forms.application.model.MachineryEntity;
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
    private Integer businessNumber;

    @NotNull
    private Integer operatingTime;

    public static MachineryEntity convert(MachineDto dto) {
        return MachineryEntity.builder()
                .serialNumber(dto.serialNumber)
                .operatingTime(dto.operatingTime)
                .manufacturer(dto.manufacturer)
                .model(dto.model)
                .businessNumber(dto.businessNumber)
                .build();
    }
}
