package forms.application.dao;

import forms.application.model.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReportDao extends JpaRepository<ReportEntity, Long> {
    //todo не работает т.к. внутренняя сущность отчета хранится в JSONB. Переписать
//    Optional<ReportEntity> findFirstByReportMachinerySerialNumberAndReportDate(String serialNumber, LocalDate date);

//    List<ReportEntity> findAllByReportOrganizationId(Long id);
}
