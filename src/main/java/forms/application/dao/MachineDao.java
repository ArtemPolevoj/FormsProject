package forms.application.dao;

import forms.application.model.MachineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineDao extends JpaRepository<MachineEntity, String> {
}
