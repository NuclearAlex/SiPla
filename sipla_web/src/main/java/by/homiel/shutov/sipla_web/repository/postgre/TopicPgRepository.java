package by.homiel.shutov.sipla_web.repository.postgre;

import by.homiel.shutov.sipla_web.entity.postgre.pub.TopicPgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicPgRepository extends JpaRepository<TopicPgEntity, Long> {
}
