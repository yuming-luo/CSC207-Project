package app;

import interface_adapter.delete_study_goal.DeleteStudyGoalController;
import interface_adapter.delete_study_goal.DeleteStudyGoalPresenter;
import interface_adapter.set_study_goal.SetStudyGoalController;
import interface_adapter.set_study_goal.SetStudyGoalPresenter;
import interface_adapter.set_study_goal.SetStudyGoalViewModel;
import interface_adapter.view_study_history.ViewStudyHistoryController;
import interface_adapter.view_study_history.ViewStudyHistoryPresenter;
import interface_adapter.view_study_history.ViewStudyHistoryViewModel;
import javafx.embed.swing.SwingNode;
import javafx.scene.Node;
import use_case.delete_study_goal.DeleteStudyGoalDataAccessInterface;
import use_case.delete_study_goal.DeleteStudyGoalInteractor;
import use_case.set_study_goal.SetStudyGoalDataAccessInterface;
import use_case.set_study_goal.SetStudyGoalInteractor;
import use_case.set_study_goal.SetStudyGoalOutputBoundary;
import use_case.view_study_history.*;
import view.NavItem;
import view.SetStudyGoalView;
import view.ViewStudyHistoryPanel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ContentBuilder {
    private final SetStudyGoalDataAccessInterface studyGoalDao;
    private final StudySessionDataAccessInterface studySessionDao;


    private final ContentPane content;
    private final ArrayList<NavItem> navItems = new ArrayList<>();

    public ContentBuilder(ContentPane content,
                      SetStudyGoalDataAccessInterface studyGoalDao,
                      StudySessionDataAccessInterface sessionDao) {
        this.content = content;
        this.studyGoalDao = studyGoalDao;
        this.studySessionDao = sessionDao;
    }

    public ArrayList<NavItem> getNavItems() {
        return navItems;
    }

    private void register(String label, String viewName, Node node) {
        content.addView(viewName, node);
        navItems.add(new NavItem(label, viewName));
    }

    private Node wrapSwing(JComponent view) {
        SwingNode node = new SwingNode();
        SwingUtilities.invokeLater(() -> node.setContent(view));
        return node;
    }

    public ContentBuilder addStudyGoalView() {
        SetStudyGoalViewModel studyGoalViewModel = new SetStudyGoalViewModel();
        SetStudyGoalOutputBoundary presenter = new SetStudyGoalPresenter(studyGoalViewModel);
        SetStudyGoalInteractor interactor = new SetStudyGoalInteractor(studyGoalDao, presenter);
        SetStudyGoalController controller = new SetStudyGoalController(interactor);

        // Delete Study Goal components.
        DeleteStudyGoalPresenter deletePresenter = new DeleteStudyGoalPresenter(studyGoalViewModel);
        DeleteStudyGoalInteractor deleteInteractor = new DeleteStudyGoalInteractor((DeleteStudyGoalDataAccessInterface) studyGoalDao, deletePresenter);
        DeleteStudyGoalController deleteController = new DeleteStudyGoalController(deleteInteractor);

        SetStudyGoalView view = new SetStudyGoalView(controller, deleteController, studyGoalViewModel);

        register("Study Goal", studyGoalViewModel.VIEW_NAME, wrapSwing(view));
        return this;
    }

    public ContentBuilder addStudyHistoryView() {
        // Add a sample session to test the DAO
        StudyHistoryEntry testSession = new StudyHistoryEntry(
                LocalDateTime.now(),
                50,
                "Coffee"
        );

        studySessionDao.addSession(testSession);

        ViewStudyHistoryViewModel studyHistoryViewModel = new ViewStudyHistoryViewModel();
        ViewStudyHistoryOutputBoundary presenter = new ViewStudyHistoryPresenter(studyHistoryViewModel);
        ViewStudyHistoryInputBoundary interactor = new ViewStudyHistoryInteractor(studySessionDao, presenter);
        ViewStudyHistoryController controller = new ViewStudyHistoryController(interactor);

        ViewStudyHistoryPanel view = new ViewStudyHistoryPanel(studyHistoryViewModel, controller);

        register("Study History", studyHistoryViewModel.VIEW_NAME, wrapSwing(view));
        return this;
    }
}
