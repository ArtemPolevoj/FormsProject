package forms.application.service;

import forms.application.dao.QuestionDao;
import forms.application.model.QuestionEntity;
import forms.application.service.dto.QuestionDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao questionDao;

    @Override
    public List<QuestionEntity> findAll() {
        return questionDao.findAll();
    }

    @Override
    public QuestionEntity findById(@NotNull Long id) {
        return questionDao.findById(id)
                .orElseThrow(()
                        -> new EntityNotFoundException("Question with id = " + id + " not found"));
    }

    @Override
    public void deleteById(@NotNull Long id) {
        questionDao.deleteById(id);
    }

    @Override
    public QuestionEntity create(QuestionDto question) {
        return questionDao.save(QuestionDto.convert(question));
    }

    @Override
    public QuestionEntity update(QuestionDto question, Long id) {
        QuestionEntity byId = this.findById(id);

        byId.setDescription(question.getDescription());

        return questionDao.save(byId);
    }

    @Override
    public Set<QuestionEntity> findAllByIds(Set<Long> ids) {
        return questionDao.findAllByIds(ids);
    }
}
