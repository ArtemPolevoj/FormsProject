package forms.application.service;

import forms.application.dao.MachineDao;
import forms.application.dao.MachineTypeDao;
import forms.application.model.ModelEntity;
import forms.application.model.QuestionEntity;
import forms.application.service.dto.ModelDto;
import forms.application.service.dto.ModelWithNumberedQuestionsDto;
import forms.application.service.dto.QuestionNumberedByMachineType;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {
    MachineDao machineDao;

    @Override
    public List<ModelEntity> findAll() {
        return typeDao.findAll();
    }

    @Override
    public ModelEntity findById(Long id) {
        return typeDao.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Machine type with id = %d not found".formatted(id)));
    }

    @Override
    public ModelEntity findByModel(String model) {
        return typeDao.findByModel(model)
                .orElseThrow(()->
                        new EntityNotFoundException("Machine type with model = %s not found".formatted(model)));
    }

    @Override
    @Transactional(readOnly = true)
    public ModelWithNumberedQuestionsDto findAllByModelWithNumberedQuestions(String model) {
        ModelEntity type = this.findByModel(model);

      //  List<QuestionEntity> questionsEntities = new ArrayList<>(type.getQuestions());
//        questionsEntities.sort(Comparator.comparingInt(QuestionEntity::getLevel));
//
//        List<QuestionNumberedByMachineType> numberedQuestions = new ArrayList<>(questionsEntities.size());
//        int questionIndex = 0;
//        for (int i = 0; i < questionsEntities.size(); i++) {
//            if (0 < i && !questionsEntities.get(i).getLevel().equals(questionsEntities.get(i-1).getLevel())) {
//                questionIndex = 0;
//            }
//            questionIndex++;
//            numberedQuestions.add(new QuestionNumberedByMachineType(questionsEntities.get(i), questionIndex));
//        }
//
//        return new ModelWithNumberedQuestionsDto(type.getModel(), numberedQuestions);
        return null;
    }

    @Override
    public void deleteById(Long id) {
        typeDao.deleteById(id);
    }

    @Override
    public ModelEntity create(ModelDto type) {
        Set<QuestionEntity> questions = questionService.findAllByIds(type.getQuestions());
        if (questions.isEmpty()) {
            throw new EntityNotFoundException("There is no questions with ids in %s".formatted(type.getQuestions()));
        }

//        ModelEntity entity = ModelEntity.builder()
//                .model(type.getModel())
//                .questions(questions).build();

 //       return typeDao.save(entity);
        return null;
    }

    @Override
    public ModelEntity update(ModelDto type) {
        Set<QuestionEntity> questions = questionService.findAllByIds(type.getQuestions());
        if (questions.isEmpty()) {
            throw new EntityNotFoundException("There is no questions with ids in %s".formatted(type.getQuestions()));
        }

        ModelEntity entity = this.findByModel(type.getModel());

        entity.setModel(type.getModel());
      //  entity.setQuestions(questions);

        return typeDao.save(entity);
    }
}
