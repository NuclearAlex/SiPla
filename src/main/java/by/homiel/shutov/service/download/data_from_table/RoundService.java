//package by.homiel.shutov.service.download.data_from_table;
//
//import by.homiel.shutov.repository.RoundRepository;
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
//public class RoundService implements TableDataService {
//    private RoundRepository roundRepository;
//
//    @Override
//    public List<String> getTableData() {
//        List<String> result = new ArrayList<>();
//        roundRepository
//                .findAll()
//                .forEach(round -> {
//                    String roundName = String.valueOf(round.getNameOfRound());
//                    String roundTime = String.valueOf(round.getTimeToRound());
//                    String topics =
//                            String.valueOf(
//                                    round.getTopics()
//                                            .stream()
//                                            .map(topic -> "Topic: " +
//                                                    topic.getTopicName() + "\n=====================\nQuestions:\n" +
//                                                    topic.getQuestions()
//                                                            .stream()
//                                                            .map(question ->
//                                                                    question.getText() + "\n" +
//                                                                            question.getAnswer() + "\n" +
//                                                                            question.getAdditionalAnswer() + "\n" +
//                                                                            question.getSource())
//                                                            .collect(Collectors.joining()))
//                                            .collect(Collectors.joining())
//                            );
//                    result.add("Round: " + roundName);
//                    result.add("\n");
//                    result.add("Round time: " + roundTime + " seconds");
//                    result.add("\n");
//                    result.add(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//                    result.add("Topics:");
//                    result.add("\n");
//                    result.add("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
//                    result.add(topics);
//                });
//        return result;
//    }
//
//    @Override
//    public String getTableName() {
//        return Table.ROUNDS.getTableName();
//    }
//}
