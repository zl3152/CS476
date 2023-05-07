import java.io.FileReader;
import java.io.IOException;
import com.opencsv.CSVReader;
import java.io.StringReader;
import java.util.*;

import com.opencsv.exceptions.CsvMalformedLineException;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class cleanSDSMapper extends Mapper<LongWritable, Text, LongWritable, Text> {

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        try (CSVReader reader = new CSVReader(new StringReader(value.toString()))) {
            if (key.get() != 0){
                String[] readin= reader.readNext();
                String[] row = Arrays.copyOfRange(readin, 0, 25);
                boolean containsNull = false;
                if (row[3].equals("Unfinished")){
                    containsNull = true;
                }
                if (row != null && containsNull == false) {
                    context.write(key, value);
                }
            }
        } catch (CsvValidationException | CsvMalformedLineException e) {
            String errorMessage = e.getMessage();
            if (errorMessage.contains("unterminated quoted field")){
                System.err.println(e);
            }
        }
    }
}