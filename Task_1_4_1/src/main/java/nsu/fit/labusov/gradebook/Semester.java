package nsu.fit.labusov.gradebook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Semester class.
 */
public class Semester implements Serializable {
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

    /**
     * serialize method.
     */
    public void serialize(String fileName) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(fileName);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(this);

        objectOutputStream.close();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Semester semester = (Semester) o;

        if (semesterNumber != semester.semesterNumber) {
            return false;
        }
        if (isSemesterLast != semester.isSemesterLast) {
            return false;
        }

        return Arrays.equals(subjects, semester.subjects);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(subjects);
        result = 31 * result + semesterNumber;
        result = 31 * result + (isSemesterLast ? 1 : 0);
        return result;
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
