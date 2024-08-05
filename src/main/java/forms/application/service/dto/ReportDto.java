package forms.application.service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto {
    @NotNull
    private Long organizationId;

    @NotNull
    private String machinerySerialNumber;

    @NotNull
    private Long implementerId;

    //путь до картинки в minio или на диске
    private byte[] image;

    private Map<QuestionDto, AnswerDto> questions;
}
