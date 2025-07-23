package by.homiel.shutov.entity.pub;

import by.homiel.shutov.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Table(name = "questions")
public class QuestionEntity extends BaseEntity {

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topics_id")
    @Cascade(CascadeType.MERGE)
    private TopicEntity topics;

    @NotNull
    @Column(name = "answer_time")
    private int answerTime;

    @NotNull
    private int nominal;

    private String text;

    private String answer;

    @Column(name = "additional_answer")
    private String additionalAnswer;

    private String source;


    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof QuestionEntity questionEntity)) {
            return false;
        }
        return Objects.equals(getId(), questionEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
