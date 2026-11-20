package use_case.view_study_history;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ViewStudyHistoryInteractorTest {
    @Test
    void testTotalStudyTime() {

        // Create sample study sessions
        List<StudyHistoryEntry> sessions = new ArrayList<>();

        sessions.add(new StudyHistoryEntry(
                LocalDateTime.of(2026, 11, 14, 10, 0),
                45,
                "Coffee"
        ));

        sessions.add(new StudyHistoryEntry(
                LocalDateTime.of(2026, 11, 15, 14, 0),
                30,
                "Tea"
        ));

        // Calculate total study time
        int totalMinutes = 0;

        for (StudyHistoryEntry session : sessions) {
            totalMinutes += session.getDurationMinutes();
        }

        // Check expected result
        assertEquals(75, totalMinutes);
    }

    @Test
    void testInteractorTotalStudyTime() {

        // Create test sessions
        List<StudyHistoryEntry> sessions = new ArrayList<>();

        sessions.add(new StudyHistoryEntry(
                LocalDateTime.of(2026, 11, 14, 10, 0),
                45,
                "Coffee"
        ));

        sessions.add(new StudyHistoryEntry(
                LocalDateTime.of(2026, 11, 15, 14, 0),
                30,
                "Tea"
        ));

        // Create a test DAO
        StudySessionDataAccessInterface dataAccess =
                new StudySessionDataAccessInterface() {

                    @Override
                    public List<StudyHistoryEntry> getCompletedSessions() {
                        return sessions;
                    }

                    @Override
                    public void addSession(StudyHistoryEntry session) {
                        sessions.add(session);
                    }
                };

        // Capture the Interactor output
        final ViewStudyHistoryOutputData[] result = new ViewStudyHistoryOutputData[1];

        ViewStudyHistoryOutputBoundary presenter =
                new ViewStudyHistoryOutputBoundary() {

                    @Override
                    public void prepareSuccessView(ViewStudyHistoryOutputData outputData) {
                        result[0] = outputData;
                    }

                    @Override
                    public void prepareFailView(String errorMessage) {
                        // Not expected in this test
                    }
                };

        // Execute the real Interactor
        ViewStudyHistoryInteractor interactor =
                new ViewStudyHistoryInteractor(dataAccess, presenter);

        interactor.execute();

        // Verify the result
        assertNotNull(result[0]);
        assertEquals(75, result[0].getTotalMinutes());
        assertEquals(2, result[0].getEntries().size());
    }

    @Test
    void testDateRangeFilter() {

        // Create test sessions
        List<StudyHistoryEntry> sessions = new ArrayList<>();

        sessions.add(new StudyHistoryEntry(
                LocalDateTime.of(2026, 11, 14, 10, 0),
                45,
                "Coffee"
        ));

        sessions.add(new StudyHistoryEntry(
                LocalDateTime.of(2026, 11, 15, 14, 0),
                30,
                "Tea"
        ));

        sessions.add(new StudyHistoryEntry(
                LocalDateTime.of(2026, 11, 20, 12, 0),
                50,
                "Coffee"
        ));

        // Create a test DAO
        StudySessionDataAccessInterface dataAccess =
                new StudySessionDataAccessInterface() {

                    @Override
                    public List<StudyHistoryEntry> getCompletedSessions() {
                        return sessions;
                    }

                    @Override
                    public void addSession(StudyHistoryEntry session) {
                        sessions.add(session);
                    }
                };

        // Capture the Interactor output
        final ViewStudyHistoryOutputData[] result =
                new ViewStudyHistoryOutputData[1];

        ViewStudyHistoryOutputBoundary presenter =
                new ViewStudyHistoryOutputBoundary() {

                    @Override
                    public void prepareSuccessView(ViewStudyHistoryOutputData outputData) {
                        result[0] = outputData;
                    }

                    @Override
                    public void prepareFailView(String errorMessage) {
                        fail(errorMessage);
                    }
                };

        // Execute the date range filter
        ViewStudyHistoryInteractor interactor =
                new ViewStudyHistoryInteractor(dataAccess, presenter);

        interactor.execute(
                LocalDate.of(2026, 11, 15),
                LocalDate.of(2026, 11, 20)
        );

        // Verify filtered results
        assertNotNull(result[0]);
        assertEquals(2, result[0].getEntries().size());
        assertEquals(80, result[0].getTotalMinutes());
    }

    @Test
    void testEmptyHistory() {

        // Create an empty session list
        List<StudyHistoryEntry> sessions = new ArrayList<>();

        // Create a test DAO
        StudySessionDataAccessInterface dataAccess =
                new StudySessionDataAccessInterface() {

                    @Override
                    public List<StudyHistoryEntry> getCompletedSessions() {
                        return sessions;
                    }

                    @Override
                    public void addSession(StudyHistoryEntry session) {
                        sessions.add(session);
                    }
                };

        // Capture the Interactor output
        final ViewStudyHistoryOutputData[] result =
                new ViewStudyHistoryOutputData[1];

        ViewStudyHistoryOutputBoundary presenter =
                new ViewStudyHistoryOutputBoundary() {

                    @Override
                    public void prepareSuccessView(
                            ViewStudyHistoryOutputData outputData) {
                        result[0] = outputData;
                    }

                    @Override
                    public void prepareFailView(String errorMessage) {
                        fail(errorMessage);
                    }
                };

        // Execute the Interactor
        ViewStudyHistoryInteractor interactor =
                new ViewStudyHistoryInteractor(dataAccess, presenter);

        interactor.execute();

        // Verify empty history results
        assertNotNull(result[0]);
        assertEquals(0, result[0].getEntries().size());
        assertEquals(0, result[0].getTotalMinutes());
    }

    @Test
    void testNoMatchingSessions() {

        // Create test sessions
        List<StudyHistoryEntry> sessions = new ArrayList<>();

        sessions.add(new StudyHistoryEntry(
                LocalDateTime.of(2026, 11, 14, 10, 0),
                45,
                "Coffee"
        ));

        sessions.add(new StudyHistoryEntry(
                LocalDateTime.of(2026, 11, 15, 14, 0),
                30,
                "Tea"
        ));

        // Create a test DAO
        StudySessionDataAccessInterface dataAccess =
                new StudySessionDataAccessInterface() {

                    @Override
                    public List<StudyHistoryEntry> getCompletedSessions() {
                        return sessions;
                    }

                    @Override
                    public void addSession(StudyHistoryEntry session) {
                        sessions.add(session);
                    }
                };

        // Capture the Interactor output
        final ViewStudyHistoryOutputData[] result =
                new ViewStudyHistoryOutputData[1];

        ViewStudyHistoryOutputBoundary presenter =
                new ViewStudyHistoryOutputBoundary() {

                    @Override
                    public void prepareSuccessView(
                            ViewStudyHistoryOutputData outputData) {
                        result[0] = outputData;
                    }

                    @Override
                    public void prepareFailView(String errorMessage) {
                        fail(errorMessage);
                    }
                };

        // Execute the date range filter
        ViewStudyHistoryInteractor interactor =
                new ViewStudyHistoryInteractor(dataAccess, presenter);

        interactor.execute(
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2020, 1, 31)
        );

        // Verify no matching sessions
        assertNotNull(result[0]);
        assertEquals(0, result[0].getEntries().size());
        assertEquals(0, result[0].getTotalMinutes());
    }

    @Test
    void testInvalidDateRange() {

        // Create an empty test DAO
        StudySessionDataAccessInterface dataAccess =
                new StudySessionDataAccessInterface() {

                    @Override
                    public List<StudyHistoryEntry> getCompletedSessions() {
                        return new ArrayList<>();
                    }

                    @Override
                    public void addSession(StudyHistoryEntry session) {
                        // No sessions need to be saved for this test
                    }
                };

        // Create a test Presenter
        ViewStudyHistoryOutputBoundary presenter =
                new ViewStudyHistoryOutputBoundary() {

                    @Override
                    public void prepareSuccessView(
                            ViewStudyHistoryOutputData outputData) {
                        fail("Invalid dates should not produce a success result.");
                    }

                    @Override
                    public void prepareFailView(String errorMessage) {
                        fail("This test expects an IllegalArgumentException.");
                    }
                };

        // Create the Interactor
        ViewStudyHistoryInteractor interactor =
                new ViewStudyHistoryInteractor(dataAccess, presenter);

        // Verify that an invalid date range is rejected
        assertThrows(IllegalArgumentException.class, () ->
                interactor.execute(
                        LocalDate.of(2026, 11, 20),
                        LocalDate.of(2026, 11, 1)
                )
        );
    }
}


