package view;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.time.LocalDateTime;
import use_case.view_study_history.StudyHistoryEntry;

import data_access.InMemoryStudySessionDataAccessObject;
import interface_adapter.view_study_history.ViewStudyHistoryController;
import interface_adapter.view_study_history.ViewStudyHistoryPresenter;
import interface_adapter.view_study_history.ViewStudyHistoryViewModel;
import use_case.view_study_history.StudySessionDataAccessInterface;
import use_case.view_study_history.ViewStudyHistoryInputBoundary;
import use_case.view_study_history.ViewStudyHistoryOutputBoundary;
import use_case.view_study_history.ViewStudyHistoryInteractor;

public class ViewStudyHistoryDemo {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            // Create DAO
            StudySessionDataAccessInterface dataAccess = new InMemoryStudySessionDataAccessObject();

            // Add a sample session to test the DAO
            StudyHistoryEntry testSession = new StudyHistoryEntry(
                    LocalDateTime.now(),
                    50,
                    "Coffee"
            );

            dataAccess.addSession(testSession);

            // Create ViewModel
            ViewStudyHistoryViewModel viewModel = new ViewStudyHistoryViewModel();

            // Create Presenter
            ViewStudyHistoryOutputBoundary presenter = new ViewStudyHistoryPresenter(viewModel);

            // Create Interactor
            ViewStudyHistoryInputBoundary interactor = new ViewStudyHistoryInteractor(dataAccess, presenter);

            // Create Controller
            ViewStudyHistoryController controller = new ViewStudyHistoryController(interactor);

            // Create View
            ViewStudyHistoryPanel panel = new ViewStudyHistoryPanel(viewModel, controller);

            // Create window
            JFrame frame = new JFrame("Study Nook - Study History");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setContentPane(panel);
            frame.setSize(750, 450);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            // Load study history
            controller.execute();
        });
    }
}
