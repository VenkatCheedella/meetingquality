package databuilder.meetingquality;

import java.util.Date;

import lombok.Data;

@Data
public class Participant {
    private String meetingId;
    private String personId;
    private String name;
    private String outlookStatus;
    private String joinStatus;
    private Date startTime;
    private Date endTime;
}
