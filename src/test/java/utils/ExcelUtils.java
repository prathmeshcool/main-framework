package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class ExcelUtils {

    public static Object[][] getSheetData(String filePath, String sheetName) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath);
             XSSFWorkbook wb = new XSSFWorkbook(fis)) {

            Sheet sheet = wb.getSheet(sheetName);
            int rows = sheet.getPhysicalNumberOfRows();
            if (rows <= 1) return new Object[0][0]; // no data

            int cols = sheet.getRow(0).getLastCellNum();
            Object[][] data = new Object[rows - 1][cols];

            Iterator<Row> rowIterator = sheet.rowIterator();
            Row header = rowIterator.next(); // skip header

            int r = 0;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                for (int c = 0; c < cols; c++) {
                    Cell cell = row.getCell(c, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    switch (cell.getCellType()) {
                        case STRING -> data[r][c] = cell.getStringCellValue();
                        case NUMERIC -> data[r][c] = String.valueOf((long) cell.getNumericCellValue());
                        case BOOLEAN -> data[r][c] = String.valueOf(cell.getBooleanCellValue());
                        default -> data[r][c] = cell.toString();
                    }
                }
                r++;
            }
            return data;
        }
    }
}
