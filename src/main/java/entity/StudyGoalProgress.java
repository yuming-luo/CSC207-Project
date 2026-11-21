package entity;

/**
 * Represents a student's progress towards their study goal
 */
public class StudyGoalProgress {

    private final StudyGoal goal;
    private final int completedMinutes;

    public StudyGoalProgress(StudyGoal goal, int completedMinutes) {
        if (goal == null) {
            throw new IllegalArgumentException("Study goal can't be null");
        }

        if (completedMinutes < 0) {
            throw new IllegalArgumentException("Completed minutes can't be negative");
        }

        this.goal = goal;
        this.completedMinutes = completedMinutes;
    }

    /**
     * Returns the progress of the study goal in percentage.
     *
     * @return study goal progress percentage
     */
    public double getProgressPercentage() {
        return (double) completedMinutes / goal.getTargetMinutes() * 100;
    }

    /**
     * Checks whether the study goal is met.
     *
     * @return true if goal met, and false if goal isn't met
     */
    public boolean isGoalMet() {
        return completedMinutes >= goal.getTargetMinutes();
    }
}