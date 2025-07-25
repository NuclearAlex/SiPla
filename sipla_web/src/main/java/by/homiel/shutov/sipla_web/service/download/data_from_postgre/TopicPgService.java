package by.homiel.shutov.sipla_web.service.download.data_from_postgre;//package by.homiel.shutov.service.download.data_from_table;
//
//import by.homiel.shutov.repository.TopicRepository;
//import by.homiel.shutov.repository.util.Table;
//import by.homiel.shutov.repository.util.TableDataService;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@AllArgsConstructor
//public class TopicService implements TableDataService {
//    private TopicRepository topicRepository;
//
//    @Override
//    public List<String> getTableData() {
//        List<String> result = new ArrayList<>();
//        topicRepository
//                .findAll()
//                .forEach(topic -> {
//                    String topicName = String.valueOf(topic.getTopicName());
//                    String roundsId = String.valueOf(topic.getRounds().getId());
//                    String questions = topic.getQuestions().stream()
//                            .map(question ->
//                                    question.getNominal() + " * " +
//                                            question.getText() + " * " +
//                                            question.getAnswer() + " * " +
//                                            question.getAdditionalAnswer() + " * "
//                                            + question.getSource() + "\n")
//                            .collect(Collectors.joining())
//                            .trim();
//
//                    result.add(topicName);
//                    result.add(roundsId);
//                    result.add(questions);
//                });
//        return result;
//    }
//
//    @Override
//    public String getTableName() {
//        return Table.TOPICS.getTableName();
//    }
//}
