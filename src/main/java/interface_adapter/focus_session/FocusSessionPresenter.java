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

    }

    @Override
    public void prepareFailView(String error) {

    }
}

