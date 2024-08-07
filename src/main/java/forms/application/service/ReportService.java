package forms.application.service;

import forms.application.model.ReportEntity;
import forms.application.service.dto.ReportDto;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReportService {
    List<ReportEntity> findAll();

    List<ReportEntity> findAllByOrganizationId(Long organizationId);

    ReportEntity findFirstByReportMachinerySerialNumberAndReportDate(LocalDate date, String serialNumber);

    ReportEntity findById(Long id);

    void deleteById(Long id);

    ReportEntity create(ReportDto report);
}
