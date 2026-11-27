package interface_adapter;

public interface ViewManagerModelListener {

    /**
     * Update which view is currently being displayed.
     * @param currentViewName is the name of the current view name.
     */
    void viewChanged(String currentViewName);
}

