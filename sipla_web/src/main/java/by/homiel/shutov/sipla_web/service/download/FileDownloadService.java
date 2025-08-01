package by.homiel.shutov.sipla_web.service.download;

import by.homiel.shutov.sipla_web.dto.data.DownloadDataRequestDto;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

@Service
public class FileDownloadService {
    private final Map<String, BuildFileService> typeFileMap;

    public FileDownloadService(List<BuildFileService> repos) {
        this.typeFileMap = repos.stream().collect(toMap(BuildFileService::getType, identity()));
    }

    public ByteArrayOutputStream getFileType(DownloadDataRequestDto downloadDataRequestDto, String type) throws IOException {
        return typeFileMap.get(type.toUpperCase()).buildFile(downloadDataRequestDto);
    }
}
