package by.homiel.shutov.sipla_web.dto.data;

import by.homiel.shutov.sipla_web.utils.FileType;
import lombok.Builder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * DTO для запроса выгрузки данных из БД в требуемом формате
 */
@Builder
public record DownloadDataRequestDto(String fileName, FileType fileType) {
    public String getFileName() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String format = formatter.format(now);

        return String.format("%s (SiPla, %s).%s", fileName, format, fileType.getType().toLowerCase());
    }
}
