package OptimalSolutions.md;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.opencsv.CSVWriter;

public class App {
	private static final String CSV_FILE_NAME = "Inter.csv";
	private static final String CSV_BAD_DATA_FILE = "bad-data";
	// to-do: <timestamp>
	private static int countingBadData = 0;
	private static int countingGoodData = 0;

	public static void main(String[] args) {

		List<InterviewFileFeeder> records = readFromCSV(CSV_FILE_NAME);

		System.out.println(countingBadData + countingGoodData + " records received");
		System.out.println(countingGoodData + " of records successful");
		System.out.println(countingBadData + " of records failed");

		// print all the fields read from CSV file
		/*
		 * for (InterviewFileFeeder record : records) { System.out.println(record); }
		 */

		/*
		 * //test FileWriter csvWriter = new FileWriter(CSV_BAD_DATA_FILE); for
		 * (InterviewFileFeeder record : records) { csvWriter.append(record); }
		 */

	}

	private static List<InterviewFileFeeder> readFromCSV(String fileName) {
		List<InterviewFileFeeder> records = new ArrayList<InterviewFileFeeder>();
		Path pathToFile = Paths.get(fileName);

		// create an instance of BufferedReader
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
			// read the first line from the text file
			String line = br.readLine();

			// loop until all lines are read
			while (line != null) {
				// use string.split to load a string array with the values from each line of the
				// file, using regex as the delimiter

				String[] attributes = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");// Elements with commas will be
																						// double quoted
				// maybe the problem could be in the split regex code that when it gets to the
				// end it does not take the last elem in account

				/*
				 * //in case of debugging for(int i = 0; i < attributes.length; i++) {
				 * System.out.println(attributes[i]); }
				 */

				InterviewFileFeeder record = createRecord(attributes);

				// adding record into ArrayList
				// System.out.println(field);
				records.add(record);

				// read next line before looping
				// if end of file reached, line would be null
				line = br.readLine();

			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return records;
	}

	private static InterviewFileFeeder createRecord(String[] metadata) throws IOException {

		if (metadata.length < 10) {
			// System.out.println("record has only " + metadata.length);

			// to do: Records that do not match the column count must be written to the
			// bad-data-<timestamp>.csv file
			// Records that are empty do not match the column count must be written to the
			// bad-data-<timestamp>.csv file
			// record has only 1 is the last record which is blank

			// writeToCSV(CSV_BAD_DATA_FILE, readingRecord(metadata));
			countingBadData++;
			return null;
			// return readingRecord(metadata);
		} else {
			// testing purposes
			if (readingRecord(metadata).getColumnA().isEmpty() || readingRecord(metadata).getColumnB().isEmpty()
					|| readingRecord(metadata).getColumnC().isEmpty() || readingRecord(metadata).getColumnD().isEmpty()
					|| readingRecord(metadata).getColumnE().isEmpty() || readingRecord(metadata).getColumnF().isEmpty()
					|| readingRecord(metadata).getColumnG().isEmpty() || readingRecord(metadata).getColumnH().isEmpty()
					|| readingRecord(metadata).getColumnI().isEmpty()
					|| readingRecord(metadata).getColumnJ().isEmpty()) {

				writeToCSV(CSV_BAD_DATA_FILE, readingRecord(metadata));
				countingBadData++;
				return readingRecord(metadata);

			}

			// some records are taking in account all the "" which are not assigned
			// return readingRecord(metadata); //for reading the successful data need to
			// return this line

			countingGoodData++;
			return null;
		}

	}

	private static InterviewFileFeeder readingRecord(String[] metadata) {

		if (!(metadata.length < 10)) {

			// Each record is verified to contain the right number of data elements to match
			// the columns.
			String columnA = metadata[0];
			String columnB = metadata[1];
			String columnC = metadata[2];
			String columnD = metadata[3];
			String columnE = metadata[4];
			String columnF = metadata[5];
			String columnG = metadata[6];
			String columnH = metadata[7];
			String columnI = metadata[8];
			String columnJ = metadata[9];
			// System.out.println("columnJ " + columnJ);
			// System.out.println(metadata.length);
			// create and return InterviewFileFeeder of this metadata
			return new InterviewFileFeeder(columnA, columnB, columnC, columnD, columnE, columnF, columnG, columnH,
					columnI, columnJ);
		} else {
			System.out.println("index out of bounds");
			return null;
		}

	}

	private static void writeToCSV(String fileName, InterviewFileFeeder metadata) throws IOException {

		// System.out.println(metadata.getColumnA());

		String columnA = metadata.getColumnA();
		String columnB = metadata.getColumnB();
		String columnC = metadata.getColumnC();
		String columnD = metadata.getColumnD();
		String columnE = metadata.getColumnE();
		String columnF = metadata.getColumnF();
		String columnG = metadata.getColumnG();
		String columnH = metadata.getColumnH();
		String columnI = metadata.getColumnI();
		String columnJ = metadata.getColumnJ();

		/*
		 * List<String> row =
		 * Arrays.asList(columnA,columnB,columnC,columnD,columnE,columnF,columnG,columnH
		 * ,columnI,columnJ);
		 * 
		 * FileWriter csvWriter = new FileWriter(fileName); csvWriter.append("A");
		 * csvWriter.append(","); csvWriter.append("B"); csvWriter.append(",");
		 * csvWriter.append("C"); csvWriter.append(","); csvWriter.append("D");
		 * csvWriter.append(","); csvWriter.append("E"); csvWriter.append(",");
		 * csvWriter.append("F"); csvWriter.append(","); csvWriter.append("G");
		 * csvWriter.append(","); csvWriter.append("H"); csvWriter.append(",");
		 * csvWriter.append("I"); csvWriter.append(","); csvWriter.append("J");
		 * csvWriter.append("\n");
		 * 
		 * csvWriter.append(String.join(",", row)); csvWriter.append("\n");
		 * 
		 * 
		 * 
		 * csvWriter.flush(); csvWriter.close();
		 */

		CSVWriter writer = new CSVWriter(new FileWriter(timeStampSetter(CSV_BAD_DATA_FILE)));
		// Writing data to a csv file
		String line1[] = { columnA, columnB, columnC, columnD, columnE, columnF, columnG, columnH, columnI, columnJ };
		// Writing data to the csv file
		writer.writeNext(line1);
		// Flushing data from writer to file
		writer.flush();
		// System.out.println("Data entered");

	}
	
	
	private static String timeStampSetter(String stringToAddTime) {
		Date now = new java.util.Date();
		Timestamp current = new java.sql.Timestamp(now.getTime());

		stringToAddTime.concat(current.toString());
		stringToAddTime.concat(".csv");
		return stringToAddTime;
	}

}
