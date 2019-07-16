package databuilder.meetingquality;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Builder
@Setter
public class MeetingAttributes {
    private String meetingID;
    private String meetingHost;
    private Date scheduledStartTime;
    private Date scheduledEndTime;
    private Date actualStartTime;
    private Date actualEndTime;
    private String meetingEndReason;
    private int activeSpeakerParticipantCount;
    private int invitedParticipantCount;
    private int participantsCount;
    private int acceptedCount;
    private int declinedCount;
    private int tentativeCount;
    private int outlookNoResponseCount;
    private int interactiveFactor;
    private int earlyMeetingEndFactor;

    private static final int ACCEPTED_FACTOR_CF = 5;
    private static final int DECLINED_FACTOR_CF = 5;
    private static final int TENTATIVE_FACTOR_CF = 5;
    private static final int NO_RESPONSE_FACTOR_CF = 1;
    private static final int PARTICIPANT_FACTOR_CF = 10;
    private static final int PARTICIPIANT_ACCEPTED_FACTOR_CF = 7;
    private static final int INTERACTIVE_FACTOR_CF = 9;
    private static final int LATE_JOINERS_FACTOR_CF = 2;
    private static final int EARLY_MEETING_END_FACTOR_CF = 4;
    private static final int EARLY_GOERS_FACTOR_CF = 4;
    private static final int MEETING_END_FACTOR_CF = 5;
    
    private static final int NORMALIZING_FACTOR = 320;

    private double rank = 0;

    @Override
    public String toString() {
        this.rank = ((double) (rankMeeting() / (double) NORMALIZING_FACTOR) * 5);
        return meetingID + "," + scheduledStartTime.getTime() + "," + scheduledEndTime.getTime() + "," + actualStartTime.getTime()
                + "," + actualEndTime.getTime() + "," + MeetingEndReason.getValue(this.meetingEndReason) + ","
                + activeSpeakerParticipantCount + "," + invitedParticipantCount + "," + participantsCount + "," + acceptedCount
                + "," + declinedCount + "," + tentativeCount + "," + outlookNoResponseCount + "," + interactiveFactor + ","
                + new DecimalFormat("#.#").format(this.rank);
    }

    public int rankMeeting() {

        int acceptedFactor = (int) (((double) acceptedCount / (double) invitedParticipantCount) * 100) / 10;

        int declinedFactor = (int) (((double) declinedCount / (double) invitedParticipantCount) * 100) / 10;
        int tentativeFactor = (int) (((double) tentativeCount / (double) invitedParticipantCount) * 100) / 10;
        int noResponseFactor = (int) (((double) outlookNoResponseCount / (double) invitedParticipantCount) * 100) / 10;
        int participantFactor = (int) (((double) participantsCount / (double) invitedParticipantCount) * 100) / 10;
        int participantAcceptedFactor = (participantsCount < acceptedCount)
                ? ((int) (((double) participantsCount / (double) acceptedCount) * 100) / 10)
                : 0;
        int randomValue = ThreadLocalRandom.current().nextInt(1, 5);
        int meetingEndFactor = (MeetingEndReason.HOST_END_MEETING.toString().equals(meetingEndReason) ? 10
                : (MeetingEndReason.SERVICE_ISSUE.toString().equals(meetingEndReason)) ? 0 : randomValue);

        int lateJoinersIdx = (int) ((double) participantsCount * 0.5);
        int lateJoiners = (lateJoinersIdx <= 0) ? 0 : ThreadLocalRandom.current().nextInt(0, lateJoinersIdx);
        int lateJoinersFactor = (int) (((double) lateJoiners / (double) participantsCount) * 100) / 10;

        int earlyGoersIdx = (int) ((double) participantsCount * 0.2);
        int earlyGoers = (earlyGoersIdx <= 0) ? 0 : ThreadLocalRandom.current().nextInt(0, earlyGoersIdx);
        int earlyGoersFactor = (int) (((double) earlyGoers / (double) participantsCount) * 100) / 10;

        int earlyMeetingEndFactor = scheduledEndTime.compareTo(actualEndTime) >= 0 ? 0 : 10;
        // System.out.println("MeetingId : " + meetingID);
        // System.out.println(acceptedFactor);
        // System.out.println(declinedFactor);
        // System.out.println(tentativeFactor);
        // System.out.println(noResponseFactor);
        // System.out.println(participantFactor);
        // System.out.println(participantAcceptedFactor);
        // System.out.println(lateJoinersFactor);
        // System.out.println(earlyGoersFactor);
        // System.out.println(earlyMeetingEndFactor);
        // System.out.println(meetingEndFactor);
        // System.out.println(interactiveFactor);

        return (acceptedFactor * ACCEPTED_FACTOR_CF) + (declinedFactor * DECLINED_FACTOR_CF)
                + (tentativeFactor * TENTATIVE_FACTOR_CF) + (noResponseFactor * NO_RESPONSE_FACTOR_CF)
                + (participantFactor * PARTICIPANT_FACTOR_CF) + (participantAcceptedFactor * PARTICIPIANT_ACCEPTED_FACTOR_CF)
                + (interactiveFactor * INTERACTIVE_FACTOR_CF) + (meetingEndFactor * MEETING_END_FACTOR_CF)
                + (lateJoinersFactor * LATE_JOINERS_FACTOR_CF) + (earlyGoersFactor * EARLY_GOERS_FACTOR_CF)
                + (earlyMeetingEndFactor * EARLY_MEETING_END_FACTOR_CF);

    }
}
