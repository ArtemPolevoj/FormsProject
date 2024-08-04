package forms.application.service;

import forms.application.model.MachineryEntity;
import forms.application.repository.MachineryEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MachineryEntityServiceImpl implements MachineryEntityService {

    private final MachineryEntityRepository machineryEntityRepository;

    @Override
    public MachineryEntity create(MachineryEntity machineryEntity) {
        return machineryEntityRepository.save(machineryEntity);
    }

    @Override
    public List<MachineryEntity> getAll() {
        return machineryEntityRepository.findAll();
    }

}

