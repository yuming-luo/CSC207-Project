
package interface_adapter.focus_session;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class FocusSessionViewModel {

    private FocusSessionState state = new FocusSessionState();

    private final PropertyChangeSupport support =
            new PropertyChangeSupport(this);

    public FocusSessionState getState() {
        return this.state;
    }

    public void setState(FocusSessionState state) {
        this.state = state;
    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}

