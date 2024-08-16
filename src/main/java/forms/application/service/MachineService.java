package forms.application.service;

import forms.application.model.MachineEntity;
import forms.application.service.dto.MachineDto;

import java.util.List;

public interface MachineService {
    List<MachineEntity> findAll();

    MachineEntity findBySerialNumber(String serialNumber);

    void deleteBySerialNumber(String serialNumber);

    MachineEntity create(MachineDto machine);

    MachineEntity update(MachineDto machine);
}
