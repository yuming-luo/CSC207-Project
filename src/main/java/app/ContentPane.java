package app;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.util.HashMap;
import java.util.Map;

public class ContentPane extends StackPane {
    private final Map<String, Node> views = new HashMap<>();

    public void addView(String name, Node view) {
        if (name == null || view == null) {
            throw new IllegalArgumentException(
                    "addView needs a name and a view, got name=" + name + ", view=" + view);
        }
        views.put(name, view);
    }

    public void showView(String name) {
        final Node view = views.get(name);
        if (view == null) {
            throw new IllegalArgumentException(
                    "No view registered for '" + name + "'. Registered: " + views.keySet());
        }
        getChildren().setAll(view);
    }
}
