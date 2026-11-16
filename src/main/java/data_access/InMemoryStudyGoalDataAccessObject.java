package data_access;

import entity.GoalType;
import entity.StudyGoal;
import use_case.set_study_goal.SetStudyGoalDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * In-memory data access object for study goals.
 */
public class InMemoryStudyGoalDataAccessObject
        implements SetStudyGoalDataAccessInterface {

    private final Map<GoalType, StudyGoal> goals = new HashMap<>();

    @Override
    public void save(StudyGoal goal) {
        goals.put(goal.getGoalType(), goal);
    }

    public StudyGoal getGoal(GoalType goalType) {
        return goals.get(goalType);
    }
}