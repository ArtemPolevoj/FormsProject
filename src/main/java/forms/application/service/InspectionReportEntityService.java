package forms.application.service;

import forms.application.model.InspectionReportEntity;

import java.util.List;

public interface InspectionReportEntityService {

    InspectionReportEntity create(InspectionReportEntity inspectionReportEntity);

    List<InspectionReportEntity> getAll();

    InspectionReportEntity getById(Long id);

    InspectionReportEntity update(InspectionReportEntity inspectionReportEntity);

    void delete(Long id);

}
