package by.homiel.shutov.sipla_web.dto.data;

import lombok.Builder;

import java.io.ByteArrayOutputStream;

/**
 * DTO для возврата результата выгрузки данных из БД
 */
@Builder
public record DownloadDataResponseDto(ByteArrayOutputStream file) {
}
