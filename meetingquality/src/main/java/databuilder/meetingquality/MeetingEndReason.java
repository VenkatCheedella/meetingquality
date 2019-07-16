package databuilder.meetingquality;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public enum MeetingEndReason {
    
    HOST_END_MEETING, MAXIMUM_TIME_LIMIT_REACHED, NON_TECHNICAL_REASON, SERVICE_ISSUE, NETWORK_ISSUE;
    
    private static final List<MeetingEndReason> MEETING_END_REASONS = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = MEETING_END_REASONS.size();
    private static final Random RANDOM = new Random();
    
    private static final Map<String, Integer> MEETING_VALUES = new HashMap<String, Integer>();
    
    static {
        int value = 1;
        for(MeetingEndReason reason : MEETING_END_REASONS) {
            MEETING_VALUES.put(reason.toString(), value++);
        }
    }
    
    public static MeetingEndReason randomMeetingEndReason() {
        return MEETING_END_REASONS.get(RANDOM.nextInt(SIZE));
    }
    
    public static int getValue(String meetingEndReason) {
        return MEETING_VALUES.get(meetingEndReason);
    }
}
