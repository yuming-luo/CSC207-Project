package interface_adapter.view_study_history;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewStudyHistoryViewModel {

    public static final String VIEW_NAME = "study history";

    private ViewStudyHistoryState state = new ViewStudyHistoryState();

    private final PropertyChangeSupport support =
            new PropertyChangeSupport(this);

    public ViewStudyHistoryState getState() {
        return state;
    }

    public void setState(ViewStudyHistoryState state) {
        this.state = state;
    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(
            PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
