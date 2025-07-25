package by.homiel.shutov.sipla_web.entity.mongo.pub;

import by.homiel.shutov.sipla_web.entity.mongo.BaseMongoEntity;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Document(collection = "topics_mongo")
public class TopicMongoEntity extends BaseMongoEntity {

    private String round_id;

    private String topic_name;

    private List<QuestionMongoEntity> questions = new ArrayList<>();

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof TopicMongoEntity topicMongoEntity)) {
            return false;
        }
        return Objects.equals(getId(), topicMongoEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
