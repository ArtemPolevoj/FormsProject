package forms.application.service;

import forms.application.model.TypeMachineEntity;
import forms.application.repository.TypeMachineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TypeMachineEntityServiceImp implements ServiceEntity<TypeMachineEntity> {
    private final TypeMachineRepository typeMachineRepository;

    @Override
    public TypeMachineEntity create(TypeMachineEntity typeMachine) {
        return typeMachineRepository.save(typeMachine);
    }

    @Override
    public List<TypeMachineEntity> getAll() {
        return typeMachineRepository.findAll();
    }

    @Override
    public TypeMachineEntity getById(Long id) {
        return typeMachineRepository.findById(id).orElse(null);
    }

    @Override
    public TypeMachineEntity update(TypeMachineEntity typeMachine) {
        return null;
    }

    @Override
    public void delete(Long id) {
        typeMachineRepository.deleteById(id);
    }
    public List<String> getAllNames(){
        List<String> allNames = new ArrayList<>();
        for (TypeMachineEntity typeMachineEntity : typeMachineRepository.findAll()) {
            allNames.add(typeMachineEntity.getTypeMachine());
        }

        return allNames;
    }
}
