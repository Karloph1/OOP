package nsu.fit.labusov.gradebook;

import java.util.Arrays;

/**
 * Semester class.
 */
public class Semester {
    private final Subject[] subjects;
    private final int semesterNumber;
    private final boolean isSemesterLast;

    /**
     * Semester constructor.
     */
    public Semester(Subject[] controlTypes, int semesterNumber) {
        this.subjects = controlTypes;
        this.semesterNumber = semesterNumber;

        isSemesterLast = Arrays.stream(controlTypes)
                .map(Subject::getWorkType)
                .anyMatch(x -> x == FormsOfControlType.VCRPROTECTION);
    }

    public Subject[] getSubjects() {
        return subjects;
    }

    public int getSemesterNumber() {
        return semesterNumber;
    }

    public boolean getIsSemesterLast() {
        return isSemesterLast;
    }

    /**
     * get GPA method.
     */
    public double getGpa() {
        return (double) Arrays.stream(subjects)
                .map(Subject::getControlGrade)
                .reduce(0, Integer::sum);
    }

    /**
     * get length of Countable types method.
     */
    public int getCountableControlTypesLength() {
        return (int) Arrays.stream(subjects)
                .map(Subject::getControlGrade)
                .filter(x -> x != 0)
                .count();
    }

    public boolean checkSemesterRatingForBudget() {
        return Arrays.stream(subjects)
                .allMatch(Subject::checkRatingForBudget);
    }

    public boolean checkSemesterRatingForIncreasedScholarShip() {
        return Arrays.stream(subjects)
                .allMatch(Subject::checkRatingForIncreasedScholarship);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Subject controlType : subjects) {
            stringBuilder.append(controlType.toString()).append("\n");
        }

        return stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length())
                .toString();
    }
}