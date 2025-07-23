package by.homiel.shutov.service;

import by.homiel.shutov.dto.data.DownloadDataRequestDto;
import by.homiel.shutov.dto.data.DownloadDataResponseDto;
import by.homiel.shutov.dto.data.UploadDataRequestDto;
import by.homiel.shutov.dto.data.UploadDataResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface WorkService {

    DownloadDataResponseDto download(DownloadDataRequestDto downloadDataRequestDto) throws IOException;

    UploadDataResponseDto upload(UploadDataRequestDto dto, MultipartFile file);
}
