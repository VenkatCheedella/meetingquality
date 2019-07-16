package databuilder.meetingquality;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import databuilder.meetingquality.common.ConfigHolder;

public class MockingUtility {

    public static List<MeetingActiveStatus> getMeetingActiveStatusList(List<String> participants, Date meetingStartTime,
                                                                       Date meetingEndTime, String meetingId) {
        int numberOfActiveParticipants = getNumberOfActiveParticipants(participants.size());

        return null;
    }

    public static int getNumberOfActiveParticipants(int participantCount) {
        double value = ThreadLocalRandom.current().nextInt(ConfigHolder.MINIMUM_ACTIVE_PARTICIPANT_PERCENT,
                ConfigHolder.MAXIMUM_ACTIVE_PARTICIPANT_PERCENT);
        double mockPercent = (double)( value/ 100);
        int activeSpokeParticipantCount = (int) (participantCount * mockPercent)+ 1;
//        System.out.println(mockPercent);
        return activeSpokeParticipantCount;
    }

}
