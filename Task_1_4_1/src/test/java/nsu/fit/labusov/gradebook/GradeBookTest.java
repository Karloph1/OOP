package nsu.fit.labusov.gradebook;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * GradeBook tests.
 */
public class GradeBookTest {

    /**
     * FormsOfControlType tests.
     */
    @Test
    void formsOfControlTypeToStringTest1() {
        FormsOfControlType a = FormsOfControlType.EXERCISE;

        Assertions.assertEquals("Задание", a.toString());
    }

    @Test
    void formsOfControlTypeToStringTest2() {
        FormsOfControlType a = FormsOfControlType.CONTROL;

        Assertions.assertEquals("Контрольная", a.toString());
    }

    @Test
    void formsOfControlTypeToStringTest3() {
        FormsOfControlType a = FormsOfControlType.COLLOQUIUM;

        Assertions.assertEquals("Коллоквиум", a.toString());
    }

    @Test
    void formsOfControlTypeToStringTest4() {
        FormsOfControlType a = FormsOfControlType.EXAM;

        Assertions.assertEquals("Экзамен", a.toString());
    }

    @Test
    void formsOfControlTypeToStringTest5() {
        FormsOfControlType a = FormsOfControlType.DIFFERENTIATEDCREDIT;

        Assertions.assertEquals("Дифференцированный зачет", a.toString());
    }

    @Test
    void formsOfControlTypeToStringTest6() {
        FormsOfControlType a = FormsOfControlType.CREDIT;

        Assertions.assertEquals("Зачет", a.toString());
    }

    @Test
    void formsOfControlTypeToStringTest7() {
        FormsOfControlType a = FormsOfControlType.PRACTICEREPORTDEFENCE;

        Assertions.assertEquals("Защита отчета по практике", a.toString());
    }

    @Test
    void formsOfControlTypeToStringTest8() {
        FormsOfControlType a = FormsOfControlType.VCRPROTECTION;

        Assertions.assertEquals("Защита ВКР", a.toString());
    }

    /**
     * FormsOfMark tests.
     */
    @Test
    void formsOfMarkToStringTest1() {
        FormsOfMark a = FormsOfMark.TWO;

        Assertions.assertEquals("Неудовлетворительно", a.toString());
    }

    @Test
    void formsOfMarkToStringTest2() {
        FormsOfMark a = FormsOfMark.THREE;

        Assertions.assertEquals("Удовлетворительно", a.toString());
    }

    @Test
    void formsOfMarkToStringTest3() {
        FormsOfMark a = FormsOfMark.FOUR;

        Assertions.assertEquals("Хорошо", a.toString());
    }

    @Test
    void formsOfMarkToStringTest4() {
        FormsOfMark a = FormsOfMark.FIVE;

        Assertions.assertEquals("Отлично", a.toString());
    }

    @Test
    void formsOfMarkToStringTest5() {
        FormsOfMark a = FormsOfMark.PASS;

        Assertions.assertEquals("Зачет", a.toString());
    }

    @Test
    void formsOfMarkToStringTest6() {
        FormsOfMark a = FormsOfMark.FAILURE;

        Assertions.assertEquals("Незачет", a.toString());
    }

    /**
     * ControlType tests.
     */
    @Test
    void controlTypeToStringTest() {
        Subject a = new Subject("OOP", FormsOfControlType.EXAM, FormsOfMark.FIVE, false);

        Assertions.assertEquals("OOP(Экзамен) - Отлично", a.toString());
    }

    @Test
    void controlTypeExceptionTest1() {
        try {
            Subject a = new Subject
                    ("OOP", FormsOfControlType.CREDIT, FormsOfMark.FIVE, false);

            System.out.println(a);
        } catch (IllegalArgumentException e) {
            Assertions.assertTrue(e.getMessage().contains("Incorrect grade for test"));
        }
    }

