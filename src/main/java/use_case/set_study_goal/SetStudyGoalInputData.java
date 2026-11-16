package use_case.set_study_goal;

import entity.GoalType;

/**
 * Input data for setting a study goal.
 */
public class SetStudyGoalInputData {

    private final GoalType goalType;
    private final int targetMinutes;

    public SetStudyGoalInputData(GoalType goalType, int targetMinutes) {
        this.goalType = goalType;
        this.targetMinutes = targetMinutes;
    }

    public GoalType getGoalType() {
        return goalType;
    }

    public int getTargetMinutes() {
        return targetMinutes;
    }
}