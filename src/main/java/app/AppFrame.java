package app;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import interface_adapter.ViewManagerModel;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import view.NavItem;

public class AppFrame extends BorderPane {

    public static final String LOGOUT_CLICKED = "logoutClicked";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private final Map<String, Button> navButtons = new LinkedHashMap<>();

    public AppFrame(ContentPane content, ViewManagerModel viewManagerModel, List<NavItem> items) {
        content.getStyleClass().add("content");

        setLeft(buildSidebar(viewManagerModel, items));
        setCenter(content);

        viewManagerModel.addListener(() -> select(viewManagerModel.getState()));
    }

    private VBox buildSidebar(ViewManagerModel model, List<NavItem> items) {
        VBox sidebar = new VBox();
        sidebar.getStyleClass().add("sidebar");

        for (NavItem item : items) {
            addNavButton(item, model);
        }
        sidebar.getChildren().addAll(navButtons.values());

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        Button logOut = new Button("Log Out");
        logOut.getStyleClass().add("sidebar-button");
        logOut.setOnAction(e -> support.firePropertyChange(LOGOUT_CLICKED, null, true));

        sidebar.getChildren().addAll(spacer, logOut);
        return sidebar;
    }

    private void addNavButton(NavItem item, ViewManagerModel model) {
        Button button = new Button(item.label());
        button.getStyleClass().add("sidebar-button");
        button.setOnAction(e -> {
            model.setState(item.viewName());
            model.notifyViewChanged();
        });
        navButtons.put(item.viewName(), button);
    }

    private void select(String viewName) {
        navButtons.forEach((name, button) -> {
            button.getStyleClass().remove("selected");
            if (name.equals(viewName)) {
                button.getStyleClass().add("selected");
            }
        });
    }

    public void addLogoutListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}