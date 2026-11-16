
package view;

import data_access.InMemoryStudyGoalDataAccessObject;
import interface_adapter.set_study_goal.SetStudyGoalController;
import interface_adapter.set_study_goal.SetStudyGoalPresenter;
import interface_adapter.set_study_goal.SetStudyGoalViewModel;
import use_case.set_study_goal.SetStudyGoalInteractor;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Temporary launcher for testing the Set Study Goal feature.
 */
public class SetStudyGoalDemo {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create the data access object.
            InMemoryStudyGoalDataAccessObject dao =
                    new InMemoryStudyGoalDataAccessObject();

            // Create the ViewModel and Presenter.
            SetStudyGoalViewModel viewModel =
                    new SetStudyGoalViewModel();
            SetStudyGoalPresenter presenter =
                    new SetStudyGoalPresenter(viewModel);

            // Create the Interactor and Controller.
            SetStudyGoalInteractor interactor =
                    new SetStudyGoalInteractor(dao, presenter);
            SetStudyGoalController controller =
                    new SetStudyGoalController(interactor);

            // Create the Swing View.
            SetStudyGoalView view =
                    new SetStudyGoalView(controller, viewModel);

            // Open the window.
            JFrame frame = new JFrame("Study Goal Management");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(view);
            frame.setSize(500, 200);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
