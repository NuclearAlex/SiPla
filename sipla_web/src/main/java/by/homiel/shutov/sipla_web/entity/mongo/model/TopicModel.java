package by.homiel.shutov.sipla_web.entity.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Objects;

import static by.homiel.shutov.sipla_web.utils.Constants.MONGO_COLLECTION;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = MONGO_COLLECTION)
public class TopicModel {

    @Field(value = "topic_name")
    private String topicName;

    @DBRef
    private List<QuestionModel> questions;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        TopicModel that = (TopicModel) object;
        return Objects.equals(topicName, that.topicName) && Objects.equals(questions, that.questions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topicName, questions);
    }
}
