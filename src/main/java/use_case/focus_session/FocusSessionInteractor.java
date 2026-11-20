package use_case.focus_session;

import entity.FocusSession.FocusSession;

public class FocusSessionInteractor implements FocusSessionInputBoundary{

    private FocusSession currentSession;
    // This is a private attribute belonging to the implementor

    @Override
    public void start(FocusSessionInputData inputData) {
        this.currentSession = new FocusSession(
                inputData.getDuration(),
                inputData.getTimerMode()
        );
        this.currentSession.start();

    }

    @Override
    public void pause() {
        if (this.currentSession != null) {
            this.currentSession.pause();
        }
    }

    @Override
    public void resume() {
        if (this.currentSession != null) {
            this.currentSession.resume();
        }
    }

    @Override
    public void end() {
        if (this.currentSession != null) {
            this.currentSession.checkPauseTimeout();
            this.currentSession.end();
        }
    }

    @Override
    public void updateElapsedTime(int secondsPassed) {
        if (this.currentSession != null) {
            this.currentSession.updateElapsedTime(secondsPassed);
        }
    }

    @Override
    public void checkPauseTimeout() {
        if (this.currentSession != null) {
            this.currentSession.checkPauseTimeout();
        }
    }
}
