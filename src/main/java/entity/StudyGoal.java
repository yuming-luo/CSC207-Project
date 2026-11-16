package entity;

/**
 * class to represent a student's weekly or daily goal
 */

public class StudyGoal {

    private GoalType goalType;
    private int targetMinutes;

    //Constructor to initialize values
    public StudyGoal(GoalType goalType, int targetMinutes){
        if (goalType == null){
            throw new IllegalArgumentException("Goal type can't be null");
        }
        if (targetMinutes <= 0){
            throw new IllegalArgumentException("Target minutes must be positive");
        }
        this.goalType = goalType;
        this.targetMinutes = targetMinutes;
    }
    public GoalType getGoalType(){
        return goalType;
    }
    public int getTargetMinutes(){
        return targetMinutes;
    }


}
