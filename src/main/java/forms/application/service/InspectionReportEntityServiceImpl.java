package forms.application.service;


import forms.application.model.InspectionReportEntity;
import forms.application.repository.InspectionReportEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InspectionReportEntityServiceImpl implements InspectionReportEntityService {
    private final InspectionReportEntityRepository inspectionReportEntityRepository;

    @Override
    public InspectionReportEntity create(InspectionReportEntity inspectionReportEntity) {
        return inspectionReportEntityRepository.save(inspectionReportEntity);
    }

    @Override
    public List<InspectionReportEntity> getAll() {
        return inspectionReportEntityRepository.findAll();
    }

    @Override
    public InspectionReportEntity getById(Long id) {
        return inspectionReportEntityRepository.findById(id).orElse(null);
    }

    @Override
    public InspectionReportEntity update(InspectionReportEntity inspectionReportEntity) {
        InspectionReportEntity inspectionReportEntityUpdated = inspectionReportEntityRepository
                .findById(inspectionReportEntity.getId()).orElse(null);

        inspectionReportEntityUpdated.setInspectionReportJSON(inspectionReportEntity
                .getInspectionReportJSON());
        return inspectionReportEntityRepository.save(inspectionReportEntityUpdated);
    }

    @Override
    public void delete(Long id) {

        InspectionReportEntity inspectionReportEntityId = getById(id);
        inspectionReportEntityRepository.delete(inspectionReportEntityId);

    }
}

