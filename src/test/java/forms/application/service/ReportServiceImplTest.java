package forms.application.service;

import forms.application.config.MinioConfig;
import forms.application.config.MinioProperties;
import forms.application.model.Report;
import forms.application.model.ReportEntity;
import forms.application.service.dto.AnswerDto;
import forms.application.service.dto.DefectDto;
import forms.application.service.dto.QuestionDto;
import forms.application.service.dto.ReportDto;
import forms.application.util.QuestionStatus;
import io.minio.MinioClient;
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
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Transactional(propagation = Propagation.NEVER)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DisplayName("Сервис для рапорты с отчетами")
@Import(value = {
        ReportServiceImpl.class, OrganizationServiceImpl.class,
        MachineServiceImpl.class, ImplementerServiceImpl.class, ImageServiceImpl.class, MinioConfig.class, MinioProperties.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReportServiceImplTest {
    @Autowired
    ReportServiceImpl reportService;

    @Test
    @Order(3)
    void findAll() {
        List<ReportEntity> reports = reportService.findAll();

        assertThat(reports).isNotNull().isNotEmpty();
        assertThat(reports.size()).isEqualTo(1);
    }

    @Test
    void findAllByOrganizationId() {
    }

    @Test
    @Order(2)
    void findById() {
    }

    @Test
    void deleteById() {
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

        QuestionDto questionDto = reportDto.getQuestions().keySet().stream().findFirst().get();
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
        report.setMachinerySerialNumber("2345");
        report.setImage(getImage("src/test/resources/images/рис. отчета.png"));

        Map<QuestionDto, AnswerDto> questions = new HashMap<>();

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
        questions.put(new QuestionDto(111, "quest111"), answerDto);
        report.setQuestions(questions);

        return report;
    }
}