package use_case.view_study_history;

import java.util.List;

public class ViewStudyHistoryInteractor
        implements ViewStudyHistoryInputBoundary {

    private final StudySessionDataAccessInterface dataAccess;
    private final ViewStudyHistoryOutputBoundary presenter;

    public ViewStudyHistoryInteractor(
            StudySessionDataAccessInterface dataAccess,
            ViewStudyHistoryOutputBoundary presenter) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
    }

    @Override
    public void execute() {
        List<StudyHistoryEntry> sessions = dataAccess.getCompletedSessions();

        int totalMinutes = 0;

        for (StudyHistoryEntry session : sessions) {
            totalMinutes += session.getDurationMinutes();
        }

        ViewStudyHistoryOutputData outputData = new ViewStudyHistoryOutputData(sessions, totalMinutes);

        presenter.prepareSuccessView(outputData);
    }
}

