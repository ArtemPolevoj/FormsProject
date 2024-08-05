package forms.application.service;

import forms.application.dao.ReportDao;
import forms.application.model.Answer;
import forms.application.model.Defect;
import forms.application.model.ImplementerEntity;
import forms.application.model.MachineryEntity;
import forms.application.model.OrganizationEntity;
import forms.application.model.Report;
import forms.application.model.ReportEntity;
import forms.application.service.dto.AnswerDto;
import forms.application.service.dto.DefectDto;
import forms.application.service.dto.QuestionDto;
import forms.application.service.dto.ReportDto;
import forms.application.util.QuestionStatus;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportServiceImpl implements ReportService {
    private final ReportDao reportDao;

    private final ImplementerService implementerService;

    private final MachineService machineService;

    private final OrganizationService organizationService;

    private final ImageService imageService;

    @Override
    public List<ReportEntity> findAll() {
        return reportDao.findAll();
    }

    @Override
    public List<ReportEntity> findAllByOrganizationId(Long organizationId) {
//todo       return reportDao.findAllByReportOrganizationId(organizationId);
        return null;
    }

    @Override
    public ReportEntity findById(Long id) {
        return reportDao.findById(id)
                .orElseThrow(()
                        -> new EntityNotFoundException("Report with id = " + id + " not found"));
    }

    @Override
    public void deleteById(Long id) {
        reportDao.deleteById(id);
    }

    @Override
    @Transactional
    public ReportEntity create(ReportDto dto) {
        Report report = getReportWithoutQuestions(dto);

        //в итоговый отчет
        Map<QuestionDto, Answer> resultQuestions = new HashMap<>(dto.getQuestions().size());
        for (QuestionDto question : dto.getQuestions().keySet()) {
            AnswerDto answer = getAnswerDto(dto, question);

            //в итоговый отчет
            Answer resultAnswer = new Answer();
            resultAnswer.setStatus(answer.getStatus());
            resultAnswer.setDefects(new ArrayList<>());

            for (int i = 0; i < answer.getDefects().size(); i++) {
                DefectDto defect = answer.getDefects().get(i);

                if (defect.getImage().length != 0) {
                    String pathToImage = getDefectImageName(dto.getMachinerySerialNumber(), question.getNumber(), i);
                    imageService.uploadImage(pathToImage, defect.getImage());

                    //в итоговый отчет
                    resultAnswer.getDefects().add(DefectDto.convert(defect, pathToImage));
                }
            }
            resultQuestions.put(question, resultAnswer);
        }
        report.setQuestions(resultQuestions);
        ReportEntity saved = reportDao.save(new ReportEntity(report));
        log.info("сохраненный отчет: " + saved);

        return saved;
    }

    /**
     * Метод получения ответа с его валидацией
     */
    private static AnswerDto getAnswerDto(ReportDto dto, QuestionDto question) {
        AnswerDto answer = dto.getQuestions().get(question);
        if (answer.getStatus().equals(QuestionStatus.НЕТ_ЗАМЕЧАНИЙ) && !answer.getDefects().isEmpty()) {
            throw new RuntimeException("Answer status can't be 'НЕТ_ЗАМЕЧАНИЙ' if there are defects");
        }
        return answer;
    }

    /**
     * Метод для конвертации в отчет для хранения в БД без вопросов
     *
     * @param dto - dto от фронта
     * @return отчет без вопросов
     */
    private Report getReportWithoutQuestions(ReportDto dto) {
        ImplementerEntity implementer = implementerService.findById(dto.getImplementerId());
        OrganizationEntity organization = organizationService.findById(dto.getOrganizationId());
        MachineryEntity machine = machineService.findBySerialNumber(dto.getMachinerySerialNumber());

        Report report = new Report();
        report.setDate(LocalDate.now());
        report.setMachinery(machine);
        report.setImplementer(implementer);
        report.setOrganization(organization);

        if (dto.getImage().length != 0) {
            String pathToImage = getReportImageName(dto.getMachinerySerialNumber());
            imageService.uploadImage(pathToImage, dto.getImage());
            report.setPathToImage(pathToImage);
        }
        return report;
    }

    private String getReportImageName(String serialNumber) {
        return LocalDate.now() + "/" + serialNumber;
    }

    private String getDefectImageName(String serialNumber, int questionNumb, int defectNumb) {
        return LocalDate.now() + "/" + serialNumber + "/question/" + questionNumb + "/defect/" + defectNumb;
    }
}
