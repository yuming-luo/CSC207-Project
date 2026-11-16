package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import interface_adapter.view_study_history.ViewStudyHistoryController;
import interface_adapter.view_study_history.ViewStudyHistoryState;
import interface_adapter.view_study_history.ViewStudyHistoryViewModel;
import use_case.view_study_history.StudyHistoryEntry;

public class ViewStudyHistoryPanel extends JPanel
        implements PropertyChangeListener {

    private final ViewStudyHistoryViewModel viewModel;
    private final ViewStudyHistoryController controller;

    private final JLabel sessionCountLabel = new JLabel();
    private final JLabel totalMinutesLabel = new JLabel();

    private final DefaultTableModel tableModel =
            new DefaultTableModel(
                    new String[]{"Date", "Drink", "Duration (min)"}, 0);

    public ViewStudyHistoryPanel(
            ViewStudyHistoryViewModel viewModel,
            ViewStudyHistoryController controller) {

        this.viewModel = viewModel;
        this.controller = controller;

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel statsPanel = new JPanel(new GridLayout(2, 1));

        statsPanel.add(sessionCountLabel);
        statsPanel.add(totalMinutesLabel);

        JButton refreshButton = new JButton("Refresh History");
        refreshButton.addActionListener(e -> controller.execute());

        topPanel.add(statsPanel, BorderLayout.CENTER);
        topPanel.add(refreshButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        JTable table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        viewModel.addPropertyChangeListener(this);

        updateView();
    }

    private void updateView() {
        ViewStudyHistoryState state = viewModel.getState();

        sessionCountLabel.setText("Sessions: " + state.getEntries().size());

        totalMinutesLabel.setText("Total Study Time: " + state.getTotalMinutes() + " minutes");

        tableModel.setRowCount(0);

        for (StudyHistoryEntry entry : state.getEntries()) {
            tableModel.addRow(new Object[]{
                    entry.getStartTime(),
                    entry.getDrink(),
                    entry.getDurationMinutes()
            });
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if ("state".equals(event.getPropertyName())) {
            updateView();
        }
    }

    public String getViewName() {
        return ViewStudyHistoryViewModel.VIEW_NAME;
    }
}
