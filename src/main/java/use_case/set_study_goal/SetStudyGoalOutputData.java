package use_case.set_study_goal;

import entity.GoalType;

/**
 * Output data for the Set Study Goal use case.
 */
public class SetStudyGoalOutputData {

    private final GoalType goalType;
    private final int targetMinutes;

    public SetStudyGoalOutputData(GoalType goalType, int targetMinutes) {
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