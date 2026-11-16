package interface_adapter.view_study_history;

import use_case.view_study_history.ViewStudyHistoryOutputBoundary;
import use_case.view_study_history.ViewStudyHistoryOutputData;

public class ViewStudyHistoryPresenter
        implements ViewStudyHistoryOutputBoundary {

    private final ViewStudyHistoryViewModel viewModel;

    public ViewStudyHistoryPresenter(
            ViewStudyHistoryViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(
            ViewStudyHistoryOutputData outputData) {

        ViewStudyHistoryState state =
                viewModel.getState();

        state.setEntries(outputData.getEntries());
        state.setTotalMinutes(outputData.getTotalMinutes());

        viewModel.setState(state);
        viewModel.firePropertyChanged();
    }
}
