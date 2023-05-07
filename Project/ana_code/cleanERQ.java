import java.io.IOException;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class cleanERQ {
    public static void main(String args[]) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: Clean <input path> <output path>");
            System.exit(-1);
        }

        Job job = new Job();
        job.addFileToClassPath(new Path("opencsv-5.7.1.jar"));
        job.setNumReduceTasks(1);
        job.setJarByClass(cleanERQ.class);
        job.setJobName("cleanERQ");
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(cleanERQMapper.class);
        job.setReducerClass(cleanERQReducer.class);
        job.setOutputKeyClass(LongWritable.class);
        job.setOutputValueClass(Text.class);

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}