package view;

import data_access.InMemoryStudyGoalDataAccessObject;
import interface_adapter.delete_study_goal.DeleteStudyGoalController;
import interface_adapter.delete_study_goal.DeleteStudyGoalPresenter;
import interface_adapter.set_study_goal.SetStudyGoalController;
import interface_adapter.set_study_goal.SetStudyGoalPresenter;
import interface_adapter.set_study_goal.SetStudyGoalViewModel;
import use_case.delete_study_goal.DeleteStudyGoalInteractor;
import use_case.set_study_goal.SetStudyGoalInteractor;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Temporary launcher for testing the Study Goal features.
 */
public class SetStudyGoalDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create one shared DAO.
            InMemoryStudyGoalDataAccessObject dao =
                    new InMemoryStudyGoalDataAccessObject();

            // Create one shared View Model.
            SetStudyGoalViewModel viewModel =
                    new SetStudyGoalViewModel();

            // Set Study Goal components.
            SetStudyGoalPresenter presenter =
                    new SetStudyGoalPresenter(viewModel);

            SetStudyGoalInteractor interactor =
                    new SetStudyGoalInteractor(dao, presenter);

            SetStudyGoalController controller =
                    new SetStudyGoalController(interactor);

            // Delete Study Goal components.
            DeleteStudyGoalPresenter deletePresenter =
                    new DeleteStudyGoalPresenter(viewModel);

            DeleteStudyGoalInteractor deleteInteractor =
                    new DeleteStudyGoalInteractor(dao, deletePresenter);

            DeleteStudyGoalController deleteController =
                    new DeleteStudyGoalController(deleteInteractor);

            // Create the Swing View with both Controllers.
            SetStudyGoalView view =
                    new SetStudyGoalView(
                            controller, deleteController, viewModel);

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
