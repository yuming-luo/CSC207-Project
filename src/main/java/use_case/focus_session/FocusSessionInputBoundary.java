package use_case.focus_session;

public interface FocusSessionInputBoundary {

    void start(FocusSessionInputData inputData);

    void pause();

    void resume();

    void end();

    void updateElapsedTime(int secondsPassed);

    void checkPauseTimeout();
}
