package by.homiel.shutov.sipla_web.service.impl;

import by.homiel.shutov.sipla_web.dto.data.DownloadDataRequestDto;
import by.homiel.shutov.sipla_web.dto.data.DownloadDataResponseDto;
import by.homiel.shutov.sipla_web.dto.data.UploadDataResponseDto;
import by.homiel.shutov.sipla_web.service.WorkService;
import by.homiel.shutov.sipla_web.service.download.FileTypeBuildService;
import by.homiel.shutov.sipla_web.service.upload.FileTypeUploadService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
@AllArgsConstructor
public class WorkServiceImpl implements WorkService {
    private final FileTypeBuildService fileTypeBuildService;
    private final FileTypeUploadService fileTypeUploadService;

    @Override
    public DownloadDataResponseDto download(DownloadDataRequestDto downloadDataRequestDto) throws IOException {

        // В будущем здесь нужен внешний сервис для хранения файлов (minio, dropbox, ...)
        ByteArrayOutputStream bytes = fileTypeBuildService.getFileType(
                downloadDataRequestDto, downloadDataRequestDto.fileType().getType());

        return DownloadDataResponseDto
                .builder()
                .file(bytes)
                .build();
    }

    @Override
    public UploadDataResponseDto upload(MultipartFile file) {
        try {
            String dataFromFile = convertDataFromFileToString(file.getInputStream());

            // нужно перенаправить по названию таблицы в маппер и смапить в сущности для последующего сохранения
            String result = fileTypeUploadService.writeToDatabase(dataFromFile);
        } catch (IOException ex) {
            ex.getLocalizedMessage();
        }

        return UploadDataResponseDto
                .builder()
                .message("Success. Upload data from file: " + file.getOriginalFilename())
                .build();
    }

    private static String convertDataFromFileToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        bufferedReader.close();
        return stringBuilder.toString();
    }
}
