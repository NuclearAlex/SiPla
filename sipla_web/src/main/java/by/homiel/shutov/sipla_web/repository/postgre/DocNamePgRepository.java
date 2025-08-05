package by.homiel.shutov.sipla_web.repository.postgre;

import by.homiel.shutov.sipla_web.entity.postgre.pub.DocNamePgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocNamePgRepository extends JpaRepository<DocNamePgEntity, Long> {
    Optional<DocNamePgEntity> findByDocName(String docName);
}
