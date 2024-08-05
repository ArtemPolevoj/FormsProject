package forms.application.dao;

import forms.application.model.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<QuestionEntity, Integer> {
    List<QuestionEntity> findAllByMachinerySerialNumber(String serialNumber);
}
