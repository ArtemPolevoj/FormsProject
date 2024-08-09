package forms.application.service;

import forms.application.model.DivisionEntity;

import java.util.List;
import java.util.Optional;

public interface DivisionEntityService {
    DivisionEntity create(DivisionEntity divisionEntity);

    List<DivisionEntity> getAll();

    DivisionEntity getById(Long id);

    DivisionEntity update(DivisionEntity divisionEntity);

    void delete(Long id);
}
