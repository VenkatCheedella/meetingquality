package databuilder.meetingquality;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import databuilder.meetingquality.common.ConfigHolder;

public class MeetingAttributesBuilder {

    public static MeetingAttributes getMeeting() {
        int randomNumber = ThreadLocalRandom.current().nextInt(0, ConfigHolder.MAXIMUM_DAYS_DELAY);

        Date scheduledMeetingStartTime = new Date(
                System.currentTimeMillis() - TimeUnit.MILLISECONDS.convert(randomNumber, TimeUnit.DAYS));
        Date scheduledMeetingEndTime = new Date(scheduledMeetingStartTime.getTime()
                + ThreadLocalRandom.current().nextInt(ConfigHolder.MIN_LENGTH_OF_MEETING, ConfigHolder.MAX_LENGTH_OF_MEETING)
                        * 1000L);
        Date actualMeetingStartTime = new Date(scheduledMeetingStartTime.getTime() + ThreadLocalRandom.current()
                .nextInt(ConfigHolder.MIN_DELTA_DELAY_MEETING_START, ConfigHolder.MAX_DELTA_DELAY_MEETING_START) * 1000L);
        Date actualMeetingEndTime = new Date(scheduledMeetingEndTime.getTime() - ThreadLocalRandom.current()
                .nextInt(ConfigHolder.MIN_DELTA_DELAY_MEETING_END, ConfigHolder.MAX_DELTA_DELAY_MEETING_END) * 1000L);

        int invitedCount = ThreadLocalRandom.current().nextInt(ConfigHolder.MIN_PARTICIPANT_COUNT,
                ConfigHolder.MAX_PARTICIPANT_COUNT);
        int acceptedCount = ThreadLocalRandom.current().nextInt(0, invitedCount);
        int tentativeCount = ThreadLocalRandom.current().nextInt(0, invitedCount - acceptedCount);
        int declinedCount = ThreadLocalRandom.current().nextInt(0, invitedCount - acceptedCount - tentativeCount);
        int noResponseCount = ThreadLocalRandom.current().nextInt(0,
                invitedCount - acceptedCount - tentativeCount - declinedCount);
        acceptedCount = acceptedCount + (invitedCount - (tentativeCount + noResponseCount + acceptedCount));
        int lowerLimit = (int) (0.85 * acceptedCount + 0.25 * tentativeCount + 0.20 * noResponseCount);
        int upperLimit = (int) (0.95 * acceptedCount + 0.75 * tentativeCount + 0.25 * noResponseCount);
        lowerLimit = (lowerLimit < upperLimit) ? lowerLimit : upperLimit-1;
        int participantCount = ThreadLocalRandom.current().nextInt(lowerLimit,upperLimit);
        int activeParticipantCount = MockingUtility.getNumberOfActiveParticipants(participantCount);
//        System.out.println("Participant COunt " + participantCount + " , activeParticipant Count " + activeParticipantCount);
        double interActiveFactor =  (((double)activeParticipantCount/(double)participantCount) * 100)/10;
        
        // need to structure interactiveFactor
        return MeetingAttributes.builder().meetingID(UUID.randomUUID().toString())
                .scheduledStartTime(scheduledMeetingStartTime).scheduledEndTime(scheduledMeetingEndTime)
                .actualStartTime(actualMeetingStartTime).actualEndTime(actualMeetingEndTime)
                .meetingEndReason(MeetingEndReason.randomMeetingEndReason().toString())
                .invitedParticipantCount(invitedCount)
                .participantsCount(participantCount)
                .acceptedCount(acceptedCount)
                .tentativeCount(tentativeCount)
                .outlookNoResponseCount(noResponseCount)
                .interactiveFactor((int)interActiveFactor)
                .activeSpeakerParticipantCount(activeParticipantCount).build();
    }

}
