package forms.application.dao;

import forms.application.model.ImplementerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImplementerDao extends JpaRepository<ImplementerEntity, Long> {
    Optional<ImplementerEntity> findByEmail(String email);

    ImplementerEntity findByFullName(String customValue);

}
