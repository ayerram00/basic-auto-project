package com.sqa.ay;

import java.io.*;
import java.util.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.testng.*;
import org.testng.annotations.*;
import org.testng.annotations.Test;

public class ApachePOITest {
	@DataProvider
	public Object[][] getData() {
		Object[][] data = DataHelper.getExcelFileData("src/main/resources/", "calc-area-dp.xlsx", true);
		DisplayHelper.multArray(data);
		return data;

	}

	@Test(enabled = false)
	public void test() {
		try {

			// Get File based on class loader (Setup Needed)
			ClassLoader classLoader = ApachePOITest.class.getClassLoader();
			//
			// Get InputStream via Class Loader (Setup Needed)
			// InputStream file =
			// classLoader.getResourceAsStream("poi-example.xls");

			// Get the file using basic File and relative path to directory
			InputStream OldExcelFormatFile = classLoader.getResourceAsStream("poi-example.xls");
			InputStream NewExcelFormatFile = classLoader.getResourceAsStream("poi-example.xlsx");

			// Get the workbook instance for XLS file
			XSSFWorkbook workbook = new XSSFWorkbook(NewExcelFormatFile);

			// Get first sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				// For each row, iterate through each columns
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					// Gather and print contents
					Cell cell = cellIterator.next();

					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BOOLEAN:
						// System.out.println("Calling a boolean value!!!!");
						System.out.print(cell.getBooleanCellValue() + "\t\t\t");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						System.out.print(cell.getNumericCellValue() + "\t\t\t");
						break;
					case Cell.CELL_TYPE_STRING:
						System.out.print(cell.getStringCellValue() + "\t\t\t");
						break;
					}
				}
				System.out.println("");
			}
			// Close File Read Stream
			NewExcelFormatFile.close();
			// Create an OutputStream to write
			FileOutputStream out = new FileOutputStream(new File("src/main/resources/excel-output.xls"));
			// Write the workbook
			workbook.write(out);
			// Close output Stream
			out.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("javadoc")
	@Test(dataProvider = "getData")
	public void testCalcAreaRectangle(String shape, int expectedResult, int parameter1, int parameter2)
			throws IncorrectShapeParameterException, UnSupportedShapeException {
		// int parameter = 4;
		// String shape = "RECTANGLE";
		int actualResult = 0;
		if (shape.equalsIgnoreCase("Circle")) {
			actualResult = calcArea(Shape.CIRCLE, parameter1);
		} else if (shape.equalsIgnoreCase("Square")) {
			actualResult = calcArea(Shape.SQUARE, parameter1);
		} else if (shape.equalsIgnoreCase("Rectangle")) {
			actualResult = calcArea(Shape.RECTANGLE, parameter1, parameter2);
		} else {
			throw new UnSupportedShapeException();
		}
		Assert.assertEquals(actualResult, expectedResult);
		;
	}

	@Test(enabled = false)
	public void testCalcAreaSquare() throws IncorrectShapeParameterException {
		int parameter = 4;
		String shape = "Square";
		int actualResult = calcArea(Shape.SQUARE, parameter);
		Assert.assertEquals(actualResult, 16);
	}

	private int calcArea(Shape shape, int... parameters) throws IncorrectShapeParameterException {
		switch (shape) {
		case SQUARE:
			if (parameters.length == 1) {
				return parameters[0] * parameters[0];
			} else {
				throw new IncorrectShapeParameterException();
			}

		case RECTANGLE:
			if (parameters.length == 2) {
				return parameters[0] * parameters[1];
			} else {
				throw new IncorrectShapeParameterException();
			}

		case CIRCLE:
			if (parameters.length == 2) {
				return (int) 3.14 * parameters[0] * parameters[0];
			} else {
				throw new IncorrectShapeParameterException();
			}
		default:
			System.out.println("Shape is not supported");
		}
		return 0;
	}

}
