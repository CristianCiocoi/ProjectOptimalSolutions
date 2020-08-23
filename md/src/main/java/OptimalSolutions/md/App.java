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
		readFromCSV(CSV_FILE_NAME);
		outputfile = new FileWriter(timeStampSetter(CSV_BAD_DATA_FILE));
		writeHelper(outputfile);
		
		System.out.println(countingBadData + countingGoodData + " records received");
		System.out.println(countingGoodData + " of records successful");
		System.out.println(countingBadData + " of records failed");
	}


	private static List<Customer> readFromCSV(String fileName) {
		List<Customer> customers = new ArrayList<Customer>();
		Path pathToFile = Paths.get(fileName);
		String[] attributes = new String[10];
		
		// create an instance of BufferedReader
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
			// read the first line from the text file
			String line = br.readLine();

			// loop until all lines are read
			while (line != null) {
				// use string.split to load a string array with the values from each line of the
				// file, using regex as the delimiter
								
				attributes = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);// Elements with commas will be
																					// double quoted


				
				
				Customer customer = createRecord(attributes);
				
				//System.out.println(customer);//the records with commas are double quoted
				
				customers.add(customer);

				line = br.readLine();

			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return customers;
	}

	
	private static Customer createRecord(String[] metadata) throws IOException {

		if (metadata.length < 10) {
			countingBadData++;
			//writeToCSV(CSV_BAD_DATA_FILE, readingRecord(metadata));
			return readingRecord(metadata);
		} else {
			if (readingRecord(metadata).getColumnA().isEmpty() || readingRecord(metadata).getColumnB().isEmpty()
					|| readingRecord(metadata).getColumnC().isEmpty() || readingRecord(metadata).getColumnD().isEmpty()
					|| readingRecord(metadata).getColumnE().isEmpty() || readingRecord(metadata).getColumnF().isEmpty()
					|| readingRecord(metadata).getColumnG().isEmpty() || readingRecord(metadata).getColumnH().isEmpty()
					|| readingRecord(metadata).getColumnI().isEmpty()
					|| readingRecord(metadata).getColumnJ().isEmpty()) {
				countingBadData++;
				writeToCSV(CSV_BAD_DATA_FILE, readingRecord(metadata));
				return readingRecord(metadata);
			}
			countingGoodData++;
			return readingRecord(metadata);
		}

	}

	private static Customer readingRecord(String[] metadata) {

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
			return new Customer(columnA, columnB, columnC, columnD, columnE, columnF, columnG, columnH,
					columnI, columnJ);
		} else {
			System.out.println("index out of bounds " + metadata.length);
			return null;
		}

	}

	private static void writeToCSV(String fileName, Customer metadata) throws IOException {

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
		CSVWriter writer = new CSVWriter(outputfile, ',', '"', '"', "\n"); //here the delimeter puts the data incorrectly in the columns        //solution: use super csv and then the regex delimiter pattern ,(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)

		
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