    @Test
    void controlTypeExceptionTest2() {
        try {
            Subject a = new Subject
                    ("OOP", FormsOfControlType.EXAM, FormsOfMark.FAILURE, false);

            System.out.println(a);
        } catch (IllegalArgumentException e) {
            Assertions.assertTrue(e.getMessage().contains("Incorrect grade for test"));
        }
    }

    @Test
    void controlTypeGetControlTypeNameTest() {
        Subject a = new Subject
                ("OOP", FormsOfControlType.COLLOQUIUM, FormsOfMark.FOUR, true);

        Assertions.assertEquals(FormsOfControlType.COLLOQUIUM, a.getWorkType());
    }

    @Test
    void controlTypeGetControlGradeTest() {
        Subject a = new Subject
                ("Math", FormsOfControlType.DIFFERENTIATEDCREDIT, FormsOfMark.THREE, true);

        Assertions.assertEquals(3, a.getControlGrade());
    }

    @Test
    void controlTypeGetIsCountedInDiplomaTest() {
        Subject a = new Subject
                ("Math", FormsOfControlType.PRACTICEREPORTDEFENCE, FormsOfMark.TWO, true);

        Assertions.assertTrue(a.getIsCountedInDiploma());
    }

    @Test
    void controlTypeCheckRatingForScholarshipTest1() {
        Subject a = new Subject
                ("History", FormsOfControlType.EXAM, FormsOfMark.THREE, true);

        Assertions.assertFalse(a.checkRatingForBudget());
    }

    @Test
    void controlTypeCheckRatingForScholarshipTest2() {
        Subject a = new Subject
                ("History", FormsOfControlType.DIFFERENTIATEDCREDIT, FormsOfMark.THREE, false);

        Assertions.assertTrue(a.checkRatingForBudget());
    }

    @Test
    void controlTypeCheckRatingForScholarshipTest3() {
        Subject a = new Subject
                ("PE", FormsOfControlType.CREDIT, FormsOfMark.PASS, true);

        Assertions.assertTrue(a.checkRatingForBudget());
    }

    /**
     * Semester tests.
     */
    @Test
    void semesterToStringTest() {
        Subject[] controlTypes = new Subject[]{new Subject("OOP", FormsOfControlType.EXERCISE, FormsOfMark.THREE, false),
                new Subject("Math", FormsOfControlType.EXERCISE, FormsOfMark.FOUR, false),
                new Subject("PE", FormsOfControlType.CONTROL, FormsOfMark.FIVE, false),
                new Subject("English", FormsOfControlType.EXAM, FormsOfMark.THREE, true),
                new Subject("Operation systems", FormsOfControlType.EXAM, FormsOfMark.FIVE, true),
                new Subject("History", FormsOfControlType.DIFFERENTIATEDCREDIT, FormsOfMark.THREE, false),
                new Subject("Philosophy", FormsOfControlType.DIFFERENTIATEDCREDIT, FormsOfMark.FIVE, false),
        };

        Semester a = new Semester(controlTypes, 5);

        Assertions.assertEquals("""
                OOP(Задание) - Удовлетворительно
                Math(Задание) - Хорошо
                PE(Контрольная) - Отлично
                English(Экзамен) - Удовлетворительно
                Operation systems(Экзамен) - Отлично
                History(Дифференцированный зачет) - Удовлетворительно
                Philosophy(Дифференцированный зачет) - Отлично""", a.toString());
    }

    @Test
    void semesterGetSubjectsTest() {
        Subject[] controlTypes = new Subject[]{new Subject("Math", FormsOfControlType.EXERCISE, FormsOfMark.THREE, false),
                new Subject("OOP", FormsOfControlType.EXERCISE, FormsOfMark.FOUR, false),
                new Subject("Philosophy", FormsOfControlType.CONTROL, FormsOfMark.FIVE, false),
                new Subject("Imperative programming", FormsOfControlType.EXAM, FormsOfMark.THREE, true),
                new Subject("PE", FormsOfControlType.EXAM, FormsOfMark.FIVE, true),
                new Subject("History", FormsOfControlType.DIFFERENTIATEDCREDIT, FormsOfMark.THREE, false),
                new Subject("Operation systems", FormsOfControlType.DIFFERENTIATEDCREDIT, FormsOfMark.FIVE, false),
        };

        Semester a = new Semester(controlTypes, 2);

        Assertions.assertArrayEquals(controlTypes, a.getSubjects());
    }

