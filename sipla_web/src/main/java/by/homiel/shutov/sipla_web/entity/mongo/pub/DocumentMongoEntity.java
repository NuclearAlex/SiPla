package by.homiel.shutov.sipla_web.entity.mongo.pub;

import by.homiel.shutov.sipla_web.entity.mongo.model.TopicModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
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
public class DocumentMongoEntity {
    @Id
    private String id;

    @Field(value = "round_name")
    private String roundName;

    @Field(value = "round_time")
    private int roundTime;

    @DBRef
    private List<TopicModel> topics;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        DocumentMongoEntity that = (DocumentMongoEntity) object;
        return roundTime == that.roundTime &&
                Objects.equals(id, that.id) &&
                Objects.equals(roundName, that.roundName) &&
                Objects.equals(topics, that.topics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roundName, roundTime, topics);
    }
}
