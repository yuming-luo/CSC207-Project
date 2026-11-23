package interface_adapter.focus_session;

import entity.FocusSession.SessionState;
import entity.FocusSession.TimerMode;
import entity.FocusSession.SessionEndReason;

public class FocusSessionState {

    private SessionState sessionState = SessionState.NOT_STARTED;
    private TimerMode timerMode;

    public void setSessionState(SessionState sessionState) {
        this.sessionState = sessionState;
    }

    public void setTimerMode(TimerMode timerMode) {
        this.timerMode = timerMode;
    }

    public void setElapsedSeconds(int elapsedSeconds) {
        this.elapsedSeconds = elapsedSeconds;
    }

    public void setRemainingSeconds(int remainingSeconds) {
        this.remainingSeconds = remainingSeconds;
    }

    public void setRemainingPausedSeconds(long remainingPausedSeconds) {
        this.remainingPausedSeconds = remainingPausedSeconds;
    }

    public void setSessionEndReason(SessionEndReason sessionEndReason) {
        this.sessionEndReason = sessionEndReason;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public void setError(String error) {
        this.error = error;
    }

    private int elapsedSeconds;
    private int remainingSeconds;
    private long remainingPausedSeconds;
    private SessionEndReason sessionEndReason;
    private String drink;
    private String error = "";

}