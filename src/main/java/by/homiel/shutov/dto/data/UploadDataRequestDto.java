package by.homiel.shutov.dto.data;


import by.homiel.shutov.repository.util.FileType;
import lombok.Builder;

/**
 * DTO для запроса загрузки данных в БД в требуемом формате
 */
@Builder
public record UploadDataRequestDto(FileType format) {
}
