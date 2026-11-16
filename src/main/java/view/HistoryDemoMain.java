package view;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import data_access.InMemoryStudySessionDataAccessObject;
import interface_adapter.view_study_history.ViewStudyHistoryController;
import interface_adapter.view_study_history.ViewStudyHistoryPresenter;
import interface_adapter.view_study_history.ViewStudyHistoryViewModel;
import use_case.view_study_history.StudySessionDataAccessInterface;
import use_case.view_study_history.ViewStudyHistoryInputBoundary;
import use_case.view_study_history.ViewStudyHistoryOutputBoundary;
import use_case.view_study_history.ViewStudyHistoryInteractor;

public class HistoryDemoMain {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            // Step 1: Create DAO
            StudySessionDataAccessInterface dataAccess = new InMemoryStudySessionDataAccessObject();

            // Step 2: Create ViewModel
            ViewStudyHistoryViewModel viewModel = new ViewStudyHistoryViewModel();

            // Step 3: Create Presenter
            ViewStudyHistoryOutputBoundary presenter = new ViewStudyHistoryPresenter(viewModel);

            // Step 4: Create Interactor
            ViewStudyHistoryInputBoundary interactor = new ViewStudyHistoryInteractor(dataAccess, presenter);

            // Step 5: Create Controller
            ViewStudyHistoryController controller = new ViewStudyHistoryController(interactor);

            // Step 6: Create View
            ViewStudyHistoryPanel panel = new ViewStudyHistoryPanel(viewModel, controller);

            // Step 7: Create window
            JFrame frame = new JFrame("Study Nook - Study History");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setContentPane(panel);
            frame.setSize(750, 450);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            // Step 8: Load study history
            controller.execute();
        });
    }
}
