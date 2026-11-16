
package data_access;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import use_case.view_study_history.StudyHistoryEntry;
import use_case.view_study_history.StudySessionDataAccessInterface;

public class InMemoryStudySessionDataAccessObject
        implements StudySessionDataAccessInterface {

    private final List<StudyHistoryEntry> sessions =
            new ArrayList<>();

    public InMemoryStudySessionDataAccessObject() {
        sessions.add(new StudyHistoryEntry(
                LocalDateTime.of(2026, 11, 15, 14, 30),
                45,
                "Coffee"
        ));

        sessions.add(new StudyHistoryEntry(
                LocalDateTime.of(2026, 11, 14, 10, 0),
                30,
                "Tea"
        ));
    }

    @Override
    public List<StudyHistoryEntry> getCompletedSessions() {
        return sessions;
    }
}
