
package interface_adapter.focus_session;

import entity.FocusSession.TimerMode;
import use_case.focus_session.FocusSessionInputBoundary;
import use_case.focus_session.FocusSessionInputData;

public class FocusSessionController {

    private final FocusSessionInputBoundary interactor;

    public FocusSessionController(FocusSessionInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void start(int duration, TimerMode timerMode, String drink) {
        FocusSessionInputData inputData =
                new FocusSessionInputData(duration, timerMode, drink);

        interactor.start(inputData);
    }

    public void pause() {
        interactor.pause();
    }

    public void resume() {
        interactor.resume();
    }

    public void end() {
        interactor.end();
    }

    public void updateElapsedTime(int secondsPassed) {
        interactor.updateElapsedTime(secondsPassed);
    }

    public void checkPauseTimeout() {
        interactor.checkPauseTimeout();
    }
}
