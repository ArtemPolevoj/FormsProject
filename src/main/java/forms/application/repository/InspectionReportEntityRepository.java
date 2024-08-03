package forms.application.repository;


import forms.application.model.InspectionReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InspectionReportEntityRepository extends JpaRepository<InspectionReportEntity, Long> {
}
