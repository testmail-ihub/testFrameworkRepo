package com.companyname.projectname.utils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class ExcelUtils {

    private static final Logger logger = LoggerFactory.getLogger(ExcelUtils.class);

    public static List<List<String>> readExcel(String filePath, String sheetName) {
        List<List<String>> data = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            if (workbook.getSheet(sheetName) != null) {
                Sheet sheet = workbook.getSheet(sheetName);
                Iterator<Row> rowIterator = sheet.iterator();

                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    Iterator<Cell> cellIterator = row.iterator();
                    List<String> rowData = new ArrayList<>();

                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        switch (cell.getCellType()) {
                            case STRING:
                                rowData.add(cell.getStringCellValue());
                                break;
                            case NUMERIC:
                                rowData.add(String.valueOf(cell.getNumericCellValue()));
                                break;
                            case BOOLEAN:
                                rowData.add(String.valueOf(cell.getBooleanCellValue()));
                                break;
                            case BLANK:
                                rowData.add("");
                                break;
                            default:
                                logger.warn("Unsupported cell type encountered: {}", cell.getCellType());
                                rowData.add("");
                                break;
                        }
                    }
                    if (!rowData.isEmpty()) {
                        data.add(rowData);
                    }
                }
            } else {
                logger.error("Sheet {} not found in the Excel file", sheetName);
            }
        } catch (IOException e) {
            logger.error("Error reading Excel file: {}", e.getMessage());
        }
        return data;
    }

    public static void writeExcel(String filePath, String sheetName, List<List<String>> data) {
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                sheet = workbook.createSheet(sheetName);
            }
            int rowCount = 0;
            for (List<String> rowData : data) {
                Row row = sheet.createRow(rowCount++);
                int cellCount = 0;
                for (String cellValue : rowData) {
                    Cell cell = row.createCell(cellCount++);
                    cell.setCellValue(cellValue);
                }
            }
            try (FileOutputStream fos = new FileOutputStream(new File(filePath))) {
                workbook.write(fos);
            }
        } catch (IOException e) {
            logger.error("Error writing to Excel file: {}", e.getMessage());
        }
    }
}
