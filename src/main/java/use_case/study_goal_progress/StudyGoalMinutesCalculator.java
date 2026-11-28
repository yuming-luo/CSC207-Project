package use_case.study_goal_progress;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import entity.GoalType;
import use_case.view_study_history.StudyHistoryEntry;
import use_case.view_study_history.StudySessionDataAccessInterface;

/**
 * Calculates total study minutes for daily and weekly study goals.
 */
public class StudyGoalMinutesCalculator {

    private final StudySessionDataAccessInterface dataAccess;

    /**
     * Creates a study goal minutes calculator.
     *
     * @param dataAccess the study session data access interface
     */
    public StudyGoalMinutesCalculator(
            StudySessionDataAccessInterface dataAccess) {
        if (dataAccess == null) {
            throw new IllegalArgumentException(
                    "Data access cannot be null");
        }
        this.dataAccess = dataAccess;
    }

    /**
     * Calculates the total study minutes for a goal period.
     *
     * @param goalType the DAILY or WEEKLY goal type
     * @param referenceDate a date within the goal period
     * @return the total study minutes for the period
     */
    public int getCompletedMinutes(GoalType goalType,
                                   LocalDate referenceDate) {
        if (goalType == null || referenceDate == null) {
            throw new IllegalArgumentException(
                    "Goal type and date cannot be null");
        }

        LocalDate startDate = referenceDate;
        LocalDate endDate = referenceDate;

        if (goalType == GoalType.WEEKLY) {
            startDate = referenceDate.with(
                    TemporalAdjusters.previousOrSame(
                            DayOfWeek.MONDAY));

            endDate = referenceDate.with(
                    TemporalAdjusters.nextOrSame(
                            DayOfWeek.SUNDAY));
        }

        int totalMinutes = 0;

        for (StudyHistoryEntry entry : dataAccess.getCompletedSessions()) {
            LocalDate sessionDate = entry.getStartTime().toLocalDate();

            if (!sessionDate.isBefore(startDate)
                    && !sessionDate.isAfter(endDate)) {
                totalMinutes += entry.getDurationMinutes();
            }
        }

        return totalMinutes;
    }
}
