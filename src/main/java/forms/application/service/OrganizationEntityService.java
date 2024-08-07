package forms.application.service;


import forms.application.model.OrganizationEntity;

import java.util.List;

public interface OrganizationEntityService {
    OrganizationEntity create(OrganizationEntity organizationEntity);

    List<OrganizationEntity> getAll();

    OrganizationEntity getById(Long id);

    OrganizationEntity update(OrganizationEntity organizationEntity);

    void delete(Long id);

}
