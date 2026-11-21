package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class StudyGoalProgressTest {

    @Test
    void constructorRejectsNullGoal() {
        assertThrows(IllegalArgumentException.class, () -> {
            new StudyGoalProgress(null, 30);
        });
    }

    @Test
    void constructorRejectsNegativeCompletedMinutes() {
        StudyGoal goal = new StudyGoal(GoalType.DAILY, 120);

        assertThrows(IllegalArgumentException.class, () -> {
            new StudyGoalProgress(goal, -10);
        });
    }

    @Test
    void calculateProgressPercentage() {
        StudyGoal goal = new StudyGoal(GoalType.DAILY, 120);
        StudyGoalProgress progress = new StudyGoalProgress(goal, 45);

        assertEquals(37.5, progress.getProgressPercentage(), 0.0001);
    }

    @Test
    void calculateZeroProgress() {
        StudyGoal goal = new StudyGoal(GoalType.DAILY, 120);
        StudyGoalProgress progress = new StudyGoalProgress(goal, 0);

        assertEquals(0.0, progress.getProgressPercentage(), 0.0001);
    }

    @Test
    void calculateFullProgress() {
        StudyGoal goal = new StudyGoal(GoalType.DAILY, 120);
        StudyGoalProgress progress = new StudyGoalProgress(goal, 120);

        assertEquals(100.0, progress.getProgressPercentage(), 0.0001);
    }

    @Test
    void calculateProgressExceedingTarget() {
        StudyGoal goal = new StudyGoal(GoalType.WEEKLY, 200);
        StudyGoalProgress progress = new StudyGoalProgress(goal, 250);

        assertEquals(125.0, progress.getProgressPercentage(), 0.0001);
    }
    @Test
    void goalMetAtExactTarget() {
        StudyGoal goal = new StudyGoal(GoalType.DAILY, 120);
        StudyGoalProgress progress = new StudyGoalProgress(goal, 120);

        assertTrue(progress.isGoalMet());
    }
    @Test
    void goalNotMetBelowTarget() {
        StudyGoal goal = new StudyGoal(GoalType.DAILY, 120);
        StudyGoalProgress progress = new StudyGoalProgress(goal, 60);

        assertFalse(progress.isGoalMet());
    }
    @Test
    void goalMetAboveTarget() {
        StudyGoal goal = new StudyGoal(GoalType.WEEKLY, 200);
        StudyGoalProgress progress = new StudyGoalProgress(goal, 250);

        assertTrue(progress.isGoalMet());
    }
}