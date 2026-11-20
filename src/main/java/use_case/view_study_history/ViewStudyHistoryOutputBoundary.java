package use_case.view_study_history;

public interface ViewStudyHistoryOutputBoundary {

    // Prepare study history data for successful retrieval
    void prepareSuccessView(ViewStudyHistoryOutputData outputData);

    // Prepare an error message when history retrieval fails
    void prepareFailView(String errorMessage);
}