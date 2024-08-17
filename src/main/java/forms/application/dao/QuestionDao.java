package forms.application.dao;

import forms.application.model.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface QuestionDao extends JpaRepository<QuestionEntity, Long> {
    @Override
    @Query("select q from QuestionEntity q order by q.level asc")
    List<QuestionEntity> findAll();

    @Query("select q from QuestionEntity q where q.id in :ids")
    Set<QuestionEntity> findAllByIds(@Param("ids") Set<Long> ids);
}
