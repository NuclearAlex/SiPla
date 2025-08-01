package by.homiel.shutov.sipla_web.service.impl;

import by.homiel.shutov.sipla_web.dto.data.DownloadDataRequestDto;
import by.homiel.shutov.sipla_web.dto.data.DownloadDataResponseDto;
import by.homiel.shutov.sipla_web.dto.data.UploadDataResponseDto;
import by.homiel.shutov.sipla_web.service.WorkService;
import by.homiel.shutov.sipla_web.service.delete.FileDeleteService;
import by.homiel.shutov.sipla_web.service.download.FileDownloadService;
import by.homiel.shutov.sipla_web.service.upload.FileUploadService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static by.homiel.shutov.sipla_web.utils.Constants.NEXT_LINE;

@Service
@AllArgsConstructor
public class WorkServiceImpl implements WorkService {
    private final FileDownloadService fileDownloadService;
    private final FileUploadService fileUploadService;
    private final FileDeleteService fileDeleteService;

    @Override
    public DownloadDataResponseDto download(DownloadDataRequestDto downloadDataRequestDto) throws IOException {

        // В будущем здесь нужен внешний сервис для хранения файлов (minio, dropbox, ...)
        ByteArrayOutputStream bytes = fileDownloadService.getFileType(
                downloadDataRequestDto, downloadDataRequestDto.fileType().getType());

        return DownloadDataResponseDto
                .builder()
                .file(bytes)
                .build();
    }

    @Override
    public UploadDataResponseDto upload(MultipartFile file) {
        String result = StringUtils.EMPTY;
        try {
            String dataFromFile = convertDataFromFileToString(file.getInputStream());

            // нужно перенаправить по названию таблицы в маппер и смапить в сущности для последующего сохранения
            result = fileUploadService.writeToDatabase(dataFromFile);
        } catch (IOException ex) {
            ex.getLocalizedMessage();
        }

        return UploadDataResponseDto
                .builder()
                .message(result + file.getOriginalFilename())
                .build();
    }

    @Override
    public String deleteDocument(String docName) {
        return fileDeleteService.deleteDocument(docName);
    }

    private static String convertDataFromFileToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line).append(NEXT_LINE);
        }
        bufferedReader.close();
        return stringBuilder.toString();
    }
}
