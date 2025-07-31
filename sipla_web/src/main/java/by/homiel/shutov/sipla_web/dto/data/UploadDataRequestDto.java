package by.homiel.shutov.sipla_web.dto.data;


import by.homiel.shutov.sipla_web.utils.FileType;
import lombok.Builder;

/**
 * DTO для запроса загрузки данных в БД в требуемом формате
 */
@Builder
public record UploadDataRequestDto(FileType format) {
}
