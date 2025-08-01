package by.homiel.shutov.sipla_web.service.download;

import by.homiel.shutov.sipla_web.dto.data.DownloadDataRequestDto;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public interface BuildFileService {

    ByteArrayOutputStream buildFile(DownloadDataRequestDto downloadDataRequestDto) throws IOException;

    String getType();
}
