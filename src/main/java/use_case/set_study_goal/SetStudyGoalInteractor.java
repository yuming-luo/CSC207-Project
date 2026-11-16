package use_case.set_study_goal;

import entity.StudyGoal;

/**
 * Interactor for the Set Study Goal use case.
 */
public class SetStudyGoalInteractor implements SetStudyGoalInputBoundary {

    private final SetStudyGoalDataAccessInterface dataAccess;
    private final SetStudyGoalOutputBoundary outputBoundary;

    public SetStudyGoalInteractor(
            SetStudyGoalDataAccessInterface dataAccess,
            SetStudyGoalOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(SetStudyGoalInputData inputData) {

        if (inputData.getGoalType() == null) {
            outputBoundary.prepareFailView("Goal type cannot be null");
        }
        else if (inputData.getTargetMinutes() <= 0) {
            outputBoundary.prepareFailView("Target minutes must be positive");
        }
        else {
            StudyGoal goal = new StudyGoal(
                    inputData.getGoalType(),
                    inputData.getTargetMinutes());

            dataAccess.save(goal);

            SetStudyGoalOutputData outputData =
                    new SetStudyGoalOutputData(
                            goal.getGoalType(),
                            goal.getTargetMinutes());

            outputBoundary.prepareSuccessView(outputData);
        }
    }
}