package forms.application.service;

import forms.application.model.ImplementerEntity;
import forms.application.service.dto.ImplementerDto;

import java.util.List;

public interface ImplementerService {
    List<ImplementerEntity> findAll();

    ImplementerEntity findById(Long id);

    void deleteById(Long id);

    ImplementerEntity create(ImplementerDto implementer);

    ImplementerEntity update(ImplementerDto implementer, Long id);
}
