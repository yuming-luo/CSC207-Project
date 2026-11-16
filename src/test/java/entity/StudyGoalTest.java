package entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StudyGoalTest {
    @Test
    public void testValidDailyGoal() {
        StudyGoal goal = new StudyGoal(GoalType.DAILY, 150);

        assertEquals(GoalType.DAILY, goal.getGoalType());
        assertEquals(150, goal.getTargetMinutes());
    }
    @Test
    public void testValidWeeklyGoal() {
        StudyGoal goal = new StudyGoal(GoalType.WEEKLY, 800);

        assertEquals(GoalType.WEEKLY, goal.getGoalType());
        assertEquals(800, goal.getTargetMinutes());
    }

    @Test
    public void testZeroTargetMinutes() {
        assertThrows(IllegalArgumentException.class,
                () -> new StudyGoal(GoalType.DAILY, 0));
    }

    @Test
    public void testNegativeTargetMinutes() {
        assertThrows(IllegalArgumentException.class,
                () -> new StudyGoal(GoalType.DAILY, -1));
    }

    @Test
    public void testNullGoalType() {
        assertThrows(IllegalArgumentException.class,
                () -> new StudyGoal(null, 120));
    }
}
