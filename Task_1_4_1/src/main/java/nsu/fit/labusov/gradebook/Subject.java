package nsu.fit.labusov.gradebook;

/**
 * Subject class.
 */
public class Subject {
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

    @Override
    public String toString() {
        return subjectName + "(" + countableName.toString() + ") - " + countableMark.toString();
    }
}
