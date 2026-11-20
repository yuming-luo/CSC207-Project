package interface_adapter.delete_study_goal;

import entity.GoalType;
import use_case.delete_study_goal.DeleteStudyGoalInputBoundary;
import use_case.delete_study_goal.DeleteStudyGoalInputData;

public class DeleteStudyGoalController {

    private final DeleteStudyGoalInputBoundary interactor;

    public DeleteStudyGoalController(DeleteStudyGoalInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(GoalType goalType) {
        DeleteStudyGoalInputData inputData =
                new DeleteStudyGoalInputData(goalType);

        interactor.execute(inputData);
    }
}