    @Test
    void semesterGetSemesterNumberTest() {
        Subject[] controlTypes = new Subject[]{new Subject("Math", FormsOfControlType.EXERCISE, FormsOfMark.THREE, false),
                new Subject("PE", FormsOfControlType.EXERCISE, FormsOfMark.FOUR, false),
                new Subject("OOP", FormsOfControlType.CONTROL, FormsOfMark.FIVE, false),
                new Subject("Philosophy", FormsOfControlType.CONTROL, FormsOfMark.THREE, false),
                new Subject("Operation systems", FormsOfControlType.EXAM, FormsOfMark.THREE, true),
                new Subject("History", FormsOfControlType.DIFFERENTIATEDCREDIT, FormsOfMark.THREE, false),
        };

        Semester a = new Semester(controlTypes, 4);

        Assertions.assertEquals(4, a.getSemesterNumber());
    }

    @Test
    void semesterGetGPATest() {
        Subject[] controlTypes = new Subject[]{new Subject("Math", FormsOfControlType.EXERCISE, FormsOfMark.THREE, false),
                new Subject("Imperative programming", FormsOfControlType.EXERCISE, FormsOfMark.FOUR, false),
                new Subject("OOP", FormsOfControlType.EXAM, FormsOfMark.FIVE, true),
                new Subject("Philosophy", FormsOfControlType.EXAM, FormsOfMark.FIVE, true),
                new Subject("Operation systems", FormsOfControlType.DIFFERENTIATEDCREDIT, FormsOfMark.FIVE, false),
                new Subject("PE", FormsOfControlType.DIFFERENTIATEDCREDIT, FormsOfMark.FOUR, false),
                new Subject("History", FormsOfControlType.CREDIT, FormsOfMark.PASS, false)
        };

        Semester a = new Semester(controlTypes, 5);

        Assertions.assertEquals(26, a.getGPA());
    }

    @Test
    void semesterGetCountableControlTypesLengthTest() {
        Subject[] controlTypes = new Subject[]{new Subject("Math", FormsOfControlType.EXERCISE, FormsOfMark.THREE, false),
                new Subject("English", FormsOfControlType.CONTROL, FormsOfMark.FIVE, false),
                new Subject("Imperial programming", FormsOfControlType.CONTROL, FormsOfMark.THREE, false),
                new Subject("History", FormsOfControlType.COLLOQUIUM, FormsOfMark.FIVE, false),
                new Subject("OOP", FormsOfControlType.EXAM, FormsOfMark.FIVE, true),
                new Subject("Philosophy", FormsOfControlType.DIFFERENTIATEDCREDIT, FormsOfMark.THREE, false),
                new Subject("PE", FormsOfControlType.CREDIT, FormsOfMark.FAILURE, false),
                new Subject("Operation systems", FormsOfControlType.CREDIT, FormsOfMark.PASS, false),
        };

        Semester a = new Semester(controlTypes, 2);

        Assertions.assertEquals(6, a.getCountableControlTypesLength());
    }

