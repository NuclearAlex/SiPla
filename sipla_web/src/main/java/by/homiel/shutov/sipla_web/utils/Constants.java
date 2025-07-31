package by.homiel.shutov.sipla_web.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {
    public static final String TITLE = "\"Si Platform\" by Nuclearalex & Shavadre";
    public static final String DESCRIPTION = "Платформа для загрузки и скачивания пакетов и тем своей игры. Все права защищены ©";

    public static final String POSTGRES_PUBLIC_SCHEMA = "public";
    public static final String POSTGRES_TABLE_NAME = "TABLE_NAME";
    public static final String POSTGRES_COLUMN_NAME = "COLUMN_NAME";
    public static final String POSTGRES_QUESTIONS_TABLE = "questions_pg";
    public static final String POSTGRES_TOPICS_TABLE = "topics_pg";
    public static final String POSTGRES_ROUNDS_TABLE = "rounds_pg";

    public static final String MONGO_PREFIX = "mongodb://";
    public static final String MONGO_DB = "siplatdb";
    public static final String MONGO_COLLECTION = "siplat_collection";

    public static final String INVALID_FILE = "You must choose any file!";
    public static final String INVALID_DATABASE = "You must choose any database!";

    public static final String NULL = "null";
}
