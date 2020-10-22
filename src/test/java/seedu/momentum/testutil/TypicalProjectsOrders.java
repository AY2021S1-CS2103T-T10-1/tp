package seedu.momentum.testutil;

import static seedu.momentum.testutil.TypicalProjects.ALICE;
import static seedu.momentum.testutil.TypicalProjects.BENSON;
import static seedu.momentum.testutil.TypicalProjects.CARL;
import static seedu.momentum.testutil.TypicalProjects.DANIEL;
import static seedu.momentum.testutil.TypicalProjects.ELLE;
import static seedu.momentum.testutil.TypicalProjects.FIONA;
import static seedu.momentum.testutil.TypicalProjects.GEORGE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.momentum.model.ProjectBook;
import seedu.momentum.model.project.Project;
import seedu.momentum.model.project.UniqueTrackedItemList;

public class TypicalProjectsOrders {

    public static List<Project> getOrderedProjectBookByAlphabeticalAscending() {
        List<Project> projects = new ArrayList<>(Arrays.asList(
                ALICE,
                BENSON,
                CARL,
                DANIEL,
                ELLE,
                FIONA,
                GEORGE));
        return projects;
    }

    public static List<Project> getOrderedProjectBookByAlphabeticalDescending() {
        List<Project> projects = new ArrayList<>(Arrays.asList(
                GEORGE,
                FIONA,
                ELLE,
                DANIEL,
                CARL,
                BENSON,
                ALICE));
        return projects;
    }

    public static List<Project> getOrderedProjectBookByDeadlineAscending() {
        List<Project> projects = new ArrayList<>(Arrays.asList(
                FIONA,
                ELLE,
                ALICE,
                BENSON,
                CARL,
                DANIEL,
                GEORGE));
        return projects;
    }

    public static List<Project> getOrderedProjectBookByDeadlineDescending() {
        List<Project> projects = new ArrayList<>(Arrays.asList(
                BENSON,
                ALICE,
                ELLE,
                FIONA,
                CARL,
                DANIEL,
                GEORGE));
        return projects;
    }

    public static List<Project> getOrderedProjectBookByCreatedDateAscending() {
        ProjectBook projectBook = new ProjectBook();
        List<Project> projects = new ArrayList<>(Arrays.asList(
                ALICE,
                BENSON,
                FIONA,
                DANIEL,
                ELLE,
                GEORGE,
                CARL));
        return projects;
    }

    public static List<Project> getOrderedProjectBookByCreatedDateDescending() {
        List<Project> projects = new ArrayList<>(Arrays.asList(
                CARL,
                GEORGE,
                ELLE,
                DANIEL,
                FIONA,
                BENSON,
                ALICE));
        return projects;
    }

    public static UniqueTrackedItemList getUniqueProjectList(List<Project> projects) {
        UniqueTrackedItemList uniqueTrackedItemList = new UniqueTrackedItemList();
        for (Project p : projects) {
            uniqueTrackedItemList.add(p);
        }
        return uniqueTrackedItemList;
    }

}