    @Test
    void semesterCheckSemesterRatingFalseTest() {
        Subject[] controlTypes = new Subject[]{new Subject("Math", FormsOfControlType.EXERCISE, FormsOfMark.THREE, false),
                new Subject("English", FormsOfControlType.EXERCISE, FormsOfMark.FOUR, false),
                new Subject("Imperial programming", FormsOfControlType.CONTROL, FormsOfMark.FIVE, false),
                new Subject("PE", FormsOfControlType.CONTROL, FormsOfMark.FIVE, false),
                new Subject("Russian", FormsOfControlType.CONTROL, FormsOfMark.THREE, false),
                new Subject("History", FormsOfControlType.COLLOQUIUM, FormsOfMark.FIVE, false),
                new Subject("Operation systems", FormsOfControlType.EXAM, FormsOfMark.THREE, true),
                new Subject("Artificial intelligent", FormsOfControlType.EXAM, FormsOfMark.FIVE, true),
                new Subject("Digital platforms", FormsOfControlType.EXAM, FormsOfMark.FIVE, true),
                new Subject("Probability theory", FormsOfControlType.DIFFERENTIATEDCREDIT, FormsOfMark.THREE, false),
                new Subject("Economy", FormsOfControlType.DIFFERENTIATEDCREDIT, FormsOfMark.FIVE, false),
                new Subject("Chinese", FormsOfControlType.DIFFERENTIATEDCREDIT, FormsOfMark.FIVE, false),
                new Subject("French", FormsOfControlType.CREDIT, FormsOfMark.FAILURE, false),
                new Subject("Declarative programming", FormsOfControlType.CREDIT, FormsOfMark.PASS, false),
        };

        Semester a = new Semester(controlTypes, 2);

        Assertions.assertFalse(a.checkSemesterRatingForBudget());
    }

    @Test
    void semesterCheckSemesterRatingTrueTest() {
        Subject[] controlTypes = new Subject[]{new Subject("Math", FormsOfControlType.EXERCISE, FormsOfMark.THREE, false),
                new Subject("English", FormsOfControlType.EXERCISE, FormsOfMark.FOUR, false),
                new Subject("Imperial programming", FormsOfControlType.CONTROL, FormsOfMark.FIVE, false),
                new Subject("PE", FormsOfControlType.CONTROL, FormsOfMark.THREE, false),
                new Subject("History", FormsOfControlType.COLLOQUIUM, FormsOfMark.FIVE, false),
                new Subject("Operation systems", FormsOfControlType.EXAM, FormsOfMark.FOUR, true),
                new Subject("Digital platforms", FormsOfControlType.EXAM, FormsOfMark.FIVE, true),
                new Subject("Declarative programming", FormsOfControlType.DIFFERENTIATEDCREDIT, FormsOfMark.THREE, false),
                new Subject("Artificial intelligent", FormsOfControlType.DIFFERENTIATEDCREDIT, FormsOfMark.FIVE, false),
                new Subject("French", FormsOfControlType.CREDIT, FormsOfMark.PASS, false),
                new Subject("Probability theory", FormsOfControlType.CREDIT, FormsOfMark.PASS, false),
        };

        Semester a = new Semester(controlTypes, 2);

        Assertions.assertTrue(a.checkSemesterRatingForBudget());
    }

    /**
     * GradeBook tests.
     */
    Subject[] controlTypes1 = new Subject[]{
            new Subject("English", FormsOfControlType.EXERCISE, FormsOfMark.THREE, false),
            new Subject("Algebra", FormsOfControlType.EXERCISE, FormsOfMark.FOUR, false),
            new Subject("Algebra", FormsOfControlType.CONTROL, FormsOfMark.FIVE, false),
            new Subject("Math logic", FormsOfControlType.CONTROL, FormsOfMark.FIVE, false),
            new Subject("English", FormsOfControlType.CONTROL, FormsOfMark.THREE, false),
            new Subject("History", FormsOfControlType.COLLOQUIUM, FormsOfMark.FIVE, false),
            new Subject("Math logic", FormsOfControlType.EXAM, FormsOfMark.THREE, true),
            new Subject("Discrete math", FormsOfControlType.EXAM, FormsOfMark.FIVE, true),
            new Subject("Declarative programming",
                    FormsOfControlType.EXAM, FormsOfMark.FIVE, true),
            new Subject("History",
                    FormsOfControlType.DIFFERENTIATEDCREDIT, FormsOfMark.FOUR, true),
            new Subject("Imperative programming",
                    FormsOfControlType.DIFFERENTIATEDCREDIT, FormsOfMark.FIVE, true),
            new Subject("Fundamentals of speech culture",
                    FormsOfControlType.DIFFERENTIATEDCREDIT, FormsOfMark.FIVE, true),
            new Subject("PE", FormsOfControlType.CREDIT, FormsOfMark.PASS, false),
            new Subject("Digital platforms",
                    FormsOfControlType.CREDIT, FormsOfMark.PASS, false),
            new Subject("English", FormsOfControlType.CREDIT, FormsOfMark.PASS, false)};

