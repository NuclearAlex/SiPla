package by.homiel.shutov.service.download.csv_format;

import by.homiel.shutov.dto.data.DownloadDataRequestDto;
import by.homiel.shutov.service.download.BuildFileService;
import by.homiel.shutov.service.metadata.MetadataExtractor;
import by.homiel.shutov.repository.util.FileDataService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static by.homiel.shutov.repository.util.FileType.CSV;


@Slf4j
@Service
@AllArgsConstructor
public class BuildFileCsvFormatService implements BuildFileService {
    public static final String NULL = "null";

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
            Map<String, List<String>> allTables = metadataExtractor.getMetaData();
            allTables.forEach((tableName, fields) ->
                    // write table data
                    writeTableData(outputStream, metadataExtractor, tableName, fileDataService, fields.size()));
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

        try {
            for (int i = 0; i < allData.size() / fieldsCount; i++) {
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
