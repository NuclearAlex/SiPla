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
public class Topic {
    private String topicName;
    private List<Question> questions;

    @Override
    public String toString() {
        return String.format("""
                        Topic: %s
                        =================
                        Questions:
                        *****************
                        %s
                        """,
                topicName,
                questions.stream()
                        .map(Object::toString)
                        .collect(Collectors.joining())
                        .trim()
        );
    }
}
