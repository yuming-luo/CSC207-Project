package use_case.view_study_history;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

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
    @Override
    public void execute(LocalDate startDate, LocalDate endDate) {

        // Validate date range
        if (startDate == null || endDate == null
                || startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Invalid date range");
        }

        // Filter study sessions by date
        List<StudyHistoryEntry> filteredEntries = new ArrayList<>();

        for (StudyHistoryEntry entry : dataAccess.getCompletedSessions()) {
            LocalDate sessionDate = entry.getStartTime().toLocalDate();

            if (!sessionDate.isBefore(startDate)
                    && !sessionDate.isAfter(endDate)) {
                filteredEntries.add(entry);
            }
        }

        // Calculate total study time
        int totalMinutes = 0;

        for (StudyHistoryEntry entry : filteredEntries) {
            totalMinutes += entry.getDurationMinutes();
        }

        // Prepare output data
        ViewStudyHistoryOutputData outputData =
                new ViewStudyHistoryOutputData(filteredEntries, totalMinutes);

        presenter.prepareSuccessView(outputData);
    }
}

