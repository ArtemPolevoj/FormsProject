package forms.application.dao;

import forms.application.model.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationDao extends JpaRepository<OrganizationEntity, Long> {
    Optional<OrganizationEntity> findByName(String name);
}