    Subject[] controlTypes2 = new Subject[]{new Subject("Algebra", FormsOfControlType.EXERCISE,
            FormsOfMark.THREE, false),
            new Subject("English", FormsOfControlType.EXERCISE, FormsOfMark.FOUR, false),
            new Subject("Algebra", FormsOfControlType.CONTROL, FormsOfMark.FIVE, false),
            new Subject("History", FormsOfControlType.COLLOQUIUM, FormsOfMark.FIVE, false),
            new Subject("Operation systems", FormsOfControlType.EXAM, FormsOfMark.FOUR, true),
            new Subject("Digital platforms", FormsOfControlType.EXAM, FormsOfMark.FIVE, true),
            new Subject("Declarative programming",
                    FormsOfControlType.EXAM, FormsOfMark.FIVE, true),
            new Subject("Philosophy",
                    FormsOfControlType.DIFFERENTIATEDCREDIT, FormsOfMark.THREE, false),
            new Subject("Imperative programming",
                    FormsOfControlType.DIFFERENTIATEDCREDIT, FormsOfMark.FIVE, false),
            new Subject("Discrete math", FormsOfControlType.CREDIT, FormsOfMark.FAILURE, false),
            new Subject("PE", FormsOfControlType.CREDIT, FormsOfMark.PASS, false),
    };

    Subject[] controlTypes3 = new Subject[]{new Subject("Artificial intelligent",
            FormsOfControlType.EXERCISE, FormsOfMark.THREE, false),
            new Subject("English", FormsOfControlType.EXERCISE, FormsOfMark.FOUR, false),
            new Subject("Philosophy", FormsOfControlType.EXERCISE, FormsOfMark.FOUR, false),
            new Subject("English", FormsOfControlType.CONTROL, FormsOfMark.FOUR, false),
            new Subject("Probability theory", FormsOfControlType.EXAM, FormsOfMark.FIVE, true),
            new Subject("French", FormsOfControlType.EXAM, FormsOfMark.FIVE, true),
            new Subject("OOP", FormsOfControlType.DIFFERENTIATEDCREDIT, FormsOfMark.FIVE, true),
            new Subject("Operation systems",
                    FormsOfControlType.DIFFERENTIATEDCREDIT, FormsOfMark.FIVE, true),
            new Subject("Artificial intelligent",
                    FormsOfControlType.DIFFERENTIATEDCREDIT, FormsOfMark.FIVE, true)};

    Subject[] controlTypes4 = new Subject[]{new Subject("Artificial intelligent",
            FormsOfControlType.EXERCISE, FormsOfMark.THREE, false),
            new Subject("English", FormsOfControlType.EXERCISE, FormsOfMark.FOUR, false),
            new Subject("Philosophy", FormsOfControlType.CONTROL, FormsOfMark.THREE, false),
            new Subject("Probability theory", FormsOfControlType.EXAM, FormsOfMark.THREE, true),
            new Subject("OOP", FormsOfControlType.EXAM, FormsOfMark.FIVE, true),
            new Subject("French",
                    FormsOfControlType.DIFFERENTIATEDCREDIT, FormsOfMark.THREE, false),
            new Subject("Artificial intelligent",
                    FormsOfControlType.DIFFERENTIATEDCREDIT, FormsOfMark.FIVE, false),
    };

