package use_case.view_study_history;

import java.util.List;

public interface StudySessionDataAccessInterface {
    List<StudyHistoryEntry> getCompletedSessions();
    void addSession(StudyHistoryEntry session);
}
