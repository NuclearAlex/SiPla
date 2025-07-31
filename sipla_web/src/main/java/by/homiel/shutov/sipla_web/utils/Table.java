package by.homiel.shutov.sipla_web.utils;

import lombok.Getter;

import static by.homiel.shutov.sipla_web.utils.Constants.MONGO_COLLECTION;

@Getter
public enum Table {
    // сюда нужно добавлять все необходимые таблицы
    SIPLAT_COLLECTION(MONGO_COLLECTION);

    private final String tableName;

    Table(String tableName) {
        this.tableName = tableName.toLowerCase();
    }
}
