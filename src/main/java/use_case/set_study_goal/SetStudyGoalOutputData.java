package use_case.set_study_goal;

import entity.GoalType;

/**
 * Output data for the Set Study Goal use case.
 */
public class SetStudyGoalOutputData {

    private final GoalType goalType;
    private final int targetMinutes;
    private final boolean doesExist;

    public SetStudyGoalOutputData(GoalType goalType, int targetMinutes, boolean doesExist) {
        this.goalType = goalType;
        this.targetMinutes = targetMinutes;
        this.doesExist = doesExist;
    }

    public GoalType getGoalType() {
        return goalType;
    }

    public int getTargetMinutes() {
        return targetMinutes;
    }

    public boolean getDoesExist(){
        return doesExist;
    }
}