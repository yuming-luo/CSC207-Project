package entity.FocusSession;

// Describes the reason that the session ended/ completed
// PAUSE_TIMEOUT is the session end reason when the user pauses the session and the pause timer of 15 minutes reaches 0.
public enum SessionEndReason {
    MANUAL,
    PAUSE_TIMEOUT,
    TIMER_FINISHED
}
