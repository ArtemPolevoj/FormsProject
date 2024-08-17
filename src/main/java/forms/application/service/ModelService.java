package forms.application.service;

import forms.application.model.ModelEntity;
import forms.application.service.dto.ModelDto;
import forms.application.service.dto.ModelWithNumberedQuestionsDto;

import java.util.List;

public interface ModelService {
    List<ModelEntity> findAll();

    ModelEntity findById(Long id);

    ModelEntity findByModel(String model);

    ModelWithNumberedQuestionsDto findAllByModelWithNumberedQuestions(String model);

    void deleteById(Long id);

    ModelEntity create(ModelDto type);

    ModelEntity update(ModelDto type);
}
