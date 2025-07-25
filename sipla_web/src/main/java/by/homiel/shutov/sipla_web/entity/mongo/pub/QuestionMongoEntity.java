package by.homiel.shutov.sipla_web.entity.mongo.pub;

import by.homiel.shutov.sipla_web.entity.mongo.BaseMongoEntity;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "questions_mongo")
public class QuestionMongoEntity extends BaseMongoEntity {

    private String topic_id;

    private Integer answer_time;

    private Integer nominal;

    private String text;

    private String answer;

    private String additional_answer;

    private String source;

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof QuestionMongoEntity questionMongoEntity)) {
            return false;
        }
        return Objects.equals(getId(), questionMongoEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
