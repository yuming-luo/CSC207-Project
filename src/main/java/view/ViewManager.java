package view;

import app.ContentPane;
import interface_adapter.ViewManagerModel;

import interface_adapter.ViewManagerModelListener;

import javax.swing.*;
import java.awt.*;


/**
 * The View Manager for the program. It listens for property change events
 * in the ViewManagerModel and updates which View should be visible.
 */
public class ViewManager implements ViewManagerModelListener {
    private ContentPane views;

    public ViewManager(ContentPane views, ViewManagerModel viewManagerModel) {
        this.views = views;
        viewManagerModel.addListener(this);
    }

    @Override
    public void viewChanged(String viewName) {
        System.out.println("View Changed");
        System.out.println(viewName);
        switch (viewName) {
            case "study goal":
                System.out.println("entered");
                views.setStudyGoalView();
                break;
            case "study history":
                views.setStudyHistoryView();
                break;
            default:
                System.out.println("Content not found");
        }
    }
}