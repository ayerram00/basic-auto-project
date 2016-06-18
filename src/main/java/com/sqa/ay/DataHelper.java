
package com.sqa.ay;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class DataHelper {

	public static Object[][] getTextFileData(String fileLocation, String fileName, TextFormat textFormat) {

		return getTextFileData(fileLocation, fileName, textFormat, false);
	}

	public static Object[][] getTextFileData(String fileLocation, String fileName, TextFormat textFormat,
			Boolean hasLabels, Object... dataTypes) {
		// Process data
		Object[][] data;
		// Collect data lines from text document supplied
		ArrayList<String> lines = openFileAndCollectData(fileLocation, fileName);
		switch (textFormat) {
		case CSV:
			data = parseCSVData(lines, hasLabels, dataTypes);
			break;
		case XML:
			data = parseXMLData(lines, hasLabels);
			break;
		case TAB:
			data = parseTabData(lines, hasLabels);
			break;
		case JSON:
			data = parseJSONData(lines, hasLabels);
			break;
		default:
			data = null;
			break;
		}
		return data;
	}

	private static Object convertDataType(String parameter, Object dataType) {
		if (dataType.equals(Integer.TYPE)) {
			return Integer.parseInt(parameter);
		} else if (dataType.equals(Boolean.TYPE)) {
			return Boolean.parseBoolean(parameter);
		} else {
			System.out.println("Data type is a String or not recognized, returning a String for (" + parameter + ")");
			return parameter;
		}
	}

	private static ArrayList<String> openFileAndCollectData(String fileLocation, String fileName) {
		// Create a full relative file path
		String fullFilePath = fileLocation + fileName;
		// Array to hold lines from file
		ArrayList<String> dataLines = new ArrayList<String>();
		try {

			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fullFilePath);
			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			// Create String to hold current line
			String line = bufferedReader.readLine();
			// While there is a line to read or not null line
			// (also setting line to current line)
			while (line != null) {
				// Pass current line to the gatherDataString Method
				dataLines.add(line);
				line = bufferedReader.readLine();
			}
			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fullFilePath + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fullFilePath + "'");
			// Alternative we could just do this:
			// ex.printStackTrace();
		}
		return dataLines;
	}

	/**
	 * @param lines
	 * @return
	 */
	private static Object[][] parseCSVData(ArrayList<String> lines, boolean hasLabels, Object[] dataTypes) {

		ArrayList<Object> results = new ArrayList<Object>();
		// Check for labels on first line
		if (hasLabels) {
			// Remove any labels present
			lines.remove(0);
		}

		// String to be scanned to find the pattern.
		String pattern = "(,*)([a-zA-Z0-9\\s]+)(,*)";

		// Create a Pattern object
		Pattern r = Pattern.compile(pattern);

		for (int i = 0; i < lines.size(); i++) {
			int curDataType = 0;
			ArrayList<Object> curMatches = new ArrayList<Object>();
			Matcher m = r.matcher(lines.get(i));
			while (m.find()) {
				if (dataTypes.length > 0) {
					try {
						curMatches.add(convertDataType(m.group(2), dataTypes[curDataType]));
					} catch (Exception e) {
						System.out.println("DataTypes provided do not match parsed data results.");
					}
				} else {
					curMatches.add(m.group(2));
				}
				curDataType++;
			}
			Object[] resultsObj = new Object[curMatches.size()];
			curMatches.toArray(resultsObj);
			results.add(resultsObj);
		}
		System.out.println("Results:" + results);
		Object[][] resultsObj = new Object[results.size()][];
		results.toArray(resultsObj);
		return resultsObj;
	}

	/**
	 * @param lines
	 * @param hasLabels
	 * @return
	 */
	private static Object[][] parseJSONData(ArrayList<String> lines, Boolean hasLabels) {
		// TODO Auto-generated method stub
		return null;
	}
	// TODO Check for labels on first line
	// Object[][] data=
	// getTextileData{"src/main/resources/","data.csv",TextFormat.csv}

	/**
	 * @param lines
	 * @param hasLabels
	 * @return
	 */
	private static Object[][] parseTabData(ArrayList<String> lines, Boolean hasLabels) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param lines
	 * @param hasLabels
	 * @return
	 */
	private static Object[][] parseXMLData(ArrayList<String> lines, Boolean hasLabels) {
		// TODO Auto-generated method stub
		return null;
	}

}
