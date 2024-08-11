package forms.application.service;

import forms.application.model.MachineryEntity;
import forms.application.repository.MachineryEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MachineryEntityServiceImpl implements ServiceEntity<MachineryEntity> {

    private final MachineryEntityRepository machineryEntityRepository;

    @Override
    public MachineryEntity create(MachineryEntity machineryEntity) {
        return machineryEntityRepository.save(machineryEntity);
    }

    @Override
    public List<MachineryEntity> getAll() {
        return machineryEntityRepository.findAll();
    }

    @Override
    public MachineryEntity getById(Long id) {
        return null;
    }

    @Override
    public MachineryEntity update(MachineryEntity param) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    public List<MachineryEntity> getByOrganization(String organization) {
        List<MachineryEntity> machineryEntities = new ArrayList<>();
        for (MachineryEntity machineryEntity : getAll()) {
            if (machineryEntity.getOrganization().equals(organization)) {
                machineryEntities.add(machineryEntity);
            }
        }
        return machineryEntities;
    }

}
