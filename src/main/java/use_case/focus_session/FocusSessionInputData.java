package use_case.focus_session;

import entity.FocusSession.TimerMode;

public class FocusSessionInputData {

    private final int duration;
    private final TimerMode timerMode;
    private final String drink;

    public FocusSessionInputData(int duration,
                                 TimerMode timerMode,
                                 String drink) {
        this.duration = duration;
        this.timerMode = timerMode;
        this.drink = drink;
    }

    public int getDuration() {
        return this.duration;
    }

    public TimerMode getTimerMode() {
        return this.timerMode;
    }

    public String getDrink() {
        return this.drink;
    }
}
