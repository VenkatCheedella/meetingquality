package databuilder.meetingquality;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import junit.framework.TestCase;

public class TestMeetingAttributes extends TestCase {
    
    public static final String PATH_TO_MEETING_METRICS= "/Users/venkatcheedella/eclipse-workspace/meetingquality/";
    public static final String MEETING_METRICS = "MeetingMetrics.csv";

    public void createSampleTestMeetingAttributes() {
        MeetingAttributes meetingAttributes = MeetingAttributesBuilder.getMeeting();
        System.out.println(meetingAttributes);
    }

    public void generateRandomActiveParticipantCount() {
        System.out.println(MockingUtility.getNumberOfActiveParticipants(6));
    }

    public void createSampleTestMeetingAttributes_Multiple() {
        try {
            List<MeetingAttributes> meetingAttributesList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                MeetingAttributes meetingAttributes = MeetingAttributesBuilder.getMeeting();
                meetingAttributesList.add(meetingAttributes);
                System.out.println(meetingAttributes);
            }
            
            JavaBeanToCSV<MeetingAttributes> beanToCSV = new JavaBeanToCSV<MeetingAttributes>();
            String[] columns = new String[] { "meetingID", "meetingHost", "scheduledStartTime", "scheduledEndTime",
                    "actualStartTime", "actualEndTime", "meetingEndReason", "activeSpeakerParticipantCount",
                    "invitedParticipantCount", "participantsCount", "acceptedCount", "declinedCount", "tentativeCount",
                    "outlookNoResponseCount", "interactiveFactor" };

            beanToCSV.toCSV(meetingAttributesList, columns);
        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    
    public void create_Mulitple_MeetingAttributes() {
        
        BufferedWriter writer = null;
        double maxRank = Integer.MIN_VALUE;
        delete_File_Content();
        try {
            writer = new BufferedWriter(new FileWriter(PATH_TO_MEETING_METRICS + MEETING_METRICS, true));
            for (int i = 0; i < 12000; i++) {
                MeetingAttributes meetingAttributes = MeetingAttributesBuilder.getMeeting();
                if(meetingAttributes.getParticipantsCount() == 0) continue;
                writer.append("\n" + meetingAttributes);
                double currentRank = meetingAttributes.getRank();
                maxRank = (maxRank < currentRank) ? currentRank : maxRank; 
            }
            System.out.println("Max Rank : " + maxRank);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        for (int i = 0; i < 10000; i++) {
//            MeetingAttributes meetingAttributes = MeetingAttributesBuilder.getMeeting();
//            if(meetingAttributes.getParticipantsCount() == 0) continue;
//            
////            System.out.println(meetingAttributes);
//        }
    }
    
    private void delete_File_Content() {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(PATH_TO_MEETING_METRICS + MEETING_METRICS));
            writer.write("");
            writer.flush();
        }catch(IOException e) {
            e.getStackTrace();
        }finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
