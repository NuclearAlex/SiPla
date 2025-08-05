package by.homiel.shutov.sipla_web.service.delete;

import by.homiel.shutov.sipla_web.entity.postgre.pub.DocNamePgEntity;
import by.homiel.shutov.sipla_web.repository.postgre.DocNamePgRepository;
import by.homiel.shutov.sipla_web.repository.postgre.QuestionPgRepository;
import by.homiel.shutov.sipla_web.repository.postgre.TopicPgRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@AllArgsConstructor
public class FileDeleteService {

    private final QuestionPgRepository questionPgRepository;
    private final TopicPgRepository topicPgRepository;
    private final DocNamePgRepository docNamePgRepository;

    @Transactional
    public String deleteDocument(String docName) {
        DocNamePgEntity docNamePgEntity = docNamePgRepository.findByDocName(docName).orElse(null);
        if (docNamePgEntity != null) {
            Long topicId = Objects.requireNonNull(topicPgRepository.findById(docNamePgEntity.getDocTopics().getId()).orElse(null)).getId();
            questionPgRepository.deleteByTopics_Id(topicId);
            docNamePgRepository.deleteById(docNamePgEntity.getId());
            topicPgRepository.deleteById(topicId);
        }
        return "The document with title \"" + docName + "\" has been deleted";
    }
}
