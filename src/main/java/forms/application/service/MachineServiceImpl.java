package forms.application.service;

import forms.application.dao.MachineDao;
import forms.application.model.MachineEntity;
import forms.application.model.TypeMachineEntity;
import forms.application.service.dto.MachineDto;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MachineServiceImpl implements MachineService {
    private final MachineDao machineDao;

    private final TypeMachineService typeService;

    @Override
    public List<MachineEntity> findAll() {
        return machineDao.findAll();
    }

    @Override
    public MachineEntity findBySerialNumber(@NotNull String serialNumber) {
        return machineDao.findById(serialNumber)
                .orElseThrow(()
                        -> new EntityNotFoundException("Machine with serial number = " + serialNumber + " not found"));
    }

    @Override
    public void deleteBySerialNumber(@NotNull String serialNumber) {
        machineDao.deleteById(serialNumber);
    }

    @Override
    public MachineEntity create(MachineDto machine) {
        Optional<MachineEntity> bySerialNumber = machineDao.findById(machine.getSerialNumber());
        if (bySerialNumber.isPresent()) {
            throw new EntityExistsException(
                    "Machine with serial number = " + machine.getSerialNumber() + " is already exists");
        }

        TypeMachineEntity type = typeService.findByTypeMachine(machine.getModel());

        return machineDao.save(MachineDto.convert(machine, type));
    }

    @Override
    public MachineEntity update(MachineDto machine) {
        TypeMachineEntity type = typeService.findByTypeMachine(machine.getModel());

        MachineEntity bySerialNumber = this.findBySerialNumber(machine.getSerialNumber());

        bySerialNumber.setBusinessNumber(machine.getBusinessNumber());
        bySerialNumber.setTypeMachine(type);
        bySerialNumber.setOperatingTime(machine.getOperatingTime());
        bySerialNumber.setManufacturer(machine.getManufacturer());

        return machineDao.save(bySerialNumber);
    }

    public List<MachineEntity> getAll() {
        return machineDao.findAll();
    }
}
