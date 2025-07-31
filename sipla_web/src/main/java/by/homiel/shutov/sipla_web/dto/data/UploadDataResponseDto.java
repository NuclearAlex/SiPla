package by.homiel.shutov.sipla_web.dto.data;

import lombok.Builder;

/**
 * DTO для возврата результата выгрузки данных в БД
 */
@Builder
public record UploadDataResponseDto(String message) {
}
