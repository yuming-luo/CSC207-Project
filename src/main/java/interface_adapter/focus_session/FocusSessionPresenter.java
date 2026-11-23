package interface_adapter.focus_session;

import use_case.focus_session.FocusSessionOutputBoundary;
import use_case.focus_session.FocusSessionOutputData;

public class FocusSessionPresenter
        implements FocusSessionOutputBoundary {

    private final FocusSessionViewModel viewModel;

    public FocusSessionPresenter(FocusSessionViewModel viewModel) {
        this.viewModel = viewModel;
    }


    @Override
    public void prepareSuccessView(FocusSessionOutputData outputData) {
        FocusSessionState state = viewModel.getState();

        state.setSessionState(outputData.getState());
        state.setTimerMode(outputData.getTimerMode());
        state.setElapsedSeconds(outputData.getElapsedSeconds());
        state.setRemainingSeconds(outputData.getRemainingSeconds());
        state.setRemainingPausedSeconds(outputData.getRemainingPausedSeconds());
        state.setSessionEndReason(outputData.getSessionEndReason());
        state.setDrink(outputData.getDrink());
        state.setError("");

        viewModel.setState(state);
        viewModel.firePropertyChanged();
    }



    @Override
    public void prepareFailView(String error) {
        FocusSessionState state = viewModel.getState();

        state.setError(error);

        viewModel.setState(state);
        viewModel.firePropertyChanged();
    }

}

