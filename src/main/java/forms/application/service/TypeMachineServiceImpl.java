package forms.application.service;


import forms.application.dao.TypeMachineDao;
import forms.application.model.TypeMachineEntity;
import forms.application.model.QuestionEntity;
import forms.application.service.dto.QuestionNumberedByMachineType;
import forms.application.service.dto.TypeMachinelDto;
import forms.application.service.dto.ModelWithNumberedQuestionsDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TypeMachineServiceImpl implements TypeMachineService {
    private final TypeMachineDao typeDao;

    private final QuestionService questionService;

    @Override
    public List<TypeMachineEntity> findAll() {
        return typeDao.findAll();
    }

    @Override
    public TypeMachineEntity findById(Long id) {
        return typeDao.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Machine type with id = %d not found".formatted(id)));
    }

    @Override
    public TypeMachineEntity findByTypeMachine(String typeMachine) {
        return typeDao.findByTypeMachine(typeMachine)
                .orElseThrow(()->
                        new EntityNotFoundException("Machine type with model = %s not found".formatted(typeMachine)));
    }

    @Override
    @Transactional(readOnly = true)
    public ModelWithNumberedQuestionsDto findAllByModelWithNumberedQuestions(String model) {
        TypeMachineEntity type = this.findByTypeMachine(model);

        List<QuestionEntity> questionsEntities = new ArrayList<>(type.getQuestions());
//        questionsEntities.sort(Comporator.comparingInt(QuestionEntity::getLevel));

        List<QuestionNumberedByMachineType> numberedQuestions = new ArrayList<>(questionsEntities.size());
        int questionIndex = 0;
        for (int i = 0; i < questionsEntities.size(); i++) {
            if (0 < i && !questionsEntities.get(i).getLevel().equals(questionsEntities.get(i-1).getLevel())) {
                questionIndex = 0;
            }
            questionIndex++;
            numberedQuestions.add(new QuestionNumberedByMachineType(questionsEntities.get(i), questionIndex));
        }

        return new ModelWithNumberedQuestionsDto(type.getTypeMachine(), numberedQuestions);

    }

    @Override
    public void deleteById(Long id) {
        typeDao.deleteById(id);
    }

    @Override
    public TypeMachineEntity create(TypeMachinelDto type) {
        Set<QuestionEntity> questions = questionService.findAllByIds(type.getQuestions());
        if (questions.isEmpty()) {
            throw new EntityNotFoundException("There is no questions with ids in %s".formatted(type.getQuestions()));
        }

        TypeMachineEntity entity = TypeMachineEntity.builder()
                .typeMachine(type.getTypeMachine())
                .questions(questions).build();

        return typeDao.save(entity);

    }

    @Override
    public TypeMachineEntity update(TypeMachinelDto type) {
        Set<QuestionEntity> questions = questionService.findAllByIds(type.getQuestions());
        if (questions.isEmpty()) {
            throw new EntityNotFoundException("There is no questions with ids in %s".formatted(type.getQuestions()));
        }

        TypeMachineEntity entity = this.findByTypeMachine(type.getTypeMachine());

        entity.setTypeMachine(type.getTypeMachine());
        entity.setQuestions(questions);

        return typeDao.save(entity);
    }
    public List<String> getAllTypeMachines() {
        List<TypeMachineEntity> typeMachines = findAll();
        List<String> result = new ArrayList<>();
        for (TypeMachineEntity typeMachine : typeMachines) {
            result.add(typeMachine.getTypeMachine());
        }
        return result;
    }

}
