package nsu.fit.labusov.gradebook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;

/**
 * Subject class.
 */
public class Subject implements Serializable {
    private final String subjectName;
    private final FormsOfControlType countableName;
    private final FormsOfMark countableMark;
    private final boolean isCountedInDiploma;

    /**
     * Subject constructor.
     */
    public Subject(String subjectName, FormsOfControlType controlTypeName,
                   FormsOfMark controlTypeGrade, boolean isCountedInDiploma) {

        if ((controlTypeName == FormsOfControlType.CREDIT
                && (controlTypeGrade != FormsOfMark.PASS
                && controlTypeGrade != FormsOfMark.FAILURE))
                || (controlTypeName != FormsOfControlType.CREDIT
                && (controlTypeGrade == FormsOfMark.PASS
                || controlTypeGrade == FormsOfMark.FAILURE))) {
            throw new IllegalArgumentException("Incorrect grade for test");
        }

        this.subjectName = subjectName;
        this.countableName = controlTypeName;
        this.countableMark = controlTypeGrade;
        this.isCountedInDiploma = isCountedInDiploma;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public FormsOfControlType getWorkType() {
        return countableName;
    }

    /**
     * Get control grade method.
     */
    public int getControlGrade() {
        return switch (countableMark) {
            case TWO -> 2;
            case THREE -> 3;
            case FOUR -> 4;
            case FIVE -> 5;
            case PASS, FAILURE -> 0;
        };
    }

    public boolean getIsCountedInDiploma() {
        return isCountedInDiploma;
    }

    /**
     * Check rating for budget method.
     */
    public boolean checkRatingForBudget() {
        if (countableName == FormsOfControlType.EXAM) {
            return countableMark != FormsOfMark.TWO && countableMark != FormsOfMark.THREE;
        } else if (countableName == FormsOfControlType.DIFFERENTIATEDCREDIT) {
            return countableMark != FormsOfMark.TWO;
        } else if (countableName == FormsOfControlType.CREDIT) {
            return countableMark != FormsOfMark.FAILURE;
        }

        return true;
    }

    /**
     * Check rating for increased scholarship method.
     */
    public boolean checkRatingForIncreasedScholarship() {
        if (countableName == FormsOfControlType.EXAM
                || countableName == FormsOfControlType.DIFFERENTIATEDCREDIT) {
            return countableMark != FormsOfMark.TWO && countableMark != FormsOfMark.THREE;
        } else if (countableName == FormsOfControlType.CREDIT) {
            return countableMark != FormsOfMark.FAILURE;
        } else {
            return countableMark != FormsOfMark.TWO;
        }
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

        Subject subject = (Subject) o;

        if (isCountedInDiploma != subject.isCountedInDiploma) {
            return false;
        }
        if (!Objects.equals(subjectName, subject.subjectName)) {
            return false;
        }
        if (countableName != subject.countableName) {
            return false;
        }
        return countableMark == subject.countableMark;
    }

    @Override
    public int hashCode() {
        int result = subjectName != null ? subjectName.hashCode() : 0;
        result = 31 * result + (countableName != null ? countableName.hashCode() : 0);
        result = 31 * result + (countableMark != null ? countableMark.hashCode() : 0);
        result = 31 * result + (isCountedInDiploma ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return subjectName + "(" + countableName.toString() + ") - " + countableMark.toString();
    }
}
