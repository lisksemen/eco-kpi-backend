package ua.kpi.eco.controller.file;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ua.kpi.eco.service.file.PollutionFileService;

@RestController
@RequestMapping("api/v1/file")
@RequiredArgsConstructor
public class PollutionFileUploadController {

    private final PollutionFileService pollutionFileService;

    @PostMapping
    public void save(@RequestParam("file") MultipartFile file) throws IOException {
        pollutionFileService.save(file);
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportToExcel() throws IOException {
        ByteArrayOutputStream result = pollutionFileService.export();
        byte[] resultBytes = result.toByteArray();
        HttpHeaders fileExportHeaders = getFileExportHeaders(resultBytes.length);

        return ResponseEntity.ok().headers(fileExportHeaders).body(resultBytes);
    }

    private static HttpHeaders getFileExportHeaders(long length) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentDispositionFormData("attachment", "pollution.xlsx");
        httpHeaders.setContentLength(length);
        return httpHeaders;
    }
}