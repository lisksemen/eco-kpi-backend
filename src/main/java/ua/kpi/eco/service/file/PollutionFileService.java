package ua.kpi.eco.service.file;

import com.google.common.collect.Streams;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ua.kpi.eco.dto.AggregatedPollutionDto;
import ua.kpi.eco.dto.PollutionDto;
import ua.kpi.eco.service.PollutantService;
import ua.kpi.eco.service.PollutionService;


@Service
@RequiredArgsConstructor
public class PollutionFileService {
    public static final String[] HEADERS = {
            "ID", "Object Name", "Object Description", "Pollutant Name",
            "Value Pollution", "Pollutant Mfr", "Pollutant Elv",
            "Pollutant TLV", "Pollution Concentration", "CR",
            "HQ", "Year"
    };

    private final PollutionService pollutionService;
    private final PollutantService pollutantService;

    @Transactional
    public void save(MultipartFile file) throws IOException {
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Streams.stream(workbook.getSheetAt(0))
                    .skip(1) // Header
                    .forEach(this::writeRow);
        }
    }

    public ByteArrayOutputStream export() throws IOException {
        List<AggregatedPollutionDto> dataToExport = pollutionService.getAll();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Data");

            addHeader(sheet);
            writeAllData(dataToExport, sheet);

            ByteArrayOutputStream result = new ByteArrayOutputStream();
            workbook.write(result);
            return result;
        }
    }

    private static void writeAllData(List<AggregatedPollutionDto> data, Sheet sheet) {
        int rowNum = 1;
        for (AggregatedPollutionDto exportDto : data) {
            rowNum = writeDataRow(exportDto, sheet, rowNum);
        }
    }

    private static int writeDataRow(AggregatedPollutionDto exportDto, Sheet sheet, int rowNum) {
        Row row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue(exportDto.id());
        row.createCell(1).setCellValue(exportDto.objectName());
        row.createCell(2).setCellValue(exportDto.objectDescription());
        row.createCell(3).setCellValue(exportDto.pollutant().getPollutantType().getPollutantTypeName());
        row.createCell(4).setCellValue(exportDto.valuePollution());
        row.createCell(5).setCellValue(exportDto.pollutantMfr());
        row.createCell(6).setCellValue(exportDto.pollutantElv());
        row.createCell(7).setCellValue(exportDto.pollutantTlv());
        row.createCell(8).setCellValue(exportDto.pollutionConcentration());
        row.createCell(9).setCellValue(exportDto.cr());
        row.createCell(10).setCellValue(exportDto.hq());
        row.createCell(11).setCellValue(exportDto.year());
        return rowNum;
    }

    private static void addHeader(Sheet sheet) {
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < HEADERS.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(HEADERS[i]);
        }
    }

    private PollutionDto parseRowToPollutionDto(Row row) {
        Cell objectNameCell = row.getCell(0);
        Cell pollutantNameCell = row.getCell(1);
        Cell valuePollutionCell = row.getCell(2);
        Cell yearCell = row.getCell(3);
        Cell concentrationCell = row.getCell(4);

        String objectName = objectNameCell.getStringCellValue().trim();
        String pollutantName = pollutantNameCell.getStringCellValue().trim();
        double valuePollution = parseDoubleValue(valuePollutionCell);
        int year = (int) yearCell.getNumericCellValue();
        double pollutionConcentration = parseDoubleValue(concentrationCell);

        return new PollutionDto(objectName, "empty", pollutantService.findIdByName(pollutantName), year, valuePollution, pollutionConcentration);
    }

    private void writeRow(Row row) {
        PollutionDto pollutionRow = parseRowToPollutionDto(row);
        pollutionService.create(pollutionRow);
    }

    private double parseDoubleValue(Cell numericCell) {
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
