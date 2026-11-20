package use_case.delete_study_goal;

import entity.GoalType;
import entity.StudyGoal;

public interface DeleteStudyGoalDataAccessInterface {

    /**
     * method to allow Interactor make a decision on whether goal
     * deletion is possible
     */
    StudyGoal getGoal(GoalType goalType);

    void deleteGoal(GoalType goalType);
}