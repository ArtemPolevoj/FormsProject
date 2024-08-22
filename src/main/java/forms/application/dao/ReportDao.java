package forms.application.dao;

import forms.application.model.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportDao extends JpaRepository<ReportEntity, Long> {
    @Query(value = "SELECT ao.id, ao.акт_осмотра " +
            "FROM акты_осмотров ao " +
            "WHERE ao.акт_осмотра -> 'machinery' ->> 'serialNumber' = :serialNumber " +
            "AND ao.акт_осмотра ->> 'date' = :date", nativeQuery = true)
    List<ReportEntity> findByReportMachinerySerialNumberAndReportDate(@Param("date") String date, @Param("serialNumber") String serialNumber);

    @Query(value = "SELECT id, акт_осмотра\n" +
            "FROM акты_осмотров ao\n" +
            "WHERE ao.акт_осмотра -> 'organization' ->> 'id' = CAST(:id AS TEXT)",
            nativeQuery = true)
    List<ReportEntity> findAllByReportOrganizationId(@Param("id") Long organizationId);
}
