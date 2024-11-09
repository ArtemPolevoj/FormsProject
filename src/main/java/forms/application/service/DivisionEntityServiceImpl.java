package forms.application.service;

import forms.application.model.DivisionEntity;
import forms.application.repository.DivisionEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DivisionEntityServiceImpl implements ServiceEntity<DivisionEntity> {
    private final DivisionEntityRepository divisionEntityRepository;

    @Override
    public DivisionEntity create(DivisionEntity divisionEntity) {
        return divisionEntityRepository.save(divisionEntity);
    }

    @Override
    public List<DivisionEntity> getAll() {
        return divisionEntityRepository.findAll();
    }


    @Override
    public DivisionEntity getById(Long id) {
        return divisionEntityRepository.findById(id).orElse(null);

    }

    @Override
    public DivisionEntity update(DivisionEntity divisionEntity) {
        DivisionEntity updateDivision = divisionEntityRepository.findById(divisionEntity.getId()).orElse(null);
        updateDivision.setName(divisionEntity.getName());
        return divisionEntityRepository.save(updateDivision);
    }

    @Override
    public void delete(Long id) {
        divisionEntityRepository.delete(getById(id));
    }

    public List<String> getAllNames(){
        return divisionEntityRepository.findAll().stream().map(DivisionEntity::getName).
                collect(java.util.stream.Collectors.toList());
    }
}
