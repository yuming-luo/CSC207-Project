package use_case.focus_session;

import entity.FocusSession.SessionState;
import entity.FocusSession.TimerMode;
import entity.FocusSession.SessionEndReason;

public class FocusSessionOutputData {

    private final SessionState state;
    private final TimerMode timerMode;
    private final int elapsedSeconds;
    private final int remainingSeconds;
    private final long remainingPausedSeconds;
    private final SessionEndReason sessionEndReason;
    private final String drink;

    public FocusSessionOutputData(
            SessionState state,
            TimerMode timerMode,
            int elapsedSeconds,
            int remainingSeconds,
            long remainingPausedSeconds,
            SessionEndReason sessionEndReason,
            String drink) {

        this.state = state;
        this.timerMode = timerMode;
        this.elapsedSeconds = elapsedSeconds;
        this.remainingSeconds = remainingSeconds;
        this.remainingPausedSeconds = remainingPausedSeconds;
        this.sessionEndReason = sessionEndReason;
        this.drink = drink;
    }

    public SessionState getState() {
        return state;
    }

    public TimerMode getTimerMode() {
        return timerMode;
    }

    public int getElapsedSeconds() {
        return elapsedSeconds;
    }

    public int getRemainingSeconds() {
        return remainingSeconds;
    }

    public long getRemainingPausedSeconds() {
        return remainingPausedSeconds;
    }

    public SessionEndReason getSessionEndReason() {
        return sessionEndReason;
    }

    public String getDrink() {
        return drink;
    }
}
