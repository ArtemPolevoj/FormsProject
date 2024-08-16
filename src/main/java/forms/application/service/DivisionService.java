package forms.application.service;

import forms.application.model.DivisionEntity;
import forms.application.service.dto.DivisionDto;

import java.util.List;

public interface DivisionService {
    List<DivisionEntity> findAll();

    DivisionEntity findById(Long id);

    void deleteById(Long id);

    DivisionEntity create(DivisionDto division);

    DivisionEntity update(DivisionDto division, Long id);
}
