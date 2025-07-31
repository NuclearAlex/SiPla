package by.homiel.shutov.sipla_web.entity.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;

import static by.homiel.shutov.sipla_web.utils.Constants.MONGO_COLLECTION;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = MONGO_COLLECTION)
public class QuestionModel {

    private Integer nominal;

    private String text;

    private String answer;

    @Field(value = "additional_answer")
    private String additionalAnswer;

    private String source;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        QuestionModel that = (QuestionModel) object;
        return Objects.equals(nominal, that.nominal) &&
                Objects.equals(text, that.text) &&
                Objects.equals(answer, that.answer) &&
                Objects.equals(additionalAnswer, that.additionalAnswer) &&
                Objects.equals(source, that.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nominal, text, answer, additionalAnswer, source);
    }
}
