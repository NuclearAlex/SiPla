package by.homiel.shutov.sipla_web.service.delete;

import by.homiel.shutov.sipla_web.repository.postgre.QuestionPgRepository;
import by.homiel.shutov.sipla_web.repository.postgre.RoundPgRepository;
import by.homiel.shutov.sipla_web.repository.postgre.TopicPgRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FileDeleteService {

    private final QuestionPgRepository questionPgRepository;
    private final TopicPgRepository topicPgRepository;
    private final RoundPgRepository roundPgRepository;

    public String deleteDocument(String docName) {

        return "The document " + docName + " has been deleted";
    }
}
