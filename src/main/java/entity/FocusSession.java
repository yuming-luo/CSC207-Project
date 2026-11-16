package entity;

public class FocusSession {
    private int duration;
    private boolean active;

    // constructor (same name as class)
    public FocusSession(int duration) {
        this.duration = duration;
        this.active = false;
    }

    // methods
    public void start() {
        this.active = true
    }

}
