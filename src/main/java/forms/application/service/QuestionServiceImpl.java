package forms.application.service;

import forms.application.dao.QuestionDao;
import forms.application.model.MachineryEntity;
import forms.application.model.QuestionEntity;
import forms.application.service.dto.QuestionDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao questionDao;

    private final MachineService machineService;

    @Override
    public List<QuestionEntity> findAll() {
        return questionDao.findAll();
    }

    @Override
    public List<QuestionDto> findAllByMachineSerialNumber(String serialNumber) {
        return questionDao.findAllByMachinerySerialNumber(serialNumber)
                .stream()
                .map(q-> new QuestionDto(q.getNumber(), q.getTitle()))
                .toList();
    }

    @Override
    public QuestionEntity findByNumber(@NotNull Integer id) {
        return questionDao.findById(id)
                .orElseThrow(()
                        -> new EntityNotFoundException("Question with id = " + id + " not found"));

    }

    @Override
    public void deleteById(@NotNull Integer id) {
        questionDao.deleteById(id);
    }

    @Override
    public QuestionEntity create(QuestionDto question, String machineSerialNumber) {
        MachineryEntity machine = machineService.findBySerialNumber(machineSerialNumber);

        return questionDao.save(new QuestionEntity(question.getNumber(), question.getTitle(), machine));
    }

    @Override
    public QuestionEntity update(QuestionDto question) {
        QuestionEntity byNumber = this.findByNumber(question.getNumber());

        byNumber.setTitle(question.getTitle());

        return questionDao.save(byNumber);
    }
}
