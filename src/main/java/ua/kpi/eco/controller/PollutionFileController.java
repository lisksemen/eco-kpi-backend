package ua.kpi.eco.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.kpi.eco.service.PollutionFileService;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/file")
@RequiredArgsConstructor
public class PollutionFileController {

    private final PollutionFileService pollutionFileService;

    @PostMapping
    public ResponseEntity<?> handlePollutionFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        pollutionFileService.parseAndSave(file);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportPollutionsToExcel() throws IOException {
        byte[] excelBytes = pollutionFileService.exportPollutions();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentDispositionFormData("attachment", "exported_data.xlsx");
        httpHeaders.setContentLength(excelBytes.length);

        return ResponseEntity.ok().headers(httpHeaders).body(excelBytes);
    }
}
