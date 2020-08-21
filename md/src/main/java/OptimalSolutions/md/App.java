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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opencsv.CSVWriter;

public class App {
	private static final String CSV_FILE_NAME = "Inter.csv";
	private static final String CSV_BAD_DATA_FILE = "bad-data-";
	private static int countingBadData = 0;
	private static int countingGoodData = 0;

	
	static List<String[]> data = new ArrayList<String[]>();
	static FileWriter outputfile;
	
	
	public static void main(String[] args) throws IOException {
		List<InterviewFileFeeder> records = readFromCSV(CSV_FILE_NAME);
		outputfile = new FileWriter(timeStampSetter(CSV_BAD_DATA_FILE));
		writeHelper(outputfile);
		
	
		
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
			// writeToCSV(CSV_BAD_DATA_FILE, readingRecord(attributes));//try to read the
			// file directly in the write to csv method
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
			//countingBadData++;
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

				// System.out.println(readingRecord(metadata));
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
			// create and return InterviewFileFeeder of this metadata
			return new InterviewFileFeeder(columnA, columnB, columnC, columnD, columnE, columnF, columnG, columnH,
					columnI, columnJ);
		} else {
			System.out.println("index out of bounds");
			return null;
		}

	}

	private static void writeToCSV(String fileName, InterviewFileFeeder metadata) throws IOException {

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
			
			// Writing data to a csv file
			String[] lines = new String[] { columnA, columnB, columnC, columnD, columnE, columnF, columnG, columnH,	columnI, columnJ };

			data.add(lines);
		
	}

	
	private static void writeHelper(FileWriter outputfile) throws IOException {
		CSVWriter writer = new CSVWriter(outputfile, ',', CSVWriter.NO_QUOTE_CHARACTER,
				CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

		writer.writeAll(data);

		writer.close();
	}
	
	private static String timeStampSetter(String stringToAddTime) {
		Date now = new java.util.Date();
		Timestamp current = new java.sql.Timestamp(now.getTime());
		String timeStamp = new SimpleDateFormat("HH.mm.ss").format(current);
		stringToAddTime = stringToAddTime + timeStamp + ".csv";
		return stringToAddTime;
	}
}
