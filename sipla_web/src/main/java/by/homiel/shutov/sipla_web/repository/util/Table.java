package by.homiel.shutov.sipla_web.repository.util;

import lombok.Getter;

import static by.homiel.shutov.sipla_web.utils.Constants.MONGO_COLLECTION;
import static by.homiel.shutov.sipla_web.utils.Constants.POSTGRES_QUESTIONS_TABLE;
import static by.homiel.shutov.sipla_web.utils.Constants.POSTGRES_ROUNDS_TABLE;
import static by.homiel.shutov.sipla_web.utils.Constants.POSTGRES_TOPICS_TABLE;

@Getter
public enum Table {
    // сюда нужно добавлять все необходимые таблицы
    SIPLAT_COLLECTION(MONGO_COLLECTION),

    QUESTIONS_PG(POSTGRES_QUESTIONS_TABLE);
//    ROUNDS(POSTGRES_ROUNDS_TABLE),
//    TOPICS(POSTGRES_TOPICS_TABLE);

    private final String tableName;

    Table(String tableName) {
        this.tableName = tableName.toLowerCase();
    }
}
