package by.homiel.shutov.repository.util;

public enum Table {
    // сюда нужно добавлять все необходимые таблицы
    QUESTIONS("questions");
//    ROUNDS("rounds"),
//    TOPICS("topics");

    private final String tableName;

    Table(String tableName) {
        this.tableName = tableName.toLowerCase();
    }

    public String getTableName() {
        return tableName;
    }
}
