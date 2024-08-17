package forms.application.dao;

import forms.application.model.DivisionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DivisionDao extends JpaRepository<DivisionEntity, Long> {
}
