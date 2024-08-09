package forms.application.repository;

import forms.application.model.DivisionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DivisionEntityRepository extends JpaRepository<DivisionEntity, Long> {

}

