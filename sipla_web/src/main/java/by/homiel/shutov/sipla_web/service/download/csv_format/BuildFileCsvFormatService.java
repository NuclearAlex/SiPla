package by.homiel.shutov.sipla_web.service.download.csv_format;

import by.homiel.shutov.sipla_web.dto.data.DownloadDataRequestDto;
import by.homiel.shutov.sipla_web.repository.util.FileDataService;
import by.homiel.shutov.sipla_web.service.download.BuildFileService;
import by.homiel.shutov.sipla_web.service.metadata.MetadataExtractor;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static by.homiel.shutov.sipla_web.repository.util.FileType.CSV;


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
            if (downloadDataRequestDto.postgre()) {
                Map<String, List<String>> allTables = metadataExtractor.getMetaDataPg();
                allTables.forEach((tableName, fields) ->
                        // write table data
                        writeTableData(outputStream, metadataExtractor, tableName, fileDataService, fields.size()));
            }

            if (downloadDataRequestDto.postgre() && downloadDataRequestDto.mongo()) {
                outputStream.write("//////////////////////////////////////////////////////////////////////\n".getBytes());
                outputStream.write(("""
                        \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
                        \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

                        """).getBytes());
            }

            if (downloadDataRequestDto.mongo()) {
                MongoCollection<Document> metaDataMongo = metadataExtractor.getMetaDataMongo();
                FindIterable<Document> documents = metaDataMongo.find();

                for (Document document : documents) {
                    if (!Objects.isNull(document)) {
                        // write data
                        //TODO: привести в удобочитаемый вид - отработать с монгосервисом
                        outputStream.write(Objects.requireNonNullElse(document.toJson(), NULL).getBytes());
                        outputStream.write("\n".getBytes());
                    }
                }
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
