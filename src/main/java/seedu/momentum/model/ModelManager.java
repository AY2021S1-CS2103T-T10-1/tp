package seedu.momentum.model;

import static java.util.Objects.requireNonNull;
import static seedu.momentum.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.momentum.commons.core.GuiSettings;
import seedu.momentum.commons.core.LogsCenter;
import seedu.momentum.model.project.SortType;
import seedu.momentum.model.project.TrackedItem;

/**
 * Represents the in-memory model of the project book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ProjectBook projectBook;
    private final UserPrefs userPrefs;
    private final FilteredList<TrackedItem> filteredTrackedItems;
    private final ObservableList<TrackedItem> runningTimers;
    private Predicate<TrackedItem> currentPredicate;
    private SortType currentSortType;
    private boolean currentSortIsAscending;

    /**
     * Initializes a ModelManager with the given projectBook and userPrefs.
     */
    public ModelManager(ReadOnlyProjectBook projectBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(projectBook, userPrefs);

        logger.fine("Initializing with project book: " + projectBook + " and user prefs " + userPrefs);

        this.projectBook = new ProjectBook(projectBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredTrackedItems = new FilteredList<>(this.projectBook.getTrackedItemList());
        runningTimers = FXCollections.observableArrayList();
        initializeRunningTimers();
        currentPredicate = PREDICATE_SHOW_ALL_TRACKED_ITEMS;
        currentSortType = SortType.ALPHA;
        currentSortIsAscending = true;
    }

    public ModelManager() {
        this(new ProjectBook(), new UserPrefs());
    }

    private void initializeRunningTimers() {
        for (TrackedItem trackedItem : filteredTrackedItems) {
            if (trackedItem.isRunning()) {
                runningTimers.add(trackedItem);
            }
        }
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getProjectBookFilePath() {
        return userPrefs.getProjectBookFilePath();
    }

    @Override
    public void setProjectBookFilePath(Path projectBookFilePath) {
        requireNonNull(projectBookFilePath);
        userPrefs.setProjectBookFilePath(projectBookFilePath);
    }

    //=========== ProjectBook ================================================================================

    @Override
    public void setProjectBook(ReadOnlyProjectBook projectBook) {
        this.projectBook.resetData(projectBook);
    }

    @Override
    public ReadOnlyProjectBook getProjectBook() {
        return projectBook;
    }

    @Override
    public boolean hasTrackedItem(TrackedItem trackedItem) {
        requireNonNull(trackedItem);
        return projectBook.hasTrackedItem(trackedItem);
    }

    @Override
    public void deleteTrackedItem(TrackedItem target) {
        projectBook.renameTrackedItem(target);
    }

    @Override
    public void addTrackedItem(TrackedItem trackedItem) {
        projectBook.addTrackedItem(trackedItem);
        orderFilteredProjectList(currentSortType, currentSortIsAscending);
        updateFilteredProjectList(PREDICATE_SHOW_ALL_TRACKED_ITEMS);
    }

    @Override
    public void setTrackedItem(TrackedItem target, TrackedItem editedTrackedItem) {
        requireAllNonNull(target, editedTrackedItem);

        projectBook.setTrackedItem(target, editedTrackedItem);
    }

    //=========== Filtered Project List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code TrackedItem} backed by the internal list of
     * {@code versionedProjectBook}
     * @return
     */
    @Override
    public ObservableList<TrackedItem> getFilteredTrackedItemList() {
        return filteredTrackedItems;
    }

    @Override
    public void updateFilteredProjectList(Predicate<TrackedItem> predicate) {
        requireNonNull(predicate);
        currentPredicate = predicate;
        filteredTrackedItems.setPredicate(predicate);
    }

    @Override
    public void orderFilteredProjectList(SortType orderType, boolean isAscending) {
        requireAllNonNull(orderType, isAscending);
        currentSortIsAscending = isAscending;
        currentSortType = orderType;
        projectBook.setOrder(orderType, isAscending);
        updateFilteredProjectList(currentPredicate);
    }

    //=========== Timers =============================================================

    @Override
    public ObservableList<TrackedItem> getRunningTimers() {
        return runningTimers;
    }

    @Override
    public void addRunningTimer(TrackedItem trackedItem) {
        assert (trackedItem.isRunning());

        runningTimers.add(trackedItem);
    }

    @Override
    public void removeRunningTimer(TrackedItem trackedItem) {
        assert (trackedItem.isRunning());
        assert (runningTimers.contains(trackedItem));

        runningTimers.remove(trackedItem);
    }



    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return projectBook.equals(other.projectBook)
                && userPrefs.equals(other.userPrefs)
                && filteredTrackedItems.equals(other.filteredTrackedItems)
                && runningTimers.equals(other.runningTimers);
    }

}
