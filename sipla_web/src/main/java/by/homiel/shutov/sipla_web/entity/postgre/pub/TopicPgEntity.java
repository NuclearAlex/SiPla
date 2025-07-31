package by.homiel.shutov.sipla_web.entity.postgre.pub;

import by.homiel.shutov.sipla_web.entity.postgre.BasePgEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Table(name = "topics_pg")
public class TopicPgEntity extends BasePgEntity {

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rounds_id")
    @Cascade(CascadeType.MERGE)
    private RoundPgEntity rounds;

    @Column(name = "topic_name")
    private String topicName;

    @OneToMany(mappedBy = "topics", orphanRemoval = true)
    private List<QuestionPgEntity> questions = new ArrayList<>();


    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof TopicPgEntity topicPgEntity)) {
            return false;
        }
        return Objects.equals(getId(), topicPgEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
