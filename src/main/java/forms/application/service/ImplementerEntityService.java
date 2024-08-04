package forms.application.service;

import forms.application.model.ImplementerEntity;

import java.util.List;

public interface ImplementerEntityService {

    ImplementerEntity create(ImplementerEntity implementerEntity);

    List<ImplementerEntity> getAll();

    ImplementerEntity getById(Long id);

    ImplementerEntity update(ImplementerEntity implementerEntity);

    void delete(Long id);

}
