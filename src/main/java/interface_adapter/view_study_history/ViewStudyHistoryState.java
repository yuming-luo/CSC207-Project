package interface_adapter.view_study_history;

import java.util.List;
import java.util.ArrayList;
import use_case.view_study_history.StudyHistoryEntry;

public class ViewStudyHistoryState {

    private List<StudyHistoryEntry> entries = new ArrayList<>();
    private int totalMinutes = 0;

    public List<StudyHistoryEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<StudyHistoryEntry> entries) {
        this.entries = entries;
    }

    public int getTotalMinutes() {
        return totalMinutes;
    }

    public void setTotalMinutes(int totalMinutes) {
        this.totalMinutes = totalMinutes;
    }
}
