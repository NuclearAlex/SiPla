package by.homiel.shutov.dto.data;

import lombok.Builder;

import java.io.ByteArrayOutputStream;

/**
 * DTO для возврата результата выгрузки данных из БД
 */
@Builder
public record DownloadDataResponseDto(ByteArrayOutputStream file) {
}
