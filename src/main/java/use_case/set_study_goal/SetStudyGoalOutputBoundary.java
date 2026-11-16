package use_case.set_study_goal;

/**
 * Output boundary for the Set Study Goal use case.
 */
public interface SetStudyGoalOutputBoundary {

    void prepareSuccessView(SetStudyGoalOutputData outputData);

    void prepareFailView(String error);
}
