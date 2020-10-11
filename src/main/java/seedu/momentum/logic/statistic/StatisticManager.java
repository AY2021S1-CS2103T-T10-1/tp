package seedu.momentum.logic.statistic;

import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.momentum.model.Model;

/**
 * The main statistics manager of the app.
 * This class contains the specifications of statistics being tracked by the app,
 * and methods to generate those statistics
 */
public class StatisticManager implements StatisticGenerator {

    private Model model;

    // Statistics being tracked by the app
    private Statistic weeklyTotalTimePerProjectStatistic = new PeriodicTotalTimePerProjectStatistic(ChronoUnit.WEEKS,
            ChronoUnit.MINUTES);

    // Maintain an array of the above statistics for easy iteration
    private Statistic[] statistics = {
        weeklyTotalTimePerProjectStatistic
    };

    /**
     * Constructs a {@code StatisticManager} that tracks statistics form the specified model.
     *
     * @param model The Model to track.
     */
    public StatisticManager(Model model) {
        this.model = model;

        updateStatistics();
    }

    /**
     * Constructs a {@code StatisticManager} with a specified model and statistics data.
     *
     * @param model The Model to track.
     * @param statistics The data to set.
     */
    public StatisticManager(Model model, Statistic[] statistics) {
        this.model = model;
        this.statistics = statistics;
    }

    @Override
    public void updateStatistics() {
        for (Statistic statistic : statistics) {
            statistic.calculate(model);
        }

        for (StatisticEntry entry : getWeeklyTimePerProjectStatistic()) {
            System.out.println(entry);
        }
    }

    @Override
    public ObservableList<StatisticEntry> getWeeklyTimePerProjectStatistic() {
        return weeklyTotalTimePerProjectStatistic.getDisplayList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StatisticManager that = (StatisticManager) o;

        return Objects.equals(model, that.model)
                && Arrays.equals(statistics, that.statistics);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(model, weeklyTotalTimePerProjectStatistic);
        result = 31 * result + Arrays.hashCode(statistics);
        return result;
    }
}