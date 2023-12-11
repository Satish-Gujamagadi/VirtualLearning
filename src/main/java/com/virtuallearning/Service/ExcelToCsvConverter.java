package com.virtuallearning.Service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

@Service
public class ExcelToCsvConverter {

	public void convertExcelToCsv(String excelFilePath, String csvFilePath) throws IOException {
		try (FileInputStream fis = new FileInputStream(new File(excelFilePath));
				XSSFWorkbook workbook = new XSSFWorkbook(fis);
				FileOutputStream fos = new FileOutputStream(new File(csvFilePath))) {

			// Get the first sheet
			Iterator<Row> rowIterator = workbook.getSheetAt(0).iterator();

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					fos.write((cell.toString() + ",").getBytes());
				}

				// Move to the next line in the CSV file
				fos.write("\n".getBytes());
			}
		}
	}
}
