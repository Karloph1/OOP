package nsu.fit.labusov.gradebook;

/**
 * Forms of mark class.
 */
public enum FormsOfMark {
    TWO("Неудовлетворительно"),
    THREE("Удовлетворительно"),
    FOUR("Хорошо"),
    FIVE("Отлично"),
    PASS("Зачет"),
    FAILURE("Незачет");

    private final String markName;

    FormsOfMark(String markName) {
        this.markName = markName;
    }

    @Override
    public String toString() {
        return markName;
    }
}
