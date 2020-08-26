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
	private static final String CSV_FILE_NAME = "Interview-task-data-osh.csv";
	private static final String CSV_BAD_DATA_FILE = "bad-data-";
	private static final String LOG_FILE = "Logs.txt";
	private static int countingBadData = 0;
	private static int countingGoodData = 0;

	static List<String[]> data = new ArrayList<String[]>();
	static FileWriter badDataFile;

	public static void main(String[] args) throws IOException {

		long startTime = System.nanoTime();

        
		List<Customer> records = readFromCSV(CSV_FILE_NAME);
		managingDB(records);

		badDataFile = new FileWriter(timeStampSetter(CSV_BAD_DATA_FILE));
		writeToCSV(badDataFile);

		recordLogs(LOG_FILE, countingGoodData, countingBadData);
		long estimatedTime = System.nanoTime() - startTime;
		System.out.println("Task took " + estimatedTime/ 10E8 + " seconds");
	}

	private static void managingDB(List<Customer> records) {
		DBConnection dbConnection = new DBConnection();
		if (dbConnection.openConnection()) {
			dbConnection.clearAll();
			for (Customer record : records) {
				if (record != null)
					dbConnection.insertCustomer(record);
			}
		} else {
			dbConnection.closeConnection();
		}
	}

	private static List<Customer> readFromCSV(String fileName) {
		List<Customer> customers = new ArrayList<Customer>();
		Path pathToFile = Paths.get(fileName);
		String[] attributes = new String[10];

		// create an instance of BufferedReader
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {

			// read the first line from the text file
			String headerLine = br.readLine();
			System.out.println("Headers: " + headerLine);
			String line = br.readLine();

			// loop until all lines are read
			while (line != null) {

				// use string.split to load a string array with the values from each line of the
				// file, using regex as the delimiter
				attributes = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);// Elements with commas will be
																					// double quoted
				Customer customer = createRecord(attributes);

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
			return null;
		} else {
			if (customerRecord(metadata).getColumnA().isEmpty() || customerRecord(metadata).getColumnB().isEmpty()
					|| customerRecord(metadata).getColumnC().isEmpty()
					|| customerRecord(metadata).getColumnD().isEmpty()
					|| customerRecord(metadata).getColumnE().isEmpty()
					|| customerRecord(metadata).getColumnF().isEmpty()
					|| customerRecord(metadata).getColumnG().isEmpty()
					|| customerRecord(metadata).getColumnH().isEmpty()
					|| customerRecord(metadata).getColumnI().isEmpty()
					|| customerRecord(metadata).getColumnJ().isEmpty()) {
				countingBadData++;
				cachingData(customerRecord(metadata));
				return null;
			}
			countingGoodData++;
			return customerRecord(metadata);
		}

	}

	private static Customer customerRecord(String[] metadata) {

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
			return new Customer(columnA, columnB, columnC, columnD, columnE, columnF, columnG, columnH, columnI,
					columnJ);
		} else {
			return new Customer();
		}

	}

	private static void cachingData(Customer metadata) throws IOException {

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

		String[] lines = new String[] { columnA, columnB, columnC, columnD, columnE, columnF, columnG, columnH, columnI,
				columnJ };

		data.add(lines);

	}

	private static void writeToCSV(FileWriter badDataFile) throws IOException {
		CSVWriter writer = new CSVWriter(badDataFile, ',', '"', '"', "\n");

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

	private static void recordLogs(String logFile, int goodDataCounter, int badDataCounter) {
		int recordsReceived = goodDataCounter + badDataCounter;
		try {
			FileWriter myWriter = new FileWriter(logFile);
			myWriter.write(recordsReceived + " records received\n");
			myWriter.write(goodDataCounter + " records successful\n");
			myWriter.write(badDataCounter + " records failed");
			myWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
