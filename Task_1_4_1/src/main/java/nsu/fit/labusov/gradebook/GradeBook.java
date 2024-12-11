package nsu.fit.labusov.gradebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * GradeBook class.
 */
public class GradeBook {
    private final Scholarship scholarship;
    private final ArrayList<Semester> semesters;

    public GradeBook(Scholarship scholarship) {
        semesters = new ArrayList<>();
        this.scholarship = scholarship;
    }

    private boolean canNotBeMoreSemesters() {
        if (semesters.isEmpty()) {
            return false;
        }

        return semesters.get(semesters.size() - 1).getIsSemesterLast();
    }

    private List<String> getUniqueNames() {
        return Arrays.stream(semesters.toArray(new Semester[0]))
                .flatMap(semester -> Arrays.stream(semester.getSubjects()))
                .flatMap(subject -> Arrays.stream(new String[]{subject.getSubjectName()}))
                .distinct()
                .collect(Collectors.toList());
    }

    private List<Integer> getExamAndDiffCreditSubjectMarks(String name) {
        return Arrays.stream(semesters.toArray(new Semester[0]))
                .flatMap(semester -> Arrays.stream(semester.getSubjects()))
                .filter(subject -> Objects.equals(subject.getSubjectName(), name)
                        && (subject.getWorkType() == FormsOfControlType.EXAM
                        || subject.getWorkType() == FormsOfControlType.DIFFERENTIATEDCREDIT))
                .flatMap(subject -> Arrays.stream(new Integer[]{subject.getControlGrade()}))
                .collect(Collectors.toList());
    }

    /**
     * Add semester method.
     */
    public void add(Subject[] controlTypes) {
        if (canNotBeMoreSemesters()) {
            throw new IllegalArgumentException("Unable to add more semesters");
        }

        semesters.add(new Semester(controlTypes, semesters.size() + 1));
    }

    /**
     * Get whole gpa method.
     */
    public double getWholeGpa() {
        Semester[] ints = semesters.toArray(new Semester[0]);

        double wholeGpa = Arrays.stream(ints)
                .map(Semester::getGpa)
                .reduce(0.0, Double::sum);
        double controlTypeAmount = Arrays.stream(ints)
                .map(Semester::getCountableControlTypesLength)
                .reduce(0, Integer::sum);

        return Math.round(wholeGpa / controlTypeAmount * 100.0) / 100.0;
    }

    /**
     * Is possible to switch to budget method.
     */
    public boolean isPossibleToSwitchToBudget() {
        if (scholarship == Scholarship.ABSENT) {
            return Arrays.stream(semesters.toArray(new Semester[0]))
                    .skip(semesters.size() - 2)
                    .allMatch(Semester::checkSemesterRatingForBudget);
        } else {
            throw new IllegalArgumentException("You are already in budget");
        }
    }

    /**
     * Is possible to switch to increased scholarship method.
     */
    public boolean isPossibleToSwitchToIncreasedScholarship() {
        if (semesters.size() < 2) {
            return false;
        } else if (scholarship == Scholarship.ABSENT) {
            return false;
        } else if (scholarship == Scholarship.INCREASED) {
            throw new IllegalArgumentException("You already have increased scholarship");
        }

        return Arrays.stream(semesters.toArray(new Semester[0]))
                .allMatch(Semester::checkSemesterRatingForIncreasedScholarShip);
    }

    /**
     * Is possible to get red diploma method.
     */
    public boolean isPossibleToGetRedDiploma() {
        if (canNotBeMoreSemesters()) {
            if (!Arrays.stream(semesters.get(semesters.size() - 1).getSubjects())
                    .filter(x -> x.getWorkType() == FormsOfControlType.VCRPROTECTION)
                    .allMatch(x -> x.getControlGrade() == 5)) {
                return false;
            }
        }

        double fiveNumCounts = Arrays.stream(semesters.toArray(new Semester[0]))
                .flatMap(semester -> Arrays.stream(semester.getSubjects()))
                .filter(Subject::getIsCountedInDiploma)
                .filter(x -> x.getControlGrade() == 5)
                .count();

        double otherNumCounts = Arrays.stream(semesters.toArray(new Semester[0]))
                .flatMap(semester -> Arrays.stream(semester.getSubjects()))
                .filter(Subject::getIsCountedInDiploma)
                .filter(x -> x.getControlGrade() == 5
                        || x.getControlGrade() == 4
                        || x.getControlGrade() == 3)
                .count();

        if (fiveNumCounts / otherNumCounts < 0.75) {
            return false;
        }

        return Arrays.stream(this.getUniqueNames().toArray(new String[0]))
                .map(this::getExamAndDiffCreditSubjectMarks)
                .filter(x -> !x.isEmpty())
                .allMatch(x -> x.get(x.size() - 1) > 3);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < semesters.size(); i++) {
            stringBuilder
                    .append(i + 1)
                    .append(" семестр - {")
                    .append(semesters.get(i).toString())
                    .append("}\n\n");
        }

        return stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length())
                .toString();
    }
}