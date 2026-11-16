
package interface_adapter.set_study_goal;

/**
 * Stores the information displayed by the Set Study Goal view.
 */
public class SetStudyGoalState {

    private String message = "";
    private String error = "";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
