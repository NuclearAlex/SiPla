package by.homiel.shutov.sipla_web.entity.mongo.pub;

import by.homiel.shutov.sipla_web.entity.mongo.BaseMongoEntity;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "rounds_mongo")
public class RoundMongoEntity extends BaseMongoEntity {

    private String round_name;

    private int round_time;

    private List<TopicMongoEntity> topics = new ArrayList<>();

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof RoundMongoEntity roundMongoEntity)) {
            return false;
        }
        return Objects.equals(getId(), roundMongoEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
