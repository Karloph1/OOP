package nsu.fit.labusov.gradebook;

/**
 *  Scholarship class.
 */
public enum Scholarship {
    ABSENT("Отсутствует"),
    NORMAL("Обычная"),
    INCREASED("Повышенная");

    private final String scholarshipName;

    Scholarship(String scholarshipName) {
        this.scholarshipName = scholarshipName;
    }

    @Override
    public String toString() {
        return scholarshipName;
    }
}
