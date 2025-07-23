import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Round {
    private String nameOfRound;
    private int timeToRound;
    private List<Topic> topic;

    @Override
    public String toString() {
        return String.format("""
                        Round: %s
                        Round time: %s seconds
                        >>>>>>>>>>>>>>>>>>
                        Topics:
                        <<<<<<<<<<<<<<<<<<
                        %s
                        """,
                nameOfRound,
                timeToRound,
                topic.stream()
                        .map(Topic::toString)
                        .collect(Collectors.joining(""))
                        .trim()
        );
    }
}
