package forms.application.service;

import forms.application.model.QuestionEntity;
import forms.application.service.dto.QuestionDto;

import java.util.List;

public interface QuestionService {
    List<QuestionEntity> findAll();

    List<QuestionDto> findAllByMachineSerialNumber(String serialNumber);

    QuestionEntity findByNumber(Integer id);

    void deleteById(Integer id);

    QuestionEntity create(QuestionDto question, String machineSerialNumber);

    QuestionEntity update(QuestionDto question);
}
