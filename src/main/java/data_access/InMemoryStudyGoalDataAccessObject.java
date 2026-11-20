package data_access;

import entity.GoalType;
import entity.StudyGoal;
import use_case.set_study_goal.SetStudyGoalDataAccessInterface;
import use_case.delete_study_goal.DeleteStudyGoalDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * In-memory data access object for study goals.
 */
public class InMemoryStudyGoalDataAccessObject
        implements SetStudyGoalDataAccessInterface,
            DeleteStudyGoalDataAccessInterface{

    private final Map<GoalType, StudyGoal> goals = new HashMap<>();

    @Override
    public void save(StudyGoal goal) {
        goals.put(goal.getGoalType(), goal);
    }

    @Override
    public StudyGoal getGoal(GoalType goalType) {
        return goals.get(goalType);
    }

    @Override
    public void deleteGoal(GoalType goalType) {
        goals.remove(goalType);
    }
}