package by.homiel.shutov.sipla_web.service.download.csv_format;

import by.homiel.shutov.sipla_web.dto.data.DownloadDataRequestDto;
import by.homiel.shutov.sipla_web.repository.util.FileDataService;
import by.homiel.shutov.sipla_web.service.download.BuildFileService;
import by.homiel.shutov.sipla_web.service.metadata.MetadataExtractor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static by.homiel.shutov.sipla_web.repository.util.FileType.CSV;
import static by.homiel.shutov.sipla_web.utils.Constants.INVALID_DATABASE;
import static by.homiel.shutov.sipla_web.utils.Constants.MONGO_COLLECTION;
import static by.homiel.shutov.sipla_web.utils.Constants.NULL;


@Slf4j
@Service
@AllArgsConstructor
public class BuildFileCsvFormatService implements BuildFileService {

    private final MetadataExtractor metadataExtractor;
    private final FileDataService fileDataService;

    @Override
    public ByteArrayOutputStream buildFile(DownloadDataRequestDto downloadDataRequestDto) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        outputStream.write(""" 
                *****************************************************************
                Создатели платформы: NuclearAlex и Shavadre. Все права защищены ©
                *****************************************************************
                
                """
                .getBytes());

        try (outputStream) {
            if ((!downloadDataRequestDto.postgre() && !downloadDataRequestDto.mongo())) {
                throw new IllegalArgumentException(INVALID_DATABASE);
            }

            if (downloadDataRequestDto.postgre()) {
                Map<String, List<String>> allTables = metadataExtractor.getMetaDataPg();
                allTables.forEach((tableName, fields) ->
                        // write table data
                        writeTableData(outputStream, metadataExtractor, tableName, fileDataService, fields.size()));
            }

            if (downloadDataRequestDto.postgre() && downloadDataRequestDto.mongo()) {
                outputStream.write("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n\n"
                        .getBytes());
            }

            if (downloadDataRequestDto.mongo()) {
                List<String> allData = fileDataService.getFileData(MONGO_COLLECTION);
                // write table data
                writeDataToOutputStream(outputStream, metadataExtractor, allData.size(), allData);
            }
        }
        return outputStream;
    }

    @Override
    public String getType() {
        return CSV.getType().toUpperCase();
    }

    private static void writeTableData(ByteArrayOutputStream outputStream, MetadataExtractor metadataExtractor,
                                       String tableName, FileDataService fileDataService, int fieldsCount) {

        List<String> allData = fileDataService.getFileData(tableName);
        if (allData.isEmpty()) {
            try {
                outputStream.write("\n".getBytes());
            } catch (IOException e) {
                log.debug(e.getLocalizedMessage());
            }
            return;
        }
        writeDataToOutputStream(outputStream, metadataExtractor, fieldsCount, allData);
    }

    private static void writeDataToOutputStream(ByteArrayOutputStream outputStream, MetadataExtractor metadataExtractor,
                                                int fieldsCount, List<String> allData) {
        int resultSize = allData.size();
        int checkSize = allData.size() % fieldsCount;
        if (checkSize != 0) {
            resultSize = allData.size() - checkSize;
        }

        try {
            for (int i = checkSize; i < resultSize / fieldsCount; i++) {
                for (int j = 0; j < fieldsCount; j++) {
                    String data = allData.get(j + i * fieldsCount);
                    outputStream.write(Objects.requireNonNullElse(data, NULL).getBytes());
                    outputStream.write(j != fieldsCount - 1
                            ? metadataExtractor.getSeparator().getBytes()
                            : "\n".getBytes());
                }
            }
        } catch (IOException e) {
            log.debug(e.getLocalizedMessage());
        }
    }
}
