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
