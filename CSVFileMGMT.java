import java.util.*;
import java.io.*;
// these two are only for dir2 var in filePath()
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CSVFileMGMT {

    private String fileName = "TimerStates.csv";
    private String filePath;
    private Calendar weekEnd;
    private Calendar[] timers;

    public CSVFileMGMT() {

    }

    public void csvStateLoader(Calendar we, Calendar[] timerArr){
        this.weekEnd = we;
        this.timers = timerArr;
    }

    public void readCSV() throws IOException, ParseException {

        File f = new File(this.filePath);

        if (f.isFile()){

            BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
            String row;
            int rowIndex = 0;
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyy", Locale.ENGLISH);

            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");

                if (rowIndex == 0){
                    // load in week delimeter
                    this.weekEnd = Calendar.getInstance();
                    try{
                        this.weekEnd.setTime(sdf.parse(data[0]));
                    } catch(ParseException e){
                        System.out.println("We've got a parse Exception");
                        System.exit(0);
                    }
                }else if (rowIndex == 1){
                    // load in the timers
                    System.out.println("loading Timers.");
                    for (int i = 0; i < data.length; i++){
                        this.timers[i] = Calendar.getInstance();
                        this.timers[i].setTime(sdf.parse(data[i]));
                    }
                }else{
                    System.out.println("Too many lines in the csv file");
                }
            }
            csvReader.close();
        } else {
            // there was no source file for timer data. Initialize all the timers to 0

        }
    }

    public void writeCSV() throws IOException {

        FileWriter csvWriter = new FileWriter(this.fileName);
        // enter week delimeter
        csvWriter.append(this.weekEnd.toString());
        csvWriter.append("\n");
        // enter timer states
        for(int i = 0; i <= timers.length; i++){
            csvWriter.append(timers[i].toString());
            csvWriter.append(",");
        }

        csvWriter.flush();
        csvWriter.close();
    }

    private void filePath(){
        Path currentRelPath = Paths.get(this.fileName);
        filePath = currentRelPath.toAbsolutePath().toString();
        System.out.println(filePath);

    }

    public static void main(String[] args) {

        CSVFileMGMT newManager = new CSVFileMGMT();


        // test 1
        newManager.filePath();
    }
}
