package by.homiel.shutov.sipla_web.repository.mongo;

import by.homiel.shutov.sipla_web.entity.postgre.pub.TopicPgPgEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicMongoRepository extends MongoRepository<TopicPgPgEntity, Long> {
}
