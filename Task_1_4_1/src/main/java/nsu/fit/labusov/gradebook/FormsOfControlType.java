package nsu.fit.labusov.gradebook;

public enum FormsOfControlType {
    EXERCISE, CONTROL, COLLOQUIUM, EXAM, DIFFERENTIATEDCREDIT,
    CREDIT, PRACTICEREPORTDEFENCE, VCRPROTECTION;

    @Override
    public String toString() {
        return switch (this) {
            case EXERCISE -> "Задание";
            case CONTROL -> "Контрольная";
            case COLLOQUIUM -> "Коллоквиум";
            case EXAM -> "Экзамен";
            case DIFFERENTIATEDCREDIT -> "Дифференцированный зачет";
            case CREDIT -> "Зачет";
            case PRACTICEREPORTDEFENCE -> "Защита отчета по практике";
            case VCRPROTECTION -> "Защита ВКР";
        };
    }
}
