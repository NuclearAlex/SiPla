package by.homiel.shutov.sipla_web.controller;

import by.homiel.shutov.sipla_web.controller.swagger.WorkControllerDocumentation;
import by.homiel.shutov.sipla_web.dto.data.DownloadDataRequestDto;
import by.homiel.shutov.sipla_web.dto.data.DownloadDataResponseDto;
import by.homiel.shutov.sipla_web.dto.data.UploadDataResponseDto;
import by.homiel.shutov.sipla_web.service.WorkService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static by.homiel.shutov.sipla_web.utils.Constants.INVALID_DOC_NAME;
import static by.homiel.shutov.sipla_web.utils.Constants.INVALID_FILE;

@RestController
@RequestMapping(value = "/api/v1/data")
@RequiredArgsConstructor
@Tag(name = "SiPla Controller", description = "Controller for requests")
public class WorkController implements WorkControllerDocumentation {

    private final WorkService workService;

    @Override
    @PostMapping("/download")
    public ResponseEntity<Resource> downloadData(@RequestBody DownloadDataRequestDto downloadDataRequestDto) throws IOException {

        DownloadDataResponseDto dataResponseDto = workService.download(downloadDataRequestDto);
        ByteArrayResource resource = new ByteArrayResource(dataResponseDto.file().toByteArray());

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(resource.contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition
                                .attachment()
                                .filename(downloadDataRequestDto.getFileName())
                                .build()
                                .toString())
                .body(resource);
    }

    @Override
    @PostMapping(value ="/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UploadDataResponseDto> uploadData(@RequestPart("File") MultipartFile file) {

        if (file == null) {
            throw new IllegalArgumentException(INVALID_FILE);
        }

        return ResponseEntity.ok(workService.upload(file));
    }

    @Override
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteDocument(String docName) {

        if (StringUtils.isEmpty(docName)) {
            throw new IllegalArgumentException(INVALID_DOC_NAME);
        }
        return ResponseEntity.ok(workService.deleteDocument(docName));
    }
}
