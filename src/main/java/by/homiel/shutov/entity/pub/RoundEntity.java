package by.homiel.shutov.entity.pub;

import by.homiel.shutov.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Table(name = "rounds")
public class RoundEntity extends BaseEntity {

    @Column(name = "round_name")
    private String nameOfRound;

    @Column(name = "round_time")
    private int timeToRound;

    @OneToMany(mappedBy = "rounds", orphanRemoval = true)
    private List<TopicEntity> topics = new ArrayList<>();

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof RoundEntity roundEntity)) {
            return false;
        }
        return Objects.equals(getId(), roundEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
