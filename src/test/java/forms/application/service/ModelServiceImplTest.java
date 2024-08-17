package forms.application.service;

import forms.application.service.dto.ModelWithNumberedQuestionsDto;
import forms.application.util.InitMachineQuestions;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@Import(value = {ModelServiceImpl.class, QuestionServiceImpl.class, InitMachineQuestions.class})
@Transactional(propagation = Propagation.NEVER)
@DisplayName(value = "Сервис для работы с моделями машин")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
class ModelServiceImplTest {
    @Autowired
    ModelServiceImpl modelService;

    @Test
    void findAllByMachineWithNumberedQuestions() {
        String model = "Бульдозер гусеничный";

        ModelWithNumberedQuestionsDto modelWithQuestions = modelService.findAllByModelWithNumberedQuestions(model);

        //todo remove this after success test
        log.info("numbered questions: {}", modelWithQuestions.getQuestions());

        assertNotNull(modelWithQuestions);
        assertFalse(modelWithQuestions.getQuestions().isEmpty());
        assertThat(modelWithQuestions.getQuestions()).allMatch(q-> 100 < q.getNumber());
    }
}