    Subject[] controlTypes5 = new Subject[]{new Subject("Security",
            FormsOfControlType.EXERCISE, FormsOfMark.THREE, false),
            new Subject("Algebra", FormsOfControlType.EXERCISE, FormsOfMark.FOUR, false),
            new Subject("Web programming", FormsOfControlType.CONTROL, FormsOfMark.FIVE, false),
            new Subject("PE", FormsOfControlType.CONTROL, FormsOfMark.THREE, false),
            new Subject("Web programming", FormsOfControlType.EXAM, FormsOfMark.THREE, true),
            new Subject("Philosophy",
                    FormsOfControlType.DIFFERENTIATEDCREDIT, FormsOfMark.THREE, false),
    };

    Subject[] controlTypes6 = new Subject[]{new Subject("English",
            FormsOfControlType.EXERCISE, FormsOfMark.THREE, false),
            new Subject("PE", FormsOfControlType.EXERCISE, FormsOfMark.FOUR, false),
            new Subject("French", FormsOfControlType.EXAM, FormsOfMark.FIVE, true),
            new Subject("Discrete math", FormsOfControlType.EXAM, FormsOfMark.FIVE, true),
            new Subject("Philosophy",
                    FormsOfControlType.DIFFERENTIATEDCREDIT, FormsOfMark.FOUR, true),
            new Subject("Imperial programming",
                    FormsOfControlType.DIFFERENTIATEDCREDIT, FormsOfMark.FIVE, true),
    };

    Subject[] controlTypes7 = new Subject[]{new Subject("English",
            FormsOfControlType.EXERCISE, FormsOfMark.THREE, false),
            new Subject("French", FormsOfControlType.EXERCISE, FormsOfMark.FOUR, false),
            new Subject("Operation systems", FormsOfControlType.EXAM, FormsOfMark.FOUR, true),
            new Subject("Discrete math",
                    FormsOfControlType.DIFFERENTIATEDCREDIT, FormsOfMark.THREE, false),
            new Subject("PE", FormsOfControlType.DIFFERENTIATEDCREDIT, FormsOfMark.FIVE, false),
            new Subject("OOP", FormsOfControlType.CREDIT, FormsOfMark.PASS, false),
            new Subject("Practice",
                    FormsOfControlType.PRACTICEREPORTDEFENCE, FormsOfMark.FIVE, true),
    };

    Subject[] controlTypes8 = new Subject[]{new Subject("English",
            FormsOfControlType.DIFFERENTIATEDCREDIT, FormsOfMark.FOUR, true),
            new Subject("History",
                    FormsOfControlType.DIFFERENTIATEDCREDIT, FormsOfMark.FIVE, true),
            new Subject("Operation system", FormsOfControlType.CREDIT, FormsOfMark.PASS, false),
            new Subject("Math practice",
                    FormsOfControlType.PRACTICEREPORTDEFENCE, FormsOfMark.FIVE, true),
            new Subject("Programming practice",
                    FormsOfControlType.PRACTICEREPORTDEFENCE, FormsOfMark.FIVE, true),
            new Subject("VCR protection",
                    FormsOfControlType.VCRPROTECTION, FormsOfMark.FIVE, false),
    };

