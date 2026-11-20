package use_case.delete_study_goal;

public interface DeleteStudyGoalOutputBoundary {

    void prepareSuccessView(DeleteStudyGoalOutputData outputData);

    void prepareFailView(String errorMessage);
}
