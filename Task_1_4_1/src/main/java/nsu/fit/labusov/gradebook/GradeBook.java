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
    private final ScholarShip scholarship;
    private final ArrayList<Semester> semesters;

    public GradeBook(ScholarShip scholarship) {
        semesters = new ArrayList<>();
        this.scholarship = scholarship;
    }

    private boolean canBeMoreSemesters() {
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

    public void add(Subject[] controlTypes) {
        if (canBeMoreSemesters()) {
            throw new IllegalArgumentException("Unable to add more semesters");
        }

        semesters.add(new Semester(controlTypes, semesters.size() + 1));
    }

    public double getWholeGPA() {
        Semester[] ints = semesters.toArray(new Semester[0]);

        double wholeGPA = Arrays.stream(ints).map(Semester::getGPA).reduce(0.0, Double::sum);
        double controlTypeAmount = Arrays.stream(ints).map(Semester::getCountableControlTypesLength).reduce(0, Integer::sum);

        return Math.round(wholeGPA / controlTypeAmount * 100.0) / 100.0;
    }

    public boolean isPossibleToSwitchToBudget() {
        if (scholarship == ScholarShip.ABSENT) {
            return Arrays.stream(semesters.toArray(new Semester[0]))
                    .skip(semesters.size() - 2)
                    .allMatch(Semester::checkSemesterRatingForBudget);
        } else {
            throw new IllegalArgumentException("You are already in budget");
        }
    }

    public boolean isPossibleToSwitchToIncreasedScholarship() {
        if (semesters.size() < 2) {
            return false;
        } else if (scholarship == ScholarShip.ABSENT) {
            return false;
        } else if (scholarship == ScholarShip.INCREASED) {
            throw new IllegalArgumentException("You already have increased scholarship");
        }

        return Arrays.stream(semesters.toArray(new Semester[0]))
                .allMatch(Semester::checkSemesterRatingForIncreasedScholarShip);
    }

    public boolean isPossibleToGetRedDiploma() {
        if (canBeMoreSemesters()) {
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

    public static void main(String[] args) {
        Subject[] controlTypes1 = new Subject[]{new Subject("English", FormsOfControlType.EXERCISE, FormsOfMark.THREE, false),
                new Subject("Algebra", FormsOfControlType.EXERCISE, FormsOfMark.FOUR, false),
                new Subject("Algebra", FormsOfControlType.CONTROL, FormsOfMark.FIVE, false),
                new Subject("Math logic", FormsOfControlType.CONTROL, FormsOfMark.FIVE, false),
                new Subject("English", FormsOfControlType.CONTROL, FormsOfMark.THREE, false),
                new Subject("History", FormsOfControlType.COLLOQUIUM, FormsOfMark.FIVE, false),
                new Subject("Math logic", FormsOfControlType.EXAM, FormsOfMark.THREE, true),
                new Subject("Discrete math", FormsOfControlType.EXAM, FormsOfMark.FIVE, true),
                new Subject("Declarative programming", FormsOfControlType.EXAM, FormsOfMark.FIVE, true),
                new Subject("History", FormsOfControlType.DIFFERENTIATEDCREDIT, FormsOfMark.FOUR, true),
                new Subject("Imperative programming", FormsOfControlType.DIFFERENTIATEDCREDIT, FormsOfMark.FIVE, true),
                new Subject("Fundamentals of speech culture", FormsOfControlType.DIFFERENTIATEDCREDIT, FormsOfMark.FIVE, true),
                new Subject("PE", FormsOfControlType.CREDIT, FormsOfMark.PASS, false),
                new Subject("Digital platforms", FormsOfControlType.CREDIT, FormsOfMark.PASS, false),
                new Subject("English", FormsOfControlType.CREDIT, FormsOfMark.PASS, false)};

        Subject[] controlTypes2 = new Subject[]{new Subject("Algebra", FormsOfControlType.EXERCISE, FormsOfMark.THREE, false),
                new Subject("English", FormsOfControlType.EXERCISE, FormsOfMark.FOUR, false),
                new Subject("Algebra", FormsOfControlType.CONTROL, FormsOfMark.FIVE, false),
                new Subject("History", FormsOfControlType.COLLOQUIUM, FormsOfMark.FIVE, false),
                new Subject("Operation systems", FormsOfControlType.EXAM, FormsOfMark.FOUR, true),
                new Subject("Digital platforms", FormsOfControlType.EXAM, FormsOfMark.FIVE, true),
                new Subject("Declarative programming", FormsOfControlType.EXAM, FormsOfMark.FIVE, true),
                new Subject("Philosophy", FormsOfControlType.DIFFERENTIATEDCREDIT, FormsOfMark.THREE, false),
                new Subject("Imperative programming", FormsOfControlType.DIFFERENTIATEDCREDIT, FormsOfMark.FIVE, false),
                new Subject("Discrete math", FormsOfControlType.CREDIT, FormsOfMark.FAILURE, false),
                new Subject("PE", FormsOfControlType.CREDIT, FormsOfMark.PASS, false),
        };

        GradeBook gradeBook = new GradeBook(ScholarShip.ABSENT);

        gradeBook.add(controlTypes1);
        gradeBook.add(controlTypes2);
        System.out.println(gradeBook);
    }
}
