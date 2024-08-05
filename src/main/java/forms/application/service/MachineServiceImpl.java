package forms.application.service;

import forms.application.dao.MachineDao;
import forms.application.model.MachineryEntity;
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

    @Override
    public List<MachineryEntity> findAll() {
        return machineDao.findAll();
    }

    @Override
    public MachineryEntity findBySerialNumber(@NotNull String serialNumber) {
        return machineDao.findById(serialNumber)
                .orElseThrow(()
                        -> new EntityNotFoundException("Machine with serial number = " + serialNumber + " not found"));
    }

    @Override
    public void deleteBySerialNumber(@NotNull String serialNumber) {
        machineDao.deleteById(serialNumber);
    }

    @Override
    public MachineryEntity create(MachineDto machine) {
        Optional<MachineryEntity> bySerialNumber = machineDao.findById(machine.getSerialNumber());
        if (bySerialNumber.isPresent()) {
            throw new EntityExistsException(
                    "Machine with serial number = " + machine.getSerialNumber() + " is already exists");
        }

        return machineDao.save(MachineDto.convert(machine));
    }

    @Override
    public MachineryEntity update(MachineDto machine) {
        MachineryEntity bySerialNumber = this.findBySerialNumber(machine.getSerialNumber());

        bySerialNumber.setBusinessNumber(machine.getBusinessNumber());
        bySerialNumber.setModel(machine.getModel());
        bySerialNumber.setOperatingTime(machine.getOperatingTime());
        bySerialNumber.setManufacturer(machine.getManufacturer());

        return machineDao.save(bySerialNumber);
    }
}
