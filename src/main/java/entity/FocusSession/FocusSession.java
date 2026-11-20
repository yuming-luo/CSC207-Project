package entity.FocusSession;

import java.time.Instant;
import java.time.Duration;

public class FocusSession {
    private int duration; // If timerType == COUNT_UP set duration = 0
    private int elapsedSeconds;
    private int remainingSeconds; // If timerType == COUNT_UP set remainingSeconds = 0
    private SessionState state;
    private Instant pausedAt;
    private TimerMode timerType;
    private SessionEndReason sessionEndReason;

    private static final int MAX_PAUSE_SECONDS = 15 * 60;

    // constructor (same name as class)
    public FocusSession(int duration, TimerMode timerType) {
        if (timerType == TimerMode.COUNT_DOWN && duration <= 0) {
            throw new IllegalArgumentException(
                    "Duration must be positive");
        }
        if (timerType == TimerMode.COUNT_UP && duration != 0) {
            throw new IllegalArgumentException(
                    "Count-up does not need a duration");
        }
        if (timerType == null) {
            throw new IllegalArgumentException(
                    "Timer mode is required");
        }

        this.duration = duration;
        this.elapsedSeconds = 0;
        this.remainingSeconds = duration;
        this.state = SessionState.NOT_STARTED;
        this.pausedAt = null;
        this.timerType = timerType;
        this.sessionEndReason = null;
    }
    // getters
    public int getDuration() {
        return this.duration;
    }

    public int getElapsedSeconds() {return this.elapsedSeconds;}

    public SessionState getState() {
        return this.state;
    }

    public TimerMode getTimerType() { return this.timerType;}

    public long getRemainingPausedSeconds() {
        if (this.state != SessionState.PAUSED || this.pausedAt == null) {
            return 0;
        }

        long secondsPaused =
                Duration.between(this.pausedAt, Instant.now()).getSeconds();
        return Math.max(0, MAX_PAUSE_SECONDS - secondsPaused);
    }

    // methods
    public void start() {
        if (this.state == SessionState.NOT_STARTED) {
            this.state = SessionState.RUNNING;
        }
        // If the SessionState is currently NOT_STARTED and the start method is called,
        // change SessionState to RUNNING to indicated study session has started
    }

    public void pause() {
        if (this.state == SessionState.RUNNING) {
            this.state = SessionState.PAUSED;
            this.pausedAt = Instant.now();
        }
    }

    public void resume() {
        if (this.state == SessionState.PAUSED) {
            checkPauseTimeout();
            // if it is still paused after checkPauseTimeout() (because this method will change session state if timedout)
            if (this.state == SessionState.PAUSED) {
                this.state = SessionState.RUNNING;
                this.pausedAt = null;
            }
        }
    }

    public void end() {
        if (this.state == SessionState.RUNNING || this.state == SessionState.PAUSED) {
            this.state = SessionState.ENDED;
            this.sessionEndReason = SessionEndReason.MANUAL;
            this.pausedAt = null;
        }
    }


    public void complete() {
        if (this.timerType == TimerMode.COUNT_DOWN && this.state == SessionState.RUNNING && this.remainingSeconds == 0) {
            this.state = SessionState.COMPLETED;
            this.sessionEndReason = SessionEndReason.TIMER_FINISHED;
        }
    }

    public void checkPauseTimeout() {
        if (this.state == SessionState.PAUSED && this.pausedAt != null) {
            long secondsPaused =
                    Duration.between(this.pausedAt, Instant.now()).getSeconds();

            if (secondsPaused >= MAX_PAUSE_SECONDS) {
                this.state = SessionState.ENDED;
                this.sessionEndReason = SessionEndReason.PAUSE_TIMEOUT;
                this.pausedAt = null;
            }
        }
    }

}
