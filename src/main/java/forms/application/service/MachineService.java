package forms.application.service;

import forms.application.model.MachineryEntity;
import forms.application.service.dto.MachineDto;

import java.util.List;

public interface MachineService {
    List<MachineryEntity> findAll();

    MachineryEntity findBySerialNumber(String serialNumber);

    void deleteBySerialNumber(String serialNumber);

    MachineryEntity create(MachineDto machine);

    MachineryEntity update(MachineDto machine);
}
