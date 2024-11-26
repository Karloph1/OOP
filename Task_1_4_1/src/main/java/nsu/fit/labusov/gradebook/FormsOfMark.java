package nsu.fit.labusov.gradebook;

public enum FormsOfMark {
    TWO, THREE, FOUR, FIVE, PASS, FAILURE;

    @Override
    public String toString() {
        return switch (this) {
            case TWO -> ("Неудовлетворительно");
            case THREE -> ("Удовлетворительно");
            case FOUR -> ("Хорошо");
            case FIVE -> ("Отлично");
            case PASS -> ("Зачет");
            case FAILURE -> ("Незачет");
        };
    }
}
