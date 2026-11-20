package view;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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

    // Empty history message
    private final JLabel emptyHistoryLabel =
            new JLabel("No study sessions found.", SwingConstants.CENTER);

    // Date range filter input fields
    private final JTextField startDateField = new JTextField(10);
    private final JTextField endDateField = new JTextField(10);

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

        // Create refresh button
        JButton refreshButton = new JButton("Refresh History");
        refreshButton.addActionListener(e -> controller.execute());

        // Create date range filter panel
        JPanel filterPanel = new JPanel();

        filterPanel.add(new JLabel("Start Date (yyyy-MM-dd):"));
        filterPanel.add(startDateField);

        filterPanel.add(new JLabel("End Date (yyyy-MM-dd):"));
        filterPanel.add(endDateField);

        // Create filter button
        JButton filterButton = new JButton("Filter History");

        filterButton.addActionListener(e -> {
            try {
                LocalDate startDate = LocalDate.parse(startDateField.getText().trim());
                LocalDate endDate = LocalDate.parse(endDateField.getText().trim());

                if (startDate.isAfter(endDate)) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Start date must not be after end date.",
                            "Invalid Date Range",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                controller.execute(startDate, endDate);

            }
            catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter dates in yyyy-MM-dd format.",
                        "Invalid Date",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        filterPanel.add(filterButton);

        // Arrange components
        topPanel.add(statsPanel, BorderLayout.CENTER);
        topPanel.add(refreshButton, BorderLayout.EAST);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.add(topPanel, BorderLayout.NORTH);
        headerPanel.add(filterPanel, BorderLayout.SOUTH);

        add(headerPanel, BorderLayout.NORTH);

        // Create history table and empty state message
        JTable table = new JTable(tableModel);

        JPanel historyPanel = new JPanel(new BorderLayout());
        historyPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        historyPanel.add(emptyHistoryLabel, BorderLayout.SOUTH);

        add(historyPanel, BorderLayout.CENTER);

        viewModel.addPropertyChangeListener(this);

        updateView();
    }

    private void updateView() {
        ViewStudyHistoryState state = viewModel.getState();

        sessionCountLabel.setText("Sessions: " + state.getEntries().size());

        totalMinutesLabel.setText("Total Study Time: " + state.getTotalMinutes() + " minutes");

        tableModel.setRowCount(0);

        // Show empty history message when no sessions are found
        emptyHistoryLabel.setVisible(state.getEntries().isEmpty());

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
