package forms.application.service;

import forms.application.model.QuestionEntity;
import forms.application.service.dto.QuestionDto;

import java.util.List;
import java.util.Set;

public interface QuestionService {
    List<QuestionEntity> findAll();

    QuestionEntity findById(Long id);

    void deleteById(Long id);

    QuestionEntity create(QuestionDto question);

    QuestionEntity update(QuestionDto question, Long id);

    Set<QuestionEntity> findAllByIds(Set<Long> ids);
}
