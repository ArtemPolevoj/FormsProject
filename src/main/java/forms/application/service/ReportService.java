package forms.application.service;

import forms.application.model.ReportEntity;
import forms.application.service.dto.ReportDto;

import java.util.List;
import java.util.Optional;

public interface ReportService {
    List<ReportEntity> findAll();

    List<ReportEntity> findAllByOrganizationId(Long organizationId);

    ReportEntity findById(Long id);

    void deleteById(Long id);

    ReportEntity create(ReportDto report);
}
