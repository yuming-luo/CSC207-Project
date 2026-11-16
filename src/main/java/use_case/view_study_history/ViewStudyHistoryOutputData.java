package use_case.view_study_history;

import java.util.List;

public class ViewStudyHistoryOutputData {
    private final List<StudyHistoryEntry> entries;
    private final int totalMinutes;

    public ViewStudyHistoryOutputData(List<StudyHistoryEntry> entries, int totalMinutes) {
        this.entries = entries;
        this.totalMinutes = totalMinutes;
    }
    public List<StudyHistoryEntry> getEntries() {
        return entries;
    }
    public int getSessionCount() {
        return entries.size();
    }
    public int getTotalMinutes() {
        return totalMinutes;
    }
}
