package databuilder.meetingquality;

import java.util.Date;

import lombok.Data;

@Data
public class MeetingActiveStatus {
    private String participantId;
    private Date startTime;
    private Date endTime;
}
