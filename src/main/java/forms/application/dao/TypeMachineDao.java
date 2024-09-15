package forms.application.dao;

import forms.application.model.ModelEntity;
import forms.application.model.TypeMachineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeMachineDao extends JpaRepository<TypeMachineEntity,  Long> {
    Optional<TypeMachineEntity> findByTypeMachine(String typeMachine);
}
