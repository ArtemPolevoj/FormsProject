package forms.application.service;

import forms.application.config.MinioConfig;
import forms.application.config.MinioProperties;
import forms.application.model.Report;
import forms.application.model.ReportEntity;
import forms.application.service.dto.AnswerDto;
import forms.application.service.dto.DefectDto;
import forms.application.service.dto.QuestionNumberedByMachineType;
import forms.application.service.dto.ReportDto;
import forms.application.util.QuestionStatus;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Transactional(propagation = Propagation.NEVER)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DisplayName("Сервис для рапорты с отчетами")
@Import(value = {
        ReportServiceImpl.class, OrganizationServiceImpl.class,
        MachineServiceImpl.class, TypeMachineServiceImpl.class, ImplementerServiceImpl.class, ImageServiceImpl.class,
        MinioConfig.class, MinioProperties.class, QuestionServiceImpl.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReportServiceImplTest {
    private static final String MACHINE_SERIAL_NUMBER = "2345";

    @Autowired
    ReportServiceImpl reportService;

    @Test
    @Order(4)
    void findAll() {
        List<ReportEntity> reports = reportService.findAll();

        assertThat(reports).isNotNull().isNotEmpty();
        assertThat(reports.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    @Order(5)
    void findAllByOrganizationId() {
        List<ReportEntity> reports = reportService.findAllByOrganizationId(1L);

        assertThat(reports).isNotNull().isNotEmpty();
        assertThat(reports.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    @Order(2)
    void findById() {
        ReportEntity report = reportService.findById(1L);

        assertThat(report).isNotNull();
        assertEquals(1L, report.getId());
    }

    @Test
    @Order(3)
    void findByIdMustThrows() {
        assertThrows(EntityNotFoundException.class, () -> reportService.findById(Long.MAX_VALUE));
    }

    @Test
    @Order(6)
    void findFirstByReportMachinerySerialNumberAndReportDate() {
        ReportEntity report = reportService
                .findFirstByReportMachinerySerialNumberAndReportDate(LocalDate.now(), MACHINE_SERIAL_NUMBER);

        assertThat(report).isNotNull();
        assertEquals(LocalDate.now(), report.getReport().getDate());
        assertEquals(MACHINE_SERIAL_NUMBER, report.getReport().getMachinery().getSerialNumber());
    }

    @Test
    @Order(7)
    void findByIdMustThrowsWhenWrongDate() {
        assertThrows(EntityNotFoundException.class, () ->
                reportService.findFirstByReportMachinerySerialNumberAndReportDate(
                        LocalDate.of(1969, 10, 29), MACHINE_SERIAL_NUMBER));
    }

    @Test
    @Order(8)
    void findByIdMustThrowsWhenWrongSerialNumber() {
        assertThrows(EntityNotFoundException.class, () ->
                reportService.findFirstByReportMachinerySerialNumberAndReportDate(
                        LocalDate.now(), "unexpected serial number"));
    }

    @Test
    @Order(1)
    void create() {
        ReportDto reportDto = getTestDto();

        ReportEntity actual = reportService.create(reportDto);
        Report report = actual.getReport();

        assertThat(actual.getId()).isNotNull().isGreaterThan(0L);
        assertEquals(reportDto.getImplementerId(), report.getImplementer().getId());
        assertEquals(reportDto.getMachinerySerialNumber(), report.getMachinery().getSerialNumber());
        assertEquals(reportDto.getOrganizationId(), report.getOrganization().getId());
        assertEquals(LocalDate.now(), report.getDate());
        assertNotNull(report.getPathToImage());
        assertEquals(1, report.getQuestions().size());

        QuestionNumberedByMachineType questionDto = reportDto.getQuestions().keySet().stream().findFirst().get();
        assertTrue(report.getQuestions().containsKey(questionDto));

        AnswerDto answerDto = reportDto.getQuestions().get(questionDto);
        assertEquals(answerDto.getStatus(), report.getQuestions().get(questionDto).getStatus());

        List<DefectDto> defectDtoList = answerDto.getDefects();
        assertThat(defectDtoList.size())
                .isEqualTo(2)
                .isEqualTo(report.getQuestions().get(questionDto).getDefects().size());
        assertNotNull(report.getQuestions().get(questionDto).getDefects().get(0).getPathToImage());
        assertNotNull(report.getQuestions().get(questionDto).getDefects().get(1).getPathToImage());
    }

    private byte[] getImage(String path) {
        try {
            File fi = new File(path);
            return Files.readAllBytes(fi.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ReportDto getTestDto() {
        ReportDto report = new ReportDto();
        report.setOrganizationId(1L);
        report.setImplementerId(1L);
        report.setMachinerySerialNumber(MACHINE_SERIAL_NUMBER);
        report.setImage(getImage("src/test/resources/images/рис. отчета.png"));

        Map<QuestionNumberedByMachineType, AnswerDto> questions = new HashMap<>();

        AnswerDto answerDto = new AnswerDto();
        answerDto.setStatus(QuestionStatus.ЕСТЬ_ЗАМЕЧАНИЯ);
        answerDto.setDefects(new ArrayList<>());

        for (int i = 1; i <= 2; i++) {
            DefectDto defectDto = new DefectDto();
            defectDto.setDescription("some description");
            defectDto.setEliminationWorks("some elimination workds");
            defectDto.setNecessarySpareParts("some necessary spare parts for " + i + " dto");
            defectDto.setImage(getImage("src/test/resources/images/деф." + i + ".png"));
            answerDto.getDefects().add(defectDto);
        }
        questions.put(new QuestionNumberedByMachineType(111, "quest111"), answerDto);
        report.setQuestions(questions);

        return report;
    }

}