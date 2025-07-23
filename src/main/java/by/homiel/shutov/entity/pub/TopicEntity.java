package by.homiel.shutov.entity.pub;

import by.homiel.shutov.entity.BaseEntity;
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
@Table(name = "topics")
public class TopicEntity extends BaseEntity {

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rounds_id")
    @Cascade(CascadeType.MERGE)
    private RoundEntity rounds;

    @Column(name = "topic_name")
    private String topicName;

    @OneToMany(mappedBy = "topics", orphanRemoval = true)
    private List<QuestionEntity> questions = new ArrayList<>();


    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof TopicEntity topicEntity)) {
            return false;
        }
        return Objects.equals(getId(), topicEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
