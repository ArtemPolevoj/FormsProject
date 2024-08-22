package forms.application.service;

import forms.application.dao.DivisionDao;
import forms.application.model.DivisionEntity;
import forms.application.service.dto.DivisionDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DivisionServiceImpl implements DivisionService {
    private final DivisionDao dao;

    @Override
    public List<DivisionEntity> findAll() {
        return dao.findAll();
    }

    @Override
    public DivisionEntity findById(Long id) {
        return dao.findById(id).orElseThrow(()->
                new EntityNotFoundException("Division with id = %d not found".formatted(id)));
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    @Override
    public DivisionEntity create(DivisionDto division) {
        return dao.save(DivisionDto.convert(division));
    }

    @Override
    public DivisionEntity update(DivisionDto dto, Long id) {
        DivisionEntity division = this.findById(id);

        division.setName(dto.getName());

        return dao.save(division);
    }
}
