//@@author kkangs0226
package seedu.momentum.model.project.comparators;

import java.util.Comparator;

import seedu.momentum.model.project.Name;
import seedu.momentum.model.project.TrackedItem;

public class NameCompare implements Comparator<TrackedItem> {

    /**
     * Compares name of two tracked items.
     *
     * @param  t1 First tracked item to compare.
     * @param  t2 Second tracked item to compare.
     * @return Integer values for comparison.
     */
    public int compare(TrackedItem t1, TrackedItem t2) {
        Name p1Name = t1.getName();
        Name p2Name = t2.getName();
        return p1Name.compareTo(p2Name);
    }
}
