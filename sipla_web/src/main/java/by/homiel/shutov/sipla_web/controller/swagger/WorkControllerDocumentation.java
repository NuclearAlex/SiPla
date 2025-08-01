package by.homiel.shutov.sipla_web.controller.swagger;

import by.homiel.shutov.sipla_web.dto.data.DownloadDataRequestDto;
import by.homiel.shutov.sipla_web.dto.data.DownloadDataResponseDto;
import by.homiel.shutov.sipla_web.dto.data.UploadDataResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface WorkControllerDocumentation {

    @Operation(summary = "Download free data (topics, packages) from platform", description = "Access: all users")
    @RequestBody(description = "You'll get data from SiPla")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Download was successful",
                    content = {
                            @Content(
                                    mediaType = "application/octet-stream",
                                    schema = @Schema(implementation = DownloadDataResponseDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Request body validation error",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "The token wan not provided or is not valid",
                    content = @Content
            )
    })
    ResponseEntity<Resource> downloadData(DownloadDataRequestDto downloadDataRequestDto) throws IOException;

    @Operation(summary = "Upload data to platform", description = "Access: all users")
    @RequestBody(description = "You must use files to text format")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Upload was successful",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UploadDataResponseDto.class), examples = @ExampleObject(value = "Success. Uploaded data from file")
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Request body validation error",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "The token wan not provided or is not valid",
                    content = @Content
            )
    })
    ResponseEntity<UploadDataResponseDto> uploadData(MultipartFile file);

    @Operation(summary = "Delete required document", description = "Access: all users")
    @RequestBody(description = "You need to input document name")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The document successfully deleted",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UploadDataResponseDto.class), examples = @ExampleObject(value = "Success. The document deleted")
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Request body validation error",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "The token wan not provided or is not valid",
                    content = @Content
            )
    })
    ResponseEntity<String> deleteDocument(String docName);
}
