package use_case.delete_study_goal;

import entity.GoalType;

public class DeleteStudyGoalInteractor
        implements DeleteStudyGoalInputBoundary {

    private final DeleteStudyGoalDataAccessInterface dataAccess;
    private final DeleteStudyGoalOutputBoundary outputBoundary;

    public DeleteStudyGoalInteractor(
            DeleteStudyGoalDataAccessInterface dataAccess,
            DeleteStudyGoalOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(DeleteStudyGoalInputData inputData) {
        if (inputData == null || inputData.getGoalType() == null) {
            outputBoundary.prepareFailView("Please select a goal type.");
        }
        else {
            GoalType goalType = inputData.getGoalType();

            if (dataAccess.getGoal(goalType) == null) {
                outputBoundary.prepareFailView(
                        goalType + " goal does not exist.");
            }
            else {
                dataAccess.deleteGoal(goalType);

                DeleteStudyGoalOutputData outputData =
                        new DeleteStudyGoalOutputData(goalType);

                outputBoundary.prepareSuccessView(outputData);
            }
        }
    }
}
