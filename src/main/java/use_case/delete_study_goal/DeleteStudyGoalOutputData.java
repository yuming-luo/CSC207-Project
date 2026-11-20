package use_case.delete_study_goal;

import entity.GoalType;

public class DeleteStudyGoalOutputData {

    private final GoalType goalType;

    public DeleteStudyGoalOutputData(GoalType goalType) {
        this.goalType = goalType;
    }

    public GoalType getGoalType() {
        return goalType;
    }
}
