package by.homiel.shutov.sipla_web.entity.postgre.pub;

import by.homiel.shutov.sipla_web.entity.postgre.BasePgEntity;
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
@Table(name = "rounds_pg")
public class RoundPgEntity extends BasePgEntity {

    @Column(name = "round_name")
    private String nameOfRound;

    @Column(name = "round_time")
    private int timeToRound;

    @OneToMany(mappedBy = "rounds", orphanRemoval = true)
    private List<TopicPgEntity> topics = new ArrayList<>();

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof RoundPgEntity roundPgEntity)) {
            return false;
        }
        return Objects.equals(getId(), roundPgEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
