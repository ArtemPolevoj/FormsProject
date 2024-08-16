package forms.application.dao;

import forms.application.model.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MachineTypeDao extends JpaRepository<ModelEntity,  Long> {
    Optional<ModelEntity> findByModel(String model);
}
