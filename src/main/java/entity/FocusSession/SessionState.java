package entity.FocusSession;

// Describes the state of both the count_down and count_up study sessions
// COMPLETED = user successfully reaches a predefined study goal (count_down timer reaches 0)
// ENDED = user manually ends study session, or pause timer runs out
public enum SessionState {
    NOT_STARTED,
    RUNNING,
    PAUSED,
    COMPLETED,
    ENDED
}
