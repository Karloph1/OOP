package nsu.fit.labusov.gradebook;

public class Subject {
    private final String subjectName;
    private final FormsOfControlType countableName;
    private final FormsOfMark countableMark;
    private final boolean isCountedInDiploma;

    public Subject(String subjectName, FormsOfControlType controlTypeName,
                   FormsOfMark controlTypeGrade, boolean isCountedInDiploma) {
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
