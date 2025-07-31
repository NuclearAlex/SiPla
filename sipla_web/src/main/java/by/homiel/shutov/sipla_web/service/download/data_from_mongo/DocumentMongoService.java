package by.homiel.shutov.sipla_web.service.download.data_from_mongo;

import by.homiel.shutov.sipla_web.repository.mongo.DocumentMongoRepository;
import by.homiel.shutov.sipla_web.repository.util.Table;
import by.homiel.shutov.sipla_web.repository.util.TableDataService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DocumentMongoService implements TableDataService {
    private DocumentMongoRepository documentMongoRepository;

    @Override
    public List<String> getTableData() {
        List<String> result = new ArrayList<>();
        StringBuilder sbt = new StringBuilder();
        documentMongoRepository
                .findAll()
                .forEach(document -> {
                    sbt.append("Раунд: ")
                            .append(document.getRoundName())
                            .append("\n**********************");

                    document.getTopics()
                            .stream()
                            .map(topic -> {
                                sbt.append("\nТема: ").append(topic.getTopicName())
                                        .append("\n========================================");
                                topic.getQuestions()
                                        .stream()
                                        .map(question -> sbt.append("\nВопрос №")
                                                .append(question.getNominal() / 10)
                                                .append("\nНоминал: ")
                                                .append(question.getNominal())
                                                .append(" очков")
                                                .append("\nВопрос: ")
                                                .append(question.getText())
                                                .append("\nОтвет: ")
                                                .append(question.getAnswer())
                                                .append(
                                                        StringUtils.isEmpty(question.getAdditionalAnswer())
                                                                ? "\nЗачёт: Точный ответ"
                                                                : "\nЗачёт: " + question.getAdditionalAnswer())
                                                .append("\nИсточник: ")
                                                .append(question.getSource())
                                                .append("\n----------------------------------------"))
                                        .collect(Collectors.joining());
                                sbt.delete(sbt.length() - 40, sbt.length());
                                sbt.append("\n========================================");
                                return sbt.toString();
                            })
                            .collect(Collectors.joining(""));
                    sbt.delete(sbt.length() - 40, sbt.length());
                    result.add(sbt.toString().trim());
                });
        return result;
    }

    @Override
    public String getTableName() {
        return Table.SIPLAT_COLLECTION.getTableName();
    }
}
