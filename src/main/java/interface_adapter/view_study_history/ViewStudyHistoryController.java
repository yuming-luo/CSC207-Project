package interface_adapter.view_study_history;

import use_case.view_study_history.ViewStudyHistoryInputBoundary;

public class ViewStudyHistoryController {

    private final ViewStudyHistoryInputBoundary interactor;

    public ViewStudyHistoryController(ViewStudyHistoryInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute() {
        interactor.execute();
    }
}
