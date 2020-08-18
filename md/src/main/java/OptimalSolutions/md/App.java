package OptimalSolutions.md;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
    	
        List<InterviewFileFeeder> fileds = readFromCSV("test.csv");
        // print all the fields read from CSV file

    	/*
        for (InterviewFileFeeder field : fileds) {
            System.out.println(field);
        }
        */
    	
    	
        
    }
    
    private static List<InterviewFileFeeder> readFromCSV(String fileName) {
        List<InterviewFileFeeder> fields = new ArrayList<InterviewFileFeeder>();
        Path pathToFile = Paths.get(fileName);

        // create an instance of BufferedReader
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            // read the first line from the text file
            String line = br.readLine();
            
            // loop until all lines are read
            while (line != null) {
                // use string.split to load a string array with the values from each line of the file, using regex as the delimiter
                String[] attributes = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
               
                InterviewFileFeeder field = createField(attributes);
                // adding field into ArrayList
                //System.out.println(field);
                fields.add(field);
                // read next line before looping
                // if end of file reached, line would be null
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return fields;
    }

    private static InterviewFileFeeder createField(String[] metadata) {

    	if(metadata.length < 10) {
    		System.out.println("record has only " + metadata.length);
    		//to do: Records that do not match the column count must be written to the bad-data-<timestamp>.csv file 
    		
    		return null;
    	}else {
        	String nameA = metadata[0];
            String surnameB = metadata[1];
            String mailC = metadata[2];
            String genderD = metadata[3];
            String profileImageE = metadata[4];
            String paymentMethodF = metadata[5];
            String transactionsG = metadata[6];
            boolean h = Boolean.parseBoolean(metadata[7]);
            boolean i = Boolean.parseBoolean(metadata[8]);        
            String cityJ = metadata[9];
        	//System.out.println("city " + cityJ);
            //System.out.println(metadata.length);
            // create and return InterviewFileFeeder of this metadata
            return new InterviewFileFeeder(nameA, surnameB, mailC, genderD, profileImageE, paymentMethodF, transactionsG, h, i, cityJ);
    	}
    	
    	//try to create the db and insert the fields
    	
    }

}
    

