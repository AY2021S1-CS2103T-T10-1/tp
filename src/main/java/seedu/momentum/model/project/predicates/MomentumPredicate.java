package seedu.momentum.model.project.predicates;

import java.util.function.Predicate;

import seedu.momentum.model.project.TrackedItem;

public interface MomentumPredicate extends Predicate<TrackedItem> {

    boolean isSamePredicate(MomentumPredicate other);

    default MomentumPredicate and(MomentumPredicate other) {
        return new MomentumPredicate() {
            @Override
            public boolean isSamePredicate(MomentumPredicate other) {
                return other.equals(this);
            }

            @Override
            public boolean test(TrackedItem trackedItem) {
                return MomentumPredicate.this.test(trackedItem) && other.test(trackedItem);
            }
        };
    }

    default MomentumPredicate or (MomentumPredicate other) {
        return new MomentumPredicate() {
            @Override
            public boolean isSamePredicate(MomentumPredicate other) {
                return other.equals(this);
            }

            @Override
            public boolean test(TrackedItem trackedItem) {
                return MomentumPredicate.this.test(trackedItem) || other.test(trackedItem);
            }
        };
    }
}
