package use_case.set_study_goal;

import entity.StudyGoal;

/**
 * Data access interface for the Set Study Goal use case.
 */
public interface SetStudyGoalDataAccessInterface {

    void save(StudyGoal goal);
}