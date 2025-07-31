package by.homiel.shutov.sipla_web.entity.postgre.pub;

import by.homiel.shutov.sipla_web.entity.postgre.BasePgEntity;
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
@Table(name = "questions_pg")
public class QuestionPgEntity extends BasePgEntity {

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topics_id")
    @Cascade(CascadeType.MERGE)
    private TopicPgEntity topics;

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
        if (!(object instanceof QuestionPgEntity questionEntity)) {
            return false;
        }
        return Objects.equals(getId(), questionEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
