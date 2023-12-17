package ua.kpi.eco.service;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ua.kpi.eco.dto.AggregatedPollutionDto;
import ua.kpi.eco.dto.PollutionDto;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PollutionFileService {

    private final PollutionService pollutionService;

    public byte[] exportPollutions() throws IOException {
        List<AggregatedPollutionDto> data = pollutionService.getAll();

        // Create a workbook and sheet
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Exported Data");

            // Create a header row
            Row headerRow = sheet.createRow(0);
            String[] headers = {
                    "ID", "Object Name", "Object Description", "Pollutant Name",
                    "Value Pollution", "Pollutant Mfr", "Pollutant Elv",
                    "Pollutant TLV", "Pollution Concentration", "CR",
                    "HQ", "Fee", "Year", "Tax"
            };

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Add data rows
            int rowNum = 1;
            for (AggregatedPollutionDto exportDto : data) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(exportDto.id());
                row.createCell(1).setCellValue(exportDto.objectName());
                row.createCell(2).setCellValue(exportDto.objectDescription());
                row.createCell(3).setCellValue(exportDto.pollutant().getName()+" "+exportDto.pollutant().getPollutantType().getPollutantTypeName());
                row.createCell(4).setCellValue(exportDto.valuePollution());
                row.createCell(5).setCellValue(exportDto.pollutantMfr());
                row.createCell(6).setCellValue(exportDto.pollutantElv());
                row.createCell(7).setCellValue(exportDto.pollutantTlv());
                row.createCell(8).setCellValue(exportDto.pollutionConcentration());
                row.createCell(9).setCellValue(exportDto.cr());
                row.createCell(10).setCellValue(exportDto.hq());
                row.createCell(11).setCellValue(exportDto.fee());
                row.createCell(12).setCellValue(exportDto.year());
                row.createCell(13).setCellValue(exportDto.tax());
            }

            // Write the workbook to a byte array
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return outputStream.toByteArray();
        }
    }

    @Transactional
    public void parseAndSave(MultipartFile file) throws IOException {
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0); // assuming the data is in the first sheet
            for (Row row : sheet) {
                PollutionDto pollutionRow = parseRowToPollutionDto(row);
                pollutionService.create(pollutionRow);
            }
        }
    }

    private PollutionDto parseRowToPollutionDto(Row row) {
        Cell objectNameCell = row.getCell(0);
        Cell pollutantById = row.getCell(1);
        Cell valuePollutionCell = row.getCell(2);
        Cell yearCell = row.getCell(3);
        Cell concentrationCell = row.getCell(4);

        String objectName = objectNameCell.getStringCellValue().trim();
        Long pollutantId =  (long) pollutantById.getNumericCellValue();
        double valuePollution = getDoubleFromCell(valuePollutionCell);
        int year = (int) yearCell.getNumericCellValue();
        double pollutionConcentration = getDoubleFromCell(concentrationCell);

        return new PollutionDto(objectName,"No Description provided",
                pollutantId, year, valuePollution, pollutionConcentration);
    }

    private double getDoubleFromCell(Cell numericCell) {
        if (numericCell == null) {
            return 0;
        }
        if (numericCell.getCellType().equals(CellType.NUMERIC)) {
            return numericCell.getNumericCellValue();
        }
        String formattedValue = numericCell.getStringCellValue().replace(",", ".");
        return Double.parseDouble(formattedValue);
    }
}
