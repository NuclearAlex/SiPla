package by.homiel.shutov.sipla_web.service;

import by.homiel.shutov.sipla_web.dto.data.DownloadDataRequestDto;
import by.homiel.shutov.sipla_web.dto.data.DownloadDataResponseDto;
import by.homiel.shutov.sipla_web.dto.data.UploadDataResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface WorkService {

    DownloadDataResponseDto download(DownloadDataRequestDto downloadDataRequestDto) throws IOException;

    UploadDataResponseDto upload(MultipartFile file);

    String deleteDocument(String docName);
}
