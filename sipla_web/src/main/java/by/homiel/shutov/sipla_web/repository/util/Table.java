package by.homiel.shutov.sipla_web.repository.util;

import lombok.Getter;

@Getter
public enum Table {
    // сюда нужно добавлять все необходимые таблицы
    QUESTIONS_PG("questions_pg"),
    QUESTIONS_MONGO("questions_mongo");
//    ROUNDS("rounds"),
//    TOPICS("topics");

    private final String tableName;

    Table(String tableName) {
        this.tableName = tableName.toLowerCase();
    }

}
