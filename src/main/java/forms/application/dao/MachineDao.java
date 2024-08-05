package forms.application.dao;

import forms.application.model.MachineryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MachineDao extends JpaRepository<MachineryEntity, String> {
}
