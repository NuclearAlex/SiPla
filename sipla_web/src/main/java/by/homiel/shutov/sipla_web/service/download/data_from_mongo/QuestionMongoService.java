package by.homiel.shutov.sipla_web.service.download.data_from_mongo;

import by.homiel.shutov.sipla_web.repository.mongo.QuestionMongoRepository;
import by.homiel.shutov.sipla_web.repository.util.Table;
import by.homiel.shutov.sipla_web.repository.util.TableDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class QuestionMongoService implements TableDataService {
    private QuestionMongoRepository questionMongoRepository;

    @Override
    public List<String> getTableData() {
        List<String> result = new ArrayList<>();
        questionMongoRepository
                .findAll()
                .forEach(question -> result.add("Вопрос: \n" + question));
        return result;
    }

    @Override
    public String getTableName() {
        return Table.QUESTIONS_MONGO.getTableName();
    }
}
