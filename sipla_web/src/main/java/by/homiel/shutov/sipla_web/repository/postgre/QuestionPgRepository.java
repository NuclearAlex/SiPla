package by.homiel.shutov.sipla_web.repository.postgre;

import by.homiel.shutov.sipla_web.entity.postgre.pub.QuestionPgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionPgRepository extends JpaRepository<QuestionPgEntity, Long> {
    List<QuestionPgEntity> findByTopics_Id(Long topicId);
    void deleteByTopics_Id(Long topicId);
}
