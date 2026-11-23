package use_case.focus_session;

import entity.FocusSession.FocusSession;

public class FocusSessionInteractor implements FocusSessionInputBoundary{

    private FocusSession currentSession;
    // This is a private attribute belonging to the implementor
    private final FocusSessionOutputBoundary outputBoundary;
    private String selectedDrink;

    public FocusSessionInteractor(FocusSessionOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }


    @Override
    public void start(FocusSessionInputData inputData) {
        this.currentSession = new FocusSession(
                inputData.getDuration(),
                inputData.getTimerMode()
        );
        this.selectedDrink = inputData.getDrink();
        this.currentSession.start();

        updateOutput();

    }

    @Override
    public void pause() {
        if (this.currentSession != null) {
            this.currentSession.pause();
        }
        updateOutput();
    }

    @Override
    public void resume() {
        if (this.currentSession != null) {
            this.currentSession.resume();
        }
        updateOutput();
    }

    @Override
    public void end() {
        if (this.currentSession != null) {
            this.currentSession.checkPauseTimeout();
            this.currentSession.end();
        }
        updateOutput();
    }

    @Override
    public void updateElapsedTime(int secondsPassed) {
        if (this.currentSession != null) {
            this.currentSession.updateElapsedTime(secondsPassed);
        }
        updateOutput();
    }

    @Override
    public void checkPauseTimeout() {
        if (this.currentSession != null) {
            this.currentSession.checkPauseTimeout();
        }
        updateOutput();
    }

    private void updateOutput() {
        FocusSessionOutputData outputData = new FocusSessionOutputData(
                this.currentSession.getState(),
                this.currentSession.getTimerType(),
                this.currentSession.getElapsedSeconds(),
                this.currentSession.getRemainingSeconds(),
                this.currentSession.getRemainingPausedSeconds(),
                this.currentSession.getSessionEndReason(),
                this.selectedDrink
        );
    }
}
