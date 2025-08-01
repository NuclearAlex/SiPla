package by.homiel.shutov.sipla_web.service.download.data_from_postgre;

import by.homiel.shutov.sipla_web.repository.postgre.QuestionPgRepository;
import by.homiel.shutov.sipla_web.utils.Table;
import by.homiel.shutov.sipla_web.utils.TableDataService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static by.homiel.shutov.sipla_web.utils.Constants.NEXT_LINE;

@Service
@AllArgsConstructor
public class QuestionPgService implements TableDataService {
    private QuestionPgRepository questionPgRepository;

    @Override
    public List<String> getTableData() {
        List<String> result = new ArrayList<>();
        questionPgRepository
                .findAll()
                .forEach(question -> {
                    result.add("Раунд: " + question.getTopics().getRounds().getNameOfRound());
                    result.add("\nТема: " + question.getTopics().getTopicName());
                    result.add("\nНоминал: " + question.getNominal() + " очков");
                    result.add("\nВопрос: " + question.getText());
                    result.add("\nОтвет: " + question.getAnswer());
                    result.add(StringUtils.isEmpty(question.getAdditionalAnswer())
                                    ? "\nЗачёт: Точный ответ"
                                    : "\nЗачёт: " + question.getAdditionalAnswer());
                    result.add("\nИсточник: " + question.getSource() + NEXT_LINE);
                });
        return result;
    }

    @Override
    public String getTableName() {
        return Table.QUESTIONS_PG.getTableName();
    }
}
