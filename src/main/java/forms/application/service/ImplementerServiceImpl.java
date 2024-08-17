package forms.application.service;

import forms.application.dao.ImplementerDao;
import forms.application.model.ImplementerEntity;
import forms.application.service.dto.ImplementerDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImplementerServiceImpl implements ImplementerService {
    private final ImplementerDao implementerDao;

    @Override
    public List<ImplementerEntity> findAll() {
        return implementerDao.findAll();
    }

    @Override
    public ImplementerEntity findById(@NotNull Long id) {
        return implementerDao.findById(id)
                .orElseThrow(()
                        -> new EntityNotFoundException("Implementer with id = " + id + " not found"));
    }

    @Override
    public void deleteById(@NotNull Long id) {
        implementerDao.deleteById(id);
    }

    @Override
    public ImplementerEntity create(ImplementerDto implementer) {
        return implementerDao.save(ImplementerDto.convert(implementer));
    }

    @Override
    public ImplementerEntity update(ImplementerDto implementer, @NotNull Long id) {
        ImplementerEntity byId = this.findById(id);

        byId.setEmail(implementer.getEmail());
        byId.setPhone(implementer.getPhone());
        byId.setFullName(implementer.getFullName());

        return implementerDao.save(byId);
    }
}
