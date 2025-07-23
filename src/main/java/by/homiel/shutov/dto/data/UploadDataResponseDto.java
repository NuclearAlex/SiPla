package by.homiel.shutov.dto.data;

import lombok.Builder;

/**
 * DTO для возврата результата выгрузки данных в БД
 */
@Builder
public record UploadDataResponseDto(String message) {
}
