package forms.application.service;

import forms.application.model.ImplementerEntity;
import forms.application.repository.ImplementerEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ImplementerEntityServiceImpl implements ImplementerEntityService {

    private final ImplementerEntityRepository implementerEntityRepository;

    @Override
    public ImplementerEntity create(ImplementerEntity implementerEntity) {
        return implementerEntityRepository.save(implementerEntity);
    }

    @Override
    public List<ImplementerEntity> getAll() {
        return implementerEntityRepository.findAll();
    }

    @Override
    public ImplementerEntity getById(Long id) {
        return implementerEntityRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        ImplementerEntity implementerEntityId = getById(id);
        implementerEntityRepository.delete(implementerEntityId);
    }

    @Override
    public ImplementerEntity update(ImplementerEntity implementerEntity) {
        ImplementerEntity implementerEntityUpdated = getById(implementerEntity.getId());

        implementerEntityUpdated.setEmail(implementerEntity.getEmail());
        implementerEntityUpdated.setPhone(implementerEntity.getPhone());
        implementerEntityUpdated.setEmail(implementerEntity.getEmail());

        return implementerEntityRepository.save(implementerEntityUpdated);
    }
}
