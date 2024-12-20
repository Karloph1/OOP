package nsu.fit.labusov.gradebook;

/**
 * Forms of control type class.
 */
public enum FormsOfControlType {
    EXERCISE("Задание"),
    CONTROL("Контрольная"),
    COLLOQUIUM("Коллоквиум"),
    EXAM("Экзамен"),
    DIFFERENTIATEDCREDIT("Дифференцированный зачет"),
    CREDIT("Зачет"),
    PRACTICEREPORTDEFENCE("Защита отчета по практике"),
    VCRPROTECTION("Защита ВКР");

    private final String formName;

    FormsOfControlType(String name) {
        this.formName = name;
    }

    @Override
    public String toString() {
        return formName;
    }
}
