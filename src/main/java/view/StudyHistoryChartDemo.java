
package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class StudyHistoryChartDemo extends Application {

    private final List<StudyRecord> records = new ArrayList<>();

    private final ComboBox<String> periodFilter = new ComboBox<>();
    private final ComboBox<String> drinkFilter = new ComboBox<>();
    private final DatePicker startDate = new DatePicker();
    private final DatePicker endDate = new DatePicker();

    private BarChart<String, Number> chart;
    private Label summaryLabel;

    @Override
    public void start(Stage stage) {

        // Sample study sessions
        records.add(new StudyRecord(
                LocalDate.of(2022, 3, 15), 60, "Coffee"));
        records.add(new StudyRecord(
                LocalDate.of(2022, 7, 20), 90, "Tea"));
        records.add(new StudyRecord(
                LocalDate.of(2022, 11, 5), 45, "Water"));

        records.add(new StudyRecord(
                LocalDate.of(2023, 2, 10), 120, "Coffee"));
        records.add(new StudyRecord(
                LocalDate.of(2023, 6, 18), 75, "Tea"));
        records.add(new StudyRecord(
                LocalDate.of(2023, 12, 2), 100, "Coffee"));

        records.add(new StudyRecord(
                LocalDate.of(2024, 1, 12), 80, "Water"));
        records.add(new StudyRecord(
                LocalDate.of(2024, 5, 25), 150, "Coffee"));
        records.add(new StudyRecord(
                LocalDate.of(2024, 9, 8), 90, "Tea"));
        records.add(new StudyRecord(
                LocalDate.of(2024, 12, 15), 60, "Water"));

        records.add(new StudyRecord(
                LocalDate.of(2025, 3, 5), 120, "Coffee"));
        records.add(new StudyRecord(
                LocalDate.of(2025, 6, 14), 180, "Tea"));
        records.add(new StudyRecord(
                LocalDate.of(2025, 10, 20), 90, "Water"));
        records.add(new StudyRecord(
                LocalDate.of(2025, 12, 3), 150, "Coffee"));

        records.add(new StudyRecord(
                LocalDate.of(2026, 9, 10), 75, "Tea"));
        records.add(new StudyRecord(
                LocalDate.of(2026, 10, 15), 120, "Coffee"));
        records.add(new StudyRecord(
                LocalDate.of(2026, 11, 16), 45, "Coffee"));
        records.add(new StudyRecord(
                LocalDate.of(2026, 11, 16), 30, "Tea"));
        records.add(new StudyRecord(
                LocalDate.of(2026, 11, 17), 60, "Tea"));
        records.add(new StudyRecord(
                LocalDate.of(2026, 11, 18), 90, "Coffee"));
        records.add(new StudyRecord(
                LocalDate.of(2026, 11, 23), 30, "Water"));


        // Time grouping filter
        periodFilter.getItems().addAll(
                "Day", "Week", "Month", "Year");
        periodFilter.setValue("Day");

        // Drink filter
        drinkFilter.getItems().addAll(
                "All", "Coffee", "Tea", "Water");
        drinkFilter.setValue("All");

        // Date filters
        startDate.setPromptText("Start date");
        endDate.setPromptText("End date");

        HBox filters = new HBox(
                12,
                new Label("Group by:"), periodFilter,
                new Label("Drink:"), drinkFilter,
                new Label("From:"), startDate,
                new Label("To:"), endDate
        );

        filters.setAlignment(
                javafx.geometry.Pos.CENTER_LEFT);

        // Chart axes
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Time Period");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Study Minutes");
        yAxis.setForceZeroInRange(true);

        // Bar chart
        chart = new BarChart<>(xAxis, yAxis);
        chart.setTitle("Study History");
        chart.setLegendVisible(false);
        chart.setAnimated(false);

        // Summary text
        summaryLabel = new Label();

        // Refresh chart when filters change
        periodFilter.setOnAction(event -> updateChart());
        drinkFilter.setOnAction(event -> updateChart());
        startDate.setOnAction(event -> updateChart());
        endDate.setOnAction(event -> updateChart());

        VBox root = new VBox(
                20,
                filters,
                chart,
                summaryLabel
        );

        root.setPadding(new Insets(20));
        VBox.setVgrow(
                chart,
                javafx.scene.layout.Priority.ALWAYS
        );

        // Display initial data
        updateChart();

        Scene scene = new Scene(root, 1150, 650);

        stage.setTitle("Study Analytics Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    private void updateChart() {

        Map<String, Integer> totals = new TreeMap<>();

        int totalMinutes = 0;
        int sessionCount = 0;

        for (StudyRecord record : records) {

            // Apply start date filter
            if (startDate.getValue() != null
                    && record.date.isBefore(startDate.getValue())) {
                continue;
            }

            // Apply end date filter
            if (endDate.getValue() != null
                    && record.date.isAfter(endDate.getValue())) {
                continue;
            }

            // Apply drink filter
            if (!drinkFilter.getValue().equals("All")
                    && !record.drink.equals(drinkFilter.getValue())) {
                continue;
            }

            // Group records by selected period
            String key = getPeriodKey(
                    record.date,
                    periodFilter.getValue()
            );

            totals.merge(
                    key,
                    record.minutes,
                    Integer::sum
            );

            totalMinutes += record.minutes;
            sessionCount++;
        }

        // Create chart series
        XYChart.Series<String, Number> series =
                new XYChart.Series<>();

        series.setName("Study Time");

        for (Map.Entry<String, Integer> entry
                : totals.entrySet()) {

            series.getData().add(
                    new XYChart.Data<>(
                            entry.getKey(),
                            entry.getValue()
                    )
            );
        }

        // Replace previous chart data
        chart.getData().clear();
        chart.getData().add(series);

        // Update summary
        summaryLabel.setText(
                "Total Study Time: " + totalMinutes + " minutes"
                        + "    |    Completed Sessions: "
                        + sessionCount
        );
    }

    private String getPeriodKey(LocalDate date, String period) {

        switch (period) {

            case "Week":
                WeekFields weeks = WeekFields.ISO;

                int weekYear = date.get(
                        weeks.weekBasedYear());

                int week = date.get(
                        weeks.weekOfWeekBasedYear());

                return String.format(
                        "%04d-W%02d",
                        weekYear,
                        week
                );

            case "Month":
                return YearMonth.from(date).toString();

            case "Year":
                return String.valueOf(date.getYear());

            default:
                return date.toString();
        }
    }

    private static class StudyRecord {

        private final LocalDate date;
        private final int minutes;
        private final String drink;

        StudyRecord(
                LocalDate date,
                int minutes,
                String drink
        ) {
            this.date = date;
            this.minutes = minutes;
            this.drink = drink;
        }
    }
}
