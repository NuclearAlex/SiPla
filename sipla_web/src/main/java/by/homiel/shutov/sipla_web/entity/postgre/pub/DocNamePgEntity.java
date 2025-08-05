package by.homiel.shutov.sipla_web.entity.postgre.pub;

import by.homiel.shutov.sipla_web.entity.postgre.BasePgEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Table(name = "doc_names_pg")
public class DocNamePgEntity extends BasePgEntity {

    @Column(name = "doc_name")
    private String docName;

    @OneToOne(mappedBy = "docName", orphanRemoval = true)
    private TopicPgEntity documentName;
}
