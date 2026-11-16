
package interface_adapter.set_study_goal;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * View model for the Set Study Goal use case.
 */
public class SetStudyGoalViewModel {

    private SetStudyGoalState state = new SetStudyGoalState();

    private final PropertyChangeSupport support =
            new PropertyChangeSupport(this);

    public SetStudyGoalState getState() {
        return state;
    }

    public void setState(SetStudyGoalState state) {
        this.state = state;
    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
