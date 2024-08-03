package forms.application.repository;


import forms.application.model.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationEntityRepository extends JpaRepository<OrganizationEntity, Long> {
}
