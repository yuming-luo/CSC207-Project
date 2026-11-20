package use_case.view_study_history;

import java.time.LocalDate;

public interface ViewStudyHistoryInputBoundary {

    // Display all study sessions
    void execute();

    // Display study sessions within a date range
    void execute(LocalDate startDate, LocalDate endDate);
}