    @Test
    void gradeBookToStringTest() {
        GradeBook gradeBook = new GradeBook(ScholarShip.ABSENT);
        gradeBook.add(controlTypes1);
        gradeBook.add(controlTypes2);

        Assertions.assertEquals("""
                1 семестр - {English(Задание) - Удовлетворительно
                Algebra(Задание) - Хорошо
                Algebra(Контрольная) - Отлично
                Math logic(Контрольная) - Отлично
                English(Контрольная) - Удовлетворительно
                History(Коллоквиум) - Отлично
                Math logic(Экзамен) - Удовлетворительно
                Discrete math(Экзамен) - Отлично
                Declarative programming(Экзамен) - Отлично
                History(Дифференцированный зачет) - Хорошо
                Imperative programming(Дифференцированный зачет) - Отлично
                Fundamentals of speech culture(Дифференцированный зачет) - Отлично
                PE(Зачет) - Зачет
                Digital platforms(Зачет) - Зачет
                English(Зачет) - Зачет}
                                
                2 семестр - {Algebra(Задание) - Удовлетворительно
                English(Задание) - Хорошо
                Algebra(Контрольная) - Отлично
                History(Коллоквиум) - Отлично
                Operation systems(Экзамен) - Хорошо
                Digital platforms(Экзамен) - Отлично
                Declarative programming(Экзамен) - Отлично
                Philosophy(Дифференцированный зачет) - Удовлетворительно
                Imperative programming(Дифференцированный зачет) - Отлично
                Discrete math(Зачет) - Незачет
                PE(Зачет) - Зачет}""", gradeBook.toString());
    }

    @Test
    void gradeBookAddExceptionTest() {
        GradeBook gradeBook = new GradeBook(ScholarShip.ABSENT);
        gradeBook.add(controlTypes8);

        try {
            gradeBook.add(controlTypes2);
        } catch (IllegalArgumentException e) {
            Assertions.assertTrue(e.getMessage().contains("Unable to add more semesters"));
        }
    }

    @Test
    void gradeBookGetWholeGPATest() {
        GradeBook gradeBook = new GradeBook(ScholarShip.NORMAL);
        gradeBook.add(controlTypes3);
        gradeBook.add(controlTypes2);
        gradeBook.add(controlTypes1);
        gradeBook.add(controlTypes4);

        Assertions.assertEquals(4.24, gradeBook.getWholeGPA());
    }

    @Test
    void gradeBookIsPossibleToSwitchToBudgetTest() {
        GradeBook gradeBook = new GradeBook(ScholarShip.ABSENT);
        gradeBook.add(controlTypes3);
        gradeBook.add(controlTypes6);
        gradeBook.add(controlTypes7);

        Assertions.assertTrue(gradeBook.isPossibleToSwitchToBudget());
    }

    @Test
    void gradeBookIsPossibleToSwitchToBudgetExceptionTest() {
        GradeBook gradeBook = new GradeBook(ScholarShip.NORMAL);
        gradeBook.add(controlTypes1);

        try {
            gradeBook.isPossibleToSwitchToBudget();
        } catch (IllegalArgumentException e) {
            Assertions.assertTrue(e.getMessage().contains("You are already in budget"));
        }
    }

    @Test
    void gradeBookIsPossibleToSwitchToIncreasedScholarshipTest() {
        GradeBook gradeBook = new GradeBook(ScholarShip.NORMAL);
        gradeBook.add(controlTypes1);
        gradeBook.add(controlTypes2);
        gradeBook.add(controlTypes5);

        Assertions.assertFalse(gradeBook.isPossibleToSwitchToIncreasedScholarship());
    }

    @Test
    void gradeBookIsPossibleToSwitchToIncreasedScholarshipExceptionTest() {
        GradeBook gradeBook = new GradeBook(ScholarShip.ABSENT);
        gradeBook.add(controlTypes1);
        gradeBook.add(controlTypes2);
        gradeBook.add(controlTypes3);

        try {
            gradeBook.isPossibleToSwitchToIncreasedScholarship();
        } catch (IllegalArgumentException e) {
            Assertions.assertTrue(e.getMessage().contains("You already have increased scholarship"));
        }
    }

    @Test
    void gradeBookIsCountedInDiplomaWithLastSemesterTest() {
        GradeBook gradeBook = new GradeBook(ScholarShip.ABSENT);

        gradeBook.add(controlTypes3);
        gradeBook.add(controlTypes6);
        gradeBook.add(controlTypes8);

        Assertions.assertTrue(gradeBook.isPossibleToGetRedDiploma());
    }
}