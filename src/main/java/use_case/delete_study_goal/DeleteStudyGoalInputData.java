package use_case.delete_study_goal;

import entity.GoalType;

public class DeleteStudyGoalInputData {

    private final GoalType goalType;

    public DeleteStudyGoalInputData(GoalType goalType) {
        this.goalType = goalType;
    }

    public GoalType getGoalType() {
        return goalType;
    }
}
