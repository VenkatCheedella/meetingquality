package databuilder.meetingquality.common;

public class ConfigHolder {
    
    public static final int MAX_PARTICIPANT_COUNT = 10;
    public static final int MIN_PARTICIPANT_COUNT = 2;
    
    public static final int MIN_LENGTH_OF_MEETING = 1500;   // in sec
    public static final int MAX_LENGTH_OF_MEETING = 10800; // in sec
    
    public static final int MIN_DELTA_DELAY_MEETING_START = 0; // in sec
    public static final int MAX_DELTA_DELAY_MEETING_START = 600; // in sec;
    public static final int MIN_DELTA_DELAY_MEETING_END = 0;      // in sec
    public static final int MAX_DELTA_DELAY_MEETING_END = 600;     // in sec
    
    public static final int PARTICIPANT_MIN_DELTA_DELAY_MEETING_START = 30; // in sec
    public static final int PARTICIPANT_MAX_DELTA_DELAY_MEETING_START = 600; // in sec;
    public static final int PARTICIPANT_MIN_DELTA_DELAY_MEETING_END = 30;      // in sec
    public static final int PARTICIPANT_MAX_DELTA_DELAY_MEETING_END = 600;     // in sec
    
    public static final int MINIMUM_ACTIVE_PARTICIPANT_TIME = 10;  //sec
    public static final int MAXIMUM_ACTIVE_PARTICIPANT_TIME = 3600; // sec
    
    public static final int MINIMUM_ACTIVE_PARTICIPANT_PERCENT = 1;  // percentage
    public static final int MAXIMUM_ACTIVE_PARTICIPANT_PERCENT = 100; // percentage
    
    public static final int MAXIMUM_DAYS_DELAY = 30;
            
}
