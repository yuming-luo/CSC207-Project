package entity;

import java.time.Instant;
import java.time.Duration;

public class FocusSession {
    private int duration;
    private int remainingSeconds;
    private SessionState state; //SessionState is an enum to be implemented
    private Instant pausedAt;

    private static final int MAX_PAUSE_SECONDS = 15 * 60;

    // constructor (same name as class)
    public FocusSession(int duration) {
        if (duration <=0) {
            throw new IllegalArgumentException(
                    "Duration must be positive"
            );
        }
        this.duration = duration;
        this.remainingSeconds = duration;
        this.state = SessionState.NOT_STARTED;
        this.pausedAt = null;
    }
    // getters
    public int getDuration() {
        return this.duration;
    }

    public int getRemainingSeconds() {
        return this.remainingSeconds;
    }

    public SessionState getState() {
        return this.state;
    }


    // methods
    public void start() {
        if (this.state == SessionState.NOT_STARTED) {
            this.state = SessionState.RUNNING;
        }
        // If the SessionState is currently NOT_STARTED and the start method is called,
        // change SessionState to RUNNING to indicated study session has started
    }

}
