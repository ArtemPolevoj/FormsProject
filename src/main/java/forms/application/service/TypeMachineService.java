package forms.application.service;

import forms.application.model.TypeMachineEntity;
import forms.application.service.dto.TypeMachinelDto;
import forms.application.service.dto.ModelWithNumberedQuestionsDto;

import java.util.List;

public interface TypeMachineService {
    List<TypeMachineEntity> findAll();

    TypeMachineEntity findById(Long id);

    TypeMachineEntity findByTypeMachine(String type);

    ModelWithNumberedQuestionsDto findAllByModelWithNumberedQuestions(String model);

    void deleteById(Long id);

    TypeMachineEntity create(TypeMachinelDto type);

    TypeMachineEntity update(TypeMachinelDto type);
}
