package use_case.study_goal_progress;

import entity.GoalType;
import org.junit.jupiter.api.Test;
import use_case.view_study_history.StudyHistoryEntry;
import use_case.view_study_history.StudySessionDataAccessInterface;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for the StudyGoalMinutesCalculator.
 */
public class StudyGoalMinutesCalculatorTest {

    /**
     * Fake study history data source used for testing.
     */
    private static class FakeStudySessionDataAccessObject
            implements StudySessionDataAccessInterface {

        private final List<StudyHistoryEntry> sessions = new ArrayList<>();

        @Override
        public List<StudyHistoryEntry> getCompletedSessions() {
            return sessions;
        }

        @Override
        public void addSession(StudyHistoryEntry session) {
            sessions.add(session);
        }
    }

    private void addTestSession(
            FakeStudySessionDataAccessObject dao,
            LocalDateTime startTime,
            int minutes) {

        dao.addSession(new StudyHistoryEntry(
                startTime, minutes, "Water"));
    }

    @Test
    public void testDailyMinutes() {
        FakeStudySessionDataAccessObject dao =
                new FakeStudySessionDataAccessObject();

        addTestSession(dao, LocalDateTime.of(2026, 11, 25, 9, 0), 30);
        addTestSession(dao, LocalDateTime.of(2026, 11, 25, 15, 0), 45);
        addTestSession(dao, LocalDateTime.of(2026, 11, 26, 10, 0), 60);

        StudyGoalMinutesCalculator calculator =
                new StudyGoalMinutesCalculator(dao);

        assertEquals(75, calculator.getCompletedMinutes(
                GoalType.DAILY, LocalDate.of(2026, 11, 25)));
    }

    @Test
    public void testDailyNoMatchingSessions() {
        FakeStudySessionDataAccessObject dao =
                new FakeStudySessionDataAccessObject();

        addTestSession(dao, LocalDateTime.of(2026, 11, 24, 10, 0), 30);

        StudyGoalMinutesCalculator calculator =
                new StudyGoalMinutesCalculator(dao);

        assertEquals(0, calculator.getCompletedMinutes(
                GoalType.DAILY, LocalDate.of(2026, 11, 25)));
    }

    @Test
    public void testWeeklyMinutes() {
        FakeStudySessionDataAccessObject dao =
                new FakeStudySessionDataAccessObject();

        addTestSession(dao, LocalDateTime.of(2026, 11, 23, 9, 0), 30);
        addTestSession(dao, LocalDateTime.of(2026, 11, 25, 14, 0), 45);
        addTestSession(dao, LocalDateTime.of(2026, 11, 29, 18, 0), 20);

        StudyGoalMinutesCalculator calculator =
                new StudyGoalMinutesCalculator(dao);

        assertEquals(95, calculator.getCompletedMinutes(
                GoalType.WEEKLY, LocalDate.of(2026, 11, 25)));
    }

    @Test
    public void testWeeklyExcludesOtherWeeks() {
        FakeStudySessionDataAccessObject dao =
                new FakeStudySessionDataAccessObject();

        addTestSession(dao, LocalDateTime.of(2026, 11, 22, 10, 0), 50);
        addTestSession(dao, LocalDateTime.of(2026, 11, 24, 10, 0), 30);
        addTestSession(dao, LocalDateTime.of(2026, 11, 30, 10, 0), 40);

        StudyGoalMinutesCalculator calculator =
                new StudyGoalMinutesCalculator(dao);

        assertEquals(30, calculator.getCompletedMinutes(
                GoalType.WEEKLY, LocalDate.of(2026, 11, 25)));
    }

    @Test
    public void testMondayBoundary() {
        FakeStudySessionDataAccessObject dao =
                new FakeStudySessionDataAccessObject();

        addTestSession(dao, LocalDateTime.of(2026, 11, 23, 0, 0), 20);

        StudyGoalMinutesCalculator calculator =
                new StudyGoalMinutesCalculator(dao);

        assertEquals(20, calculator.getCompletedMinutes(
                GoalType.WEEKLY, LocalDate.of(2026, 11, 23)));
    }

    @Test
    public void testSundayBoundary() {
        FakeStudySessionDataAccessObject dao =
                new FakeStudySessionDataAccessObject();

        addTestSession(dao, LocalDateTime.of(2026, 11, 29, 23, 0), 30);

        StudyGoalMinutesCalculator calculator =
                new StudyGoalMinutesCalculator(dao);

        assertEquals(30, calculator.getCompletedMinutes(
                GoalType.WEEKLY, LocalDate.of(2026, 11, 29)));
    }

    @Test
    public void testEmptyHistory() {
        FakeStudySessionDataAccessObject dao =
                new FakeStudySessionDataAccessObject();

        StudyGoalMinutesCalculator calculator =
                new StudyGoalMinutesCalculator(dao);

        assertEquals(0, calculator.getCompletedMinutes(
                GoalType.DAILY, LocalDate.of(2026, 11, 25)));

        assertEquals(0, calculator.getCompletedMinutes(
                GoalType.WEEKLY, LocalDate.of(2026, 11, 25)));
    }

    @Test
    public void testNullDataAccess() {
        assertThrows(IllegalArgumentException.class,
                () -> new StudyGoalMinutesCalculator(null));
    }

    @Test
    public void testNullGoalType() {
        FakeStudySessionDataAccessObject dao =
                new FakeStudySessionDataAccessObject();

        StudyGoalMinutesCalculator calculator =
                new StudyGoalMinutesCalculator(dao);

        assertThrows(IllegalArgumentException.class,
                () -> calculator.getCompletedMinutes(
                        null, LocalDate.of(2026, 11, 25)));
    }

    @Test
    public void testNullReferenceDate() {
        FakeStudySessionDataAccessObject dao =
                new FakeStudySessionDataAccessObject();

        StudyGoalMinutesCalculator calculator =
                new StudyGoalMinutesCalculator(dao);

        assertThrows(IllegalArgumentException.class,
                () -> calculator.getCompletedMinutes(
                        GoalType.DAILY, null));
    }
}
