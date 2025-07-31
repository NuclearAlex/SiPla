package by.homiel.shutov;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Question {
    private int answerTime;
    private int nominal;
    private String text;
    private String answer;
    private String additionalAnswer;
    private String source;

    @Override
    public String toString() {
        return String.format("""
                                Time to answer: %s seconds
                                Nominal: %s points
                                The question is:
                                [
                                Text: %s
                                Answer: %s
                                Additional answer: %s
                                Source: %s
                                ]
                                
                                """,
                        answerTime, nominal, text, answer, additionalAnswer, source)
                .trim();
    }
}
