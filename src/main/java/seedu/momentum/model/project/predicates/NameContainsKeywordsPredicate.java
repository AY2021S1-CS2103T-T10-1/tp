package seedu.momentum.model.project.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.momentum.commons.util.StringUtil;
import seedu.momentum.model.project.TrackedItem;

/**
 * Tests that a {@code TrackedItem}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<TrackedItem> {
    private final List<String> keywords;
    private final FindType findType;

    /**
     * Creates a predicate to check whether the {@code Name} of a {@code TrackedItem} contains a
     * certain keyword.
     *
     * @param findType enum to indicate the find type to be used for this find command.
     * @param keywords list of keywords to check for matches.
     */
    public NameContainsKeywordsPredicate(FindType findType, List<String> keywords) {
        this.findType = findType;
        this.keywords = keywords;
    }

    @Override
    public boolean test(TrackedItem trackedItem) {
        Predicate<String> predicate =
            keyword -> StringUtil.containsPartialIgnoreCase(trackedItem.getName().fullName, keyword);
        switch (findType) {
        case ALL:
            return keywords.stream().allMatch(predicate);
        case ANY:
            // Fallthrough
        default:
            return keywords.stream().anyMatch(predicate);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)) // state check
                && findType == ((NameContainsKeywordsPredicate) other).findType;
    }

}
