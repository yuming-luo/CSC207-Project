package interface_adapter.delete_study_goal;

import interface_adapter.set_study_goal.SetStudyGoalState;
import interface_adapter.set_study_goal.SetStudyGoalViewModel;
import use_case.delete_study_goal.DeleteStudyGoalOutputBoundary;
import use_case.delete_study_goal.DeleteStudyGoalOutputData;

public class DeleteStudyGoalPresenter
        implements DeleteStudyGoalOutputBoundary {

    private final SetStudyGoalViewModel viewModel;

    public DeleteStudyGoalPresenter(SetStudyGoalViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(DeleteStudyGoalOutputData outputData) {
        SetStudyGoalState state = viewModel.getState();

        String message = outputData.getGoalType()
                + " goal deleted successfully!";

        state.setMessage(message);
        state.setError("");

        viewModel.setState(state);
        viewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        SetStudyGoalState state = viewModel.getState();

        state.setError(errorMessage);
        state.setMessage("");

        viewModel.setState(state);
        viewModel.firePropertyChanged();
    }
}
