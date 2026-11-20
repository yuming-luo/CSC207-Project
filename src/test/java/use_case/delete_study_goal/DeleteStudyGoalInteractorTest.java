package use_case.delete_study_goal;

import data_access.InMemoryStudyGoalDataAccessObject;
import entity.GoalType;
import entity.StudyGoal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteStudyGoalInteractorTest {

    @Test
    public void testDeleteExistingDailyGoal() {
        // Arrange
        InMemoryStudyGoalDataAccessObject dao =
                new InMemoryStudyGoalDataAccessObject();

        dao.save(new StudyGoal(GoalType.DAILY, 100));

        TestOutputBoundary presenter = new TestOutputBoundary();

        DeleteStudyGoalInteractor interactor =
                new DeleteStudyGoalInteractor(dao, presenter);

        DeleteStudyGoalInputData inputData =
                new DeleteStudyGoalInputData(GoalType.DAILY);

        // Act
        interactor.execute(inputData);

        // Assert
        assertNull(dao.getGoal(GoalType.DAILY));
        assertNotNull(presenter.outputData);
        assertEquals(GoalType.DAILY,
                presenter.outputData.getGoalType());
        assertNull(presenter.error);
    }


    @Test
    public void testDeleteNonexistentWeeklyGoal() {
        // Arrange
        InMemoryStudyGoalDataAccessObject dao =
                new InMemoryStudyGoalDataAccessObject();

        TestOutputBoundary presenter = new TestOutputBoundary();

        DeleteStudyGoalInteractor interactor =
                new DeleteStudyGoalInteractor(dao, presenter);

        DeleteStudyGoalInputData inputData =
                new DeleteStudyGoalInputData(GoalType.WEEKLY);

        // Act
        interactor.execute(inputData);

        // Assert
        assertNull(dao.getGoal(GoalType.WEEKLY));
        assertNull(presenter.outputData);
        assertEquals("WEEKLY goal does not exist.", presenter.error);
    }

    @Test
    public void testDeleteDailyDoesNotAffectWeekly() {
        // Arrange
        InMemoryStudyGoalDataAccessObject dao =
                new InMemoryStudyGoalDataAccessObject();

        dao.save(new StudyGoal(GoalType.DAILY, 100));
        dao.save(new StudyGoal(GoalType.WEEKLY, 600));

        TestOutputBoundary presenter = new TestOutputBoundary();

        DeleteStudyGoalInteractor interactor =
                new DeleteStudyGoalInteractor(dao, presenter);

        DeleteStudyGoalInputData inputData =
                new DeleteStudyGoalInputData(GoalType.DAILY);

        // Act
        interactor.execute(inputData);

        // Assert
        assertNull(dao.getGoal(GoalType.DAILY));

        assertNotNull(dao.getGoal(GoalType.WEEKLY));
        assertEquals(600,
                dao.getGoal(GoalType.WEEKLY).getTargetMinutes());

        assertNotNull(presenter.outputData);
        assertEquals(GoalType.DAILY,
                presenter.outputData.getGoalType());
        assertNull(presenter.error);
    }

    @Test
    public void testDeleteWeeklyDoesNotAffectDaily() {
        // Arrange
        InMemoryStudyGoalDataAccessObject dao =
                new InMemoryStudyGoalDataAccessObject();

        dao.save(new StudyGoal(GoalType.DAILY, 100));
        dao.save(new StudyGoal(GoalType.WEEKLY, 600));

        TestOutputBoundary presenter = new TestOutputBoundary();

        DeleteStudyGoalInteractor interactor =
                new DeleteStudyGoalInteractor(dao, presenter);

        DeleteStudyGoalInputData inputData =
                new DeleteStudyGoalInputData(GoalType.WEEKLY);

        // Act
        interactor.execute(inputData);

        // Assert
        assertNull(dao.getGoal(GoalType.WEEKLY));

        assertNotNull(dao.getGoal(GoalType.DAILY));
        assertEquals(100,
                dao.getGoal(GoalType.DAILY).getTargetMinutes());

        assertNotNull(presenter.outputData);
        assertEquals(GoalType.WEEKLY,
                presenter.outputData.getGoalType());
        assertNull(presenter.error);
    }

    @Test
    public void testNullGoalType() {
        // Arrange
        InMemoryStudyGoalDataAccessObject dao =
                new InMemoryStudyGoalDataAccessObject();

        dao.save(new StudyGoal(GoalType.DAILY, 100));

        TestOutputBoundary presenter = new TestOutputBoundary();

        DeleteStudyGoalInteractor interactor =
                new DeleteStudyGoalInteractor(dao, presenter);

        DeleteStudyGoalInputData inputData =
                new DeleteStudyGoalInputData(null);

        // Act
        interactor.execute(inputData);

        // Assert
        assertNotNull(dao.getGoal(GoalType.DAILY));
        assertEquals(100,
                dao.getGoal(GoalType.DAILY).getTargetMinutes());

        assertNull(presenter.outputData);
        assertEquals("Please select a goal type.", presenter.error);
    }

    @Test
    public void testNullInputData() {
        // Arrange
        InMemoryStudyGoalDataAccessObject dao =
                new InMemoryStudyGoalDataAccessObject();

        dao.save(new StudyGoal(GoalType.DAILY, 100));

        TestOutputBoundary presenter = new TestOutputBoundary();

        DeleteStudyGoalInteractor interactor =
                new DeleteStudyGoalInteractor(dao, presenter);

        // Act
        interactor.execute(null);

        // Assert
        assertNotNull(dao.getGoal(GoalType.DAILY));
        assertEquals(100,
                dao.getGoal(GoalType.DAILY).getTargetMinutes());

        assertNull(presenter.outputData);
        assertEquals("Please select a goal type.", presenter.error);
    }

    private static class TestOutputBoundary
            implements DeleteStudyGoalOutputBoundary {

        private DeleteStudyGoalOutputData outputData;
        private String error;

        @Override
        public void prepareSuccessView(
                DeleteStudyGoalOutputData outputData) {
            this.outputData = outputData;
        }

        @Override
        public void prepareFailView(String errorMessage) {
            this.error = errorMessage;
        }
    }
}
