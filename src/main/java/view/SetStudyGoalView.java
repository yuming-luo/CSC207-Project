
package view;

import entity.GoalType;
import interface_adapter.set_study_goal.SetStudyGoalController;
import interface_adapter.set_study_goal.SetStudyGoalState;
import interface_adapter.set_study_goal.SetStudyGoalViewModel;

import java.awt.FlowLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * View for setting daily or weekly study goals.
 */
public class SetStudyGoalView extends JPanel
        implements PropertyChangeListener {

    private final SetStudyGoalController controller;
    private final SetStudyGoalViewModel viewModel;

    private final JComboBox<GoalType> goalTypeBox =
            new JComboBox<>(GoalType.values());

    private final JTextField minutesField = new JTextField(8);
    private final JLabel messageLabel = new JLabel(" ");

    public SetStudyGoalView(SetStudyGoalController controller,
                            SetStudyGoalViewModel viewModel) {
        this.controller = controller;
        this.viewModel = viewModel;

        setLayout(new FlowLayout());

        add(new JLabel("Goal type:"));
        add(goalTypeBox);

        add(new JLabel("Target minutes:"));
        add(minutesField);

        JButton saveButton = new JButton("Set Goal");
        add(saveButton);
        add(messageLabel);

        saveButton.addActionListener(event -> {
            try {
                int minutes = Integer.parseInt(
                        minutesField.getText().trim());

                GoalType goalType =
                        (GoalType) goalTypeBox.getSelectedItem();

                controller.execute(goalType, minutes);
            }
            catch (NumberFormatException error) {
                messageLabel.setText("Please enter numbers.");
            }
        });

        viewModel.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        SetStudyGoalState state = viewModel.getState();

        if (!state.getError().isEmpty()) {
            messageLabel.setText(state.getError());
        }
        else {
            messageLabel.setText(state.getMessage());
        }
    }
}
