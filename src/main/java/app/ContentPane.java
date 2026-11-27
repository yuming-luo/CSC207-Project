package app;

import data_access.InMemoryStudyGoalDataAccessObject;
import data_access.InMemoryStudySessionDataAccessObject;
import interface_adapter.delete_study_goal.DeleteStudyGoalController;
import interface_adapter.delete_study_goal.DeleteStudyGoalPresenter;
import interface_adapter.set_study_goal.SetStudyGoalController;
import interface_adapter.set_study_goal.SetStudyGoalPresenter;
import interface_adapter.set_study_goal.SetStudyGoalViewModel;
import interface_adapter.view_study_history.ViewStudyHistoryController;
import interface_adapter.view_study_history.ViewStudyHistoryPresenter;
import interface_adapter.view_study_history.ViewStudyHistoryViewModel;
import javafx.embed.swing.SwingNode;
import javafx.scene.layout.StackPane;
import use_case.delete_study_goal.DeleteStudyGoalInteractor;
import use_case.set_study_goal.SetStudyGoalInteractor;
import use_case.set_study_goal.SetStudyGoalOutputBoundary;
import use_case.view_study_history.*;
import view.SetStudyGoalView;
import view.ViewStudyHistoryPanel;

import java.time.LocalDateTime;

public class ContentPane extends StackPane {
    public void setStudyGoalView() {
        this.getChildren().clear();
        final InMemoryStudyGoalDataAccessObject studyGoalDataAccessObject = new InMemoryStudyGoalDataAccessObject();
        SetStudyGoalViewModel studyGoalViewModel = new SetStudyGoalViewModel();

        // Set Study Goal components.
        SetStudyGoalOutputBoundary presenter = new SetStudyGoalPresenter(studyGoalViewModel);
        SetStudyGoalInteractor interactor = new SetStudyGoalInteractor(studyGoalDataAccessObject, presenter);
        SetStudyGoalController controller = new SetStudyGoalController(interactor);

        // Delete Study Goal components.
        DeleteStudyGoalPresenter deletePresenter = new DeleteStudyGoalPresenter(studyGoalViewModel);
        DeleteStudyGoalInteractor deleteInteractor = new DeleteStudyGoalInteractor(studyGoalDataAccessObject, deletePresenter);
        DeleteStudyGoalController deleteController = new DeleteStudyGoalController(deleteInteractor);

        // Create the Swing View with both Controllers.
        SetStudyGoalView studyGoalView = new SetStudyGoalView(controller, deleteController, studyGoalViewModel);

        SwingNode node = new SwingNode();
        node.setContent(studyGoalView);
        this.getChildren().addAll(node);
    }

    public void setStudyHistoryView() {
        this.getChildren().clear();

        StudySessionDataAccessInterface dataAccess = new InMemoryStudySessionDataAccessObject();

        // Add a sample session to test the DAO
        StudyHistoryEntry testSession = new StudyHistoryEntry(
                LocalDateTime.now(),
                50,
                "Coffee"
        );

        dataAccess.addSession(testSession);

        ViewStudyHistoryViewModel studyHistoryViewModel = new ViewStudyHistoryViewModel();
        ViewStudyHistoryOutputBoundary presenter = new ViewStudyHistoryPresenter(studyHistoryViewModel);
        ViewStudyHistoryInputBoundary interactor = new ViewStudyHistoryInteractor(dataAccess, presenter);
        ViewStudyHistoryController controller = new ViewStudyHistoryController(interactor);

        ViewStudyHistoryPanel studyHistoryView = new ViewStudyHistoryPanel(studyHistoryViewModel, controller);

        SwingNode node = new SwingNode();
        node.setContent(studyHistoryView);
        this.getChildren().addAll(node);
    }
}
