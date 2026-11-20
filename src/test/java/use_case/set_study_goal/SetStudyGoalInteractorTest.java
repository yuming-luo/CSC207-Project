package use_case.set_study_goal;

import data_access.InMemoryStudyGoalDataAccessObject;
import entity.GoalType;
import entity.StudyGoal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SetStudyGoalInteractorTest {

    @Test
    public void testValidDailyGoal() {
        InMemoryStudyGoalDataAccessObject dao =
                new InMemoryStudyGoalDataAccessObject();
        TestOutputBoundary presenter = new TestOutputBoundary();

        SetStudyGoalInteractor interactor =
                new SetStudyGoalInteractor(dao, presenter);

        SetStudyGoalInputData inputData =
                new SetStudyGoalInputData(GoalType.DAILY, 120);

        interactor.execute(inputData);

        StudyGoal savedGoal = dao.getGoal(GoalType.DAILY);

        assertNotNull(savedGoal);
        assertEquals(120, savedGoal.getTargetMinutes());
        assertNotNull(presenter.outputData);
        assertNull(presenter.error);
    }

    @Test
    public void testZeroTargetMinutes() {
        InMemoryStudyGoalDataAccessObject dao =
                new InMemoryStudyGoalDataAccessObject();
        TestOutputBoundary presenter = new TestOutputBoundary();

        SetStudyGoalInteractor interactor =
                new SetStudyGoalInteractor(dao, presenter);

        SetStudyGoalInputData inputData =
                new SetStudyGoalInputData(GoalType.DAILY, 0);

        interactor.execute(inputData);

        assertNull(dao.getGoal(GoalType.DAILY));
        assertEquals("Target minutes must be positive",
                presenter.error);
        assertNull(presenter.outputData);
    }

    @Test
    public void testNullGoalType() {
        InMemoryStudyGoalDataAccessObject dao =
                new InMemoryStudyGoalDataAccessObject();
        TestOutputBoundary presenter = new TestOutputBoundary();

        SetStudyGoalInteractor interactor =
                new SetStudyGoalInteractor(dao, presenter);

        SetStudyGoalInputData inputData =
                new SetStudyGoalInputData(null, 120);

        interactor.execute(inputData);

        assertEquals("Goal type cannot be null", presenter.error);
        assertNull(presenter.outputData);
    }


    @Test
    public void testNegativeTargetMinutes() {
        InMemoryStudyGoalDataAccessObject dao =
                new InMemoryStudyGoalDataAccessObject();
        TestOutputBoundary presenter = new TestOutputBoundary();

        SetStudyGoalInteractor interactor =
                new SetStudyGoalInteractor(dao, presenter);

        SetStudyGoalInputData inputData =
                new SetStudyGoalInputData(GoalType.DAILY, -30);

        interactor.execute(inputData);

        assertNull(dao.getGoal(GoalType.DAILY));
        assertEquals("Target minutes must be positive", presenter.error);
        assertNull(presenter.outputData);
    }

    @Test
    public void testUpdateDailyGoal() {
        InMemoryStudyGoalDataAccessObject dao =
                new InMemoryStudyGoalDataAccessObject();
        TestOutputBoundary presenter = new TestOutputBoundary();
        SetStudyGoalInteractor interactor =
                new SetStudyGoalInteractor(dao, presenter);

        // Create the original DAILY goal.
        SetStudyGoalInputData originalGoal =
                new SetStudyGoalInputData(GoalType.DAILY, 100);
        interactor.execute(originalGoal);

        // Update the DAILY goal to 180 minutes.
        SetStudyGoalInputData updatedGoal =
                new SetStudyGoalInputData(GoalType.DAILY, 200);
        interactor.execute(updatedGoal);

        // Verify the new value was saved.
        StudyGoal savedGoal = dao.getGoal(GoalType.DAILY);
        assertNotNull(savedGoal);
        assertEquals(200, savedGoal.getTargetMinutes());

        // Verify the Interactor reported an update.
        assertNotNull(presenter.outputData);
        assertTrue(presenter.outputData.getDoesExist());
        assertNull(presenter.error);
    }

    @Test
    public void testInvalidUpdateDoesNotOverwriteGoal() {
        InMemoryStudyGoalDataAccessObject dao =
                new InMemoryStudyGoalDataAccessObject();
        TestOutputBoundary presenter = new TestOutputBoundary();
        SetStudyGoalInteractor interactor =
                new SetStudyGoalInteractor(dao, presenter);

        // Create an initial valid goal.
        SetStudyGoalInputData originalGoal =
                new SetStudyGoalInputData(GoalType.DAILY, 100);
        interactor.execute(originalGoal);

        // Attempt to update the goal a non-positive target.
        SetStudyGoalInputData invalidUpdate =
                new SetStudyGoalInputData(GoalType.DAILY, 0);
        interactor.execute(invalidUpdate);

        // The initial goal must stay the same.
        StudyGoal savedGoal = dao.getGoal(GoalType.DAILY);
        assertNotNull(savedGoal);
        assertEquals(100, savedGoal.getTargetMinutes());

        // The Interactor must report a failure.
        assertEquals("Target minutes must be positive", presenter.error);
    }

    @Test
    public void testUpdateWeeklyGoal() {
        InMemoryStudyGoalDataAccessObject dao =
                new InMemoryStudyGoalDataAccessObject();
        TestOutputBoundary presenter = new TestOutputBoundary();
        SetStudyGoalInteractor interactor =
                new SetStudyGoalInteractor(dao, presenter);

        // Create a DAILY goal
        interactor.execute(
                new SetStudyGoalInputData(GoalType.DAILY, 100));

        // Create a WEEKLY goal
        interactor.execute(
                new SetStudyGoalInputData(GoalType.WEEKLY, 600));

        // Update the WEEKLY goal only
        interactor.execute(
                new SetStudyGoalInputData(GoalType.WEEKLY, 700));

        // The WEEKLY goal should have the new target
        StudyGoal weeklyGoal = dao.getGoal(GoalType.WEEKLY);
        assertNotNull(weeklyGoal);
        assertEquals(700, weeklyGoal.getTargetMinutes());

        // The DAILY goal should remain unchanged
        StudyGoal dailyGoal = dao.getGoal(GoalType.DAILY);
        assertNotNull(dailyGoal);
        assertEquals(100, dailyGoal.getTargetMinutes());

        // Verify that the operation was an update
        assertNotNull(presenter.outputData);
        assertTrue(presenter.outputData.getDoesExist());
        assertNull(presenter.error);
    }

    private static class TestOutputBoundary
            implements SetStudyGoalOutputBoundary {

        private SetStudyGoalOutputData outputData;
        private String error;

        @Override
        public void prepareSuccessView(SetStudyGoalOutputData outputData) {
            this.outputData = outputData;
        }

        @Override
        public void prepareFailView(String error) {
            this.error = error;
        }
    }
}
