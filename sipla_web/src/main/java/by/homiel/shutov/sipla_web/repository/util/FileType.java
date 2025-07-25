package by.homiel.shutov.sipla_web.repository.util;

import lombok.Getter;

@Getter
public enum FileType {

    // сюда можно добавлять типы файлов
    CSV("csv");

    private final String type;

    FileType(String type) {
        this.type = type.toLowerCase();
    }
}
