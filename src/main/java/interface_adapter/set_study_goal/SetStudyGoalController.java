
package interface_adapter.set_study_goal;

import entity.GoalType;
import use_case.set_study_goal.SetStudyGoalInputBoundary;
import use_case.set_study_goal.SetStudyGoalInputData;

/**
 * Controller for the Set Study Goal use case.
 */
public class SetStudyGoalController {

    private final SetStudyGoalInputBoundary interactor;

    public SetStudyGoalController(SetStudyGoalInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(GoalType goalType, int targetMinutes) {
        SetStudyGoalInputData inputData =
                new SetStudyGoalInputData(goalType, targetMinutes);

        interactor.execute(inputData);
    }
}
