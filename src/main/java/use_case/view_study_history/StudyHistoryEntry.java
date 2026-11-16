package use_case.view_study_history;

import java.time.LocalDateTime;

public class StudyHistoryEntry {
    private final LocalDateTime startTime;
    private final int durationMinutes;
    private final String drink;

    public StudyHistoryEntry(LocalDateTime startTime, int durationMinutes, String drink) {
        this.startTime = startTime;
        this.durationMinutes = durationMinutes;
        this.drink = drink;
    }

    public LocalDateTime getStartTime () {
        return startTime;
    }
    public int getDurationMinutes() {
        return durationMinutes;
    }
    public String getDrink() {
        return drink;
    }

}
