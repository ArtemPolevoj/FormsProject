package forms.application.repository;

import forms.application.model.MachineryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MachineryEntityRepository extends JpaRepository<MachineryEntity, Long> {
}
