# ProjectOptimalSolutions
 
Instructions to run the code:

1. Attaching the JDBC driver to your IDE: You can find the JDBC driver in the sqlLite folde.
I use Eclipse IDE so for me is as follows (PROJECT -> PROPERTIES -> JAVA BUILD PATH -> LIBRARIES -> ADD EXTERNAL JARs... -> choose the JDBC)

2. Now for the purpose of accessing the DataBase.
In the folder you need to open the sqlite3.exe.
A console will open, then you type ".open customers.db", hold on that console for now.

3. Now i prefer to open the Eclipse IDE, then here you need to open the Project: (File -> Open Projects From File System...)

4. Press on Run the App.java

The app will work around the readFromCsv() method.
First it will read from the provided Interview-task-data-osh.csv with BufferedReader then it will iterate through every line that csv have then this line gets splitted in elements
in order to assign them to the customer class.
Second also in that method you will find the createRecord() method which takes those elements and cheks them if they correspond to the length of a line whick is 10 alsoa counter will trigger else an if statement will double check if those elements are empty, if yes then will trigger the counter of bad data and the method for cachingData() the bad data
will work and finaly if those elements are not empty then it will return customerRecord() and also a counter of good data will trigger.

customerRecord() is taking elements from the splitted line and then it returns a new customer with all of the good data.
cachingData() is adding to a String List all the elements so then we can take them in main and write to the BAD-DATA.csv file

writeToCSV() is basically taking the cached data and with open source libraries CSVWriter i indicate to take in account the quotes for which to omit the default delimiter comma

timeStampSetter() is taking a string as an argument which will get modified with a timeStamp

recordLogs() will create a Log.txt file and with the triggered data which were calculated before it will be printed in that file.

Going for the DBConnection class i wrote some basic functions to open and close the connection.
I use PreparedStatements which are easier to use to inser data in the customer.db

After Run in the main folder the Log.txt will be created and the Bad-data with timestamps will appear and also the SQLite, remember that terminal you've opened?
Now it's time to check that by querying the customers table
select * from customers; to see all the good data!!!
