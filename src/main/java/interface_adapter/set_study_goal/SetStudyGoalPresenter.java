
package interface_adapter.set_study_goal;

import use_case.set_study_goal.SetStudyGoalOutputBoundary;
import use_case.set_study_goal.SetStudyGoalOutputData;

/**
 * Presenter for the Set Study Goal use case.
 */
public class SetStudyGoalPresenter implements SetStudyGoalOutputBoundary {

    private final SetStudyGoalViewModel viewModel;

    public SetStudyGoalPresenter(SetStudyGoalViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(SetStudyGoalOutputData outputData) {
        SetStudyGoalState state = viewModel.getState();

        String message;

        if (outputData.getDoesExist()) {
            message = outputData.getGoalType()
                    + " goal updated to "
                    + outputData.getTargetMinutes()
                    + " minutes!";
        }
        else {
            message = outputData.getGoalType()
                    + " goal set to "
                    + outputData.getTargetMinutes()
                    + " minutes!";
        }

        state.setMessage(message);
        state.setError("");

        viewModel.setState(state);
        viewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        SetStudyGoalState state = viewModel.getState();

        state.setError(error);
        state.setMessage("");

        viewModel.setState(state);
        viewModel.firePropertyChanged();
    }
}
