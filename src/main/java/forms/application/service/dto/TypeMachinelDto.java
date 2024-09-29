package forms.application.service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TypeMachinelDto {
    @NotNull
    private String typeMachine;

    @NotNull
    private Set<Long> questions;
}