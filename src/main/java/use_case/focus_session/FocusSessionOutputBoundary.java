package use_case.focus_session;

public interface FocusSessionOutputBoundary {

    void prepareSuccessView(FocusSessionOutputData outputData);

    void prepareFailView(String error);
}
