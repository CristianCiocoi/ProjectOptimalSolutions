package OptimalSolutions.md;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App 
{
    private static final String CSV_FILE_NAME = "Inter.csv";

	public static void main( String[] args )
    {
    	
        List<InterviewFileFeeder> records = readFromCSV(CSV_FILE_NAME);
        
        // print all the fields read from CSV file
    	
        for (InterviewFileFeeder record : records) {
            System.out.println(record);
        }
        
    	
    	
        
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
                // use string.split to load a string array with the values from each line of the file, using regex as the delimiter
                
            	String[] attributes = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");//Elements with commas will be double quoted
               
                InterviewFileFeeder record = createRecord(attributes);
                // adding record into ArrayList
                //System.out.println(field);
                records.add(record);
                // read next line before looping
                // if end of file reached, line would be null
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return records;
    }

    private static InterviewFileFeeder createRecord(String[] metadata) {
    	
    	if(metadata.length < 10) {
    		//System.out.println("record has only " + metadata.length);
    		//to do: Records that do not match the column count must be written to the bad-data-<timestamp>.csv file 
    		
    		//readingRecord(metadata).setColumnJ(null); //setting the ColumnJ to smth so it should be calculated as a length of 10
    		return null;
    		//return readingRecord(metadata);
    	}else {

    		if(readingRecord(metadata).getColumnA().isEmpty() || readingRecord(metadata).getColumnB().isEmpty() || readingRecord(metadata).getColumnC().isEmpty() 
    				|| readingRecord(metadata).getColumnD().isEmpty() || readingRecord(metadata).getColumnE().isEmpty() || readingRecord(metadata).getColumnF().isEmpty()
    				|| readingRecord(metadata).getColumnG().isEmpty()|| readingRecord(metadata).getColumnH().isEmpty()|| readingRecord(metadata).getColumnI().isEmpty()
    				|| readingRecord(metadata).getColumnJ().isEmpty()) {
    			
    			
    			//Records that are empty  do not match the column count must be written to the bad-data-<timestamp>.csv file 
    			return null;
    		}
    		//System.out.println("record has only " + metadata.length);
    		//some records are taking in account all the "" which are not assigned
    		return readingRecord(metadata);
    	}
    	    	
    }

	private static InterviewFileFeeder readingRecord(String[] metadata) {
		//Each record is verified to contain the right number of data elements to match the columns.
		String columnA = metadata[0];
		String columnB = metadata[1];
		String columnC = metadata[2];
		String columnD = metadata[3];
		String columnE = metadata[4];
		String columnF = metadata[5];
		String columnG = metadata[6];
		String columnH = metadata[7];
		String icolumnI = metadata[8];
		String columnJ = metadata[9];
		//System.out.println("columnJ " + columnJ);
		//System.out.println(metadata.length);
		// create and return InterviewFileFeeder of this metadata
		return new InterviewFileFeeder(columnA, columnB, columnC, columnD, columnE, columnF, columnG, columnH, icolumnI, columnJ);
	}
    
    
    private String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }
    
    //creating a method for formatting a single line of data
    public String convertToCSV(String[] data) {
        return Stream.of(data)
          .map(this::escapeSpecialCharacters)
          .collect(Collectors.joining(","));
    }
    /*
	public void givenDataArray_whenConvertToCSV_thenOutputCreated() throws IOException {
	    File csvOutputFile = new File(CSV_FILE_NAME);
	    try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
	        dataLines.stream()
	          .map(this::convertToCSV)
	          .forEach(pw::println);
	    }
	    assertTrue(csvOutputFile.exists());
	}
    */

}


