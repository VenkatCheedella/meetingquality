package databuilder.meetingquality;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;


public class JavaBeanToCSV<T extends Object> {
    
    public static final String PATH_TO_MEETING_METRICS= "/Users/venkatcheedella/eclipse-workspace/meetingquality";
    public static final String MEETING_METRICS = "MeetingMetrics.csv";
    
    public void toCSV(List<T> javaBeansList, String[] columns) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        FileWriter writer = null;
        try {
            writer = new FileWriter(new File(PATH_TO_MEETING_METRICS + MEETING_METRICS));
            ColumnPositionMappingStrategy<T> columnPositionMappingStrategy = new ColumnPositionMappingStrategy<T>(); 
            columnPositionMappingStrategy.setColumnMapping(columns);
            StatefulBeanToCsvBuilder<T> builder = new StatefulBeanToCsvBuilder<T>(writer);
            StatefulBeanToCsv<T> beanWriter = builder.withMappingStrategy(columnPositionMappingStrategy).build();
            beanWriter.write(javaBeansList);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
