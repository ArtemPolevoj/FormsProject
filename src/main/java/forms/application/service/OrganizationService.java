package forms.application.service;

import forms.application.model.OrganizationEntity;
import forms.application.service.dto.OrganizationDto;

import java.util.List;

public interface OrganizationService {
    List<OrganizationEntity> findAll();

    OrganizationEntity findById(Long id);

    void deleteById(Long id);

    OrganizationEntity create(OrganizationDto org);

    OrganizationEntity update(OrganizationDto org, Long id);
}
