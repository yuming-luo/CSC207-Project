package view;

import app.ContentPane;
import interface_adapter.ViewManagerModel;

import interface_adapter.ViewManagerModelListener;

import javax.swing.*;
import java.awt.*;


/**
 * The View Manager for the content. It listens for property change events
 * in the ViewManagerModel and updates which View should be visible.
 */
public class ViewManager implements ViewManagerModelListener {
    private final ContentPane content;
    private final ViewManagerModel viewManagerModel;

    public ViewManager(ContentPane content, ViewManagerModel viewManagerModel) {
        this.content = content;
        this.viewManagerModel = viewManagerModel;
        viewManagerModel.addListener(this);
    }

    @Override
    public void viewChanged() {
        content.showView(viewManagerModel.getState());
    }
}