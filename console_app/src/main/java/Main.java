import by.homiel.shutov.Question;
import by.homiel.shutov.Round;
import by.homiel.shutov.Topic;

import java.util.List;

public class Main {
    public static final int ROUND_TIME = 10800;
    public static final int TIME_TO_ANSWER_TO_QUESTION = 10;
    public static final int TEN_POINTS_NOMINAL = 10;
    public static final int TWENTY_POINTS_NOMINAL = 20;
    public static final int THIRTY_POINTS_NOMINAL = 30;
    public static final int FORTY_POINTS_NOMINAL = 40;
    public static final int FIFTY_POINTS_NOMINAL = 50;

    public static void main(String[] args) {
        /* Question */
        Question question1 = new Question(TIME_TO_ANSWER_TO_QUESTION, TEN_POINTS_NOMINAL, "lalala1", "1",
                "11, 111", "lol1.net");
        Question question2 = new Question(TIME_TO_ANSWER_TO_QUESTION, TWENTY_POINTS_NOMINAL, "lalala2", "2",
                "22, 222", "lol2.net");
        Question question3 = new Question(TIME_TO_ANSWER_TO_QUESTION, THIRTY_POINTS_NOMINAL, "lalala3", "3",
                "33, 333", "lol3.net");
        Question question4 = new Question(TIME_TO_ANSWER_TO_QUESTION, FORTY_POINTS_NOMINAL, "lalala4", "4",
                "44, 444", "lol4.net");
        Question question5 = new Question(TIME_TO_ANSWER_TO_QUESTION, FIFTY_POINTS_NOMINAL, "lalala5", "5",
                "55, 555", "lol5.net");

        Question question6 = new Question(TIME_TO_ANSWER_TO_QUESTION, TEN_POINTS_NOMINAL, "lalala6", "6",
                "66, 666", "lol6.net");
        Question question7 = new Question(TIME_TO_ANSWER_TO_QUESTION, TWENTY_POINTS_NOMINAL, "lalala7", "7",
                "77, 777", "lol7.net");
        Question question8 = new Question(TIME_TO_ANSWER_TO_QUESTION, THIRTY_POINTS_NOMINAL, "lalala8", "8",
                "88, 888", "lol8.net");
        Question question9 = new Question(TIME_TO_ANSWER_TO_QUESTION, FORTY_POINTS_NOMINAL, "lalala9", "9",
                "99, 999", "lol9.net");
        Question question10 = new Question(TIME_TO_ANSWER_TO_QUESTION, FIFTY_POINTS_NOMINAL, "lalala10", "10",
                "100, 1000", "lol10.net");

        System.out.println("Random question:");
        System.out.println(question1);
        System.out.println("---------------------------------");

        /* Topic */
        Topic topic1 = new Topic(
                "Topic A",
                List.of(question1, question2, question3, question4, question5));

        Topic topic2 = new Topic(
                "Topic B",
                List.of(question6, question7, question8, question9, question10));

        System.out.println("Random topic:");
        System.out.println(topic1);
        System.out.println("---------------------------------");

        /* Round */
        Round round1 = new Round(
                "Round 1",
                ROUND_TIME,
                List.of(topic1, topic2)
        );
        System.out.println("Random round:");
        System.out.println(round1);
    }
}
