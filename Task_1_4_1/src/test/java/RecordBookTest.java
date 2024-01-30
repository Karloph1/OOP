import org.example.RecordBook;
import org.example.Semester;
import org.example.Mark;
import org.example.Subject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * main test class.
 */
public class RecordBookTest {

    @Test
    void testAverageMark() {
        Semester sem1 = new Semester(1);
        Subject peLesson = new Subject("PE", Mark.FOUR);
        Subject literature = new Subject("literature", Mark.THREE);
        sem1.addSubject(peLesson);
        sem1.addSubject(literature);

        Semester sem2 = new Semester(2);
        Subject peLesson2 = new Subject("PE", Mark.FOUR);
        Subject math = new Subject("math", Mark.FOUR);
        sem2.addSubject(peLesson2);
        sem2.addSubject(math);

        RecordBook student = new RecordBook();
        student.addSemester(sem1);
        student.addSemester(sem2);

        Assertions.assertFalse(Math.abs(5 - student.getAverageMark()) < 0.1);
    }

    @Test
    void testStipend() {
        Semester sem1 = new Semester(1);
        Subject terver = new Subject("terver", Mark.THREE);
        Subject objectOriented = new Subject("java", Mark.THREE);
        Subject imperative = new Subject("imperative", Mark.THREE);
        sem1.addSubject(terver);
        sem1.addSubject(objectOriented);
        sem1.addSubject(imperative);

        Semester sem2 = new Semester(2);
        Subject english = new Subject("english", Mark.FIVE);
        Subject peLesson = new Subject("PE", Mark.FIVE);
        Subject math = new Subject("math", Mark.FOUR);
        sem2.addSubject(english);
        sem2.addSubject(peLesson);
        sem2.addSubject(math);

        RecordBook student = new RecordBook();
        student.addSemester(sem1);
        student.addSemester(sem2);
        assert (student.isStipendPossible());
    }


    @Test
    void testDiploma() {
        Semester sem1 = new Semester(1);
        Subject terver = new Subject("PE", Mark.FIVE);
        Subject objectOriented = new Subject("math", Mark.FIVE);
        sem1.addSubject(terver);
        sem1.addSubject(objectOriented);

        Semester sem2 = new Semester(2);
        Subject english = new Subject("english", Mark.FIVE);
        Subject peLesson2 = new Subject("PE", Mark.FIVE);
        sem2.addSubject(english);
        sem2.addSubject(peLesson2);

        RecordBook student = new RecordBook();
        student.addSemester(sem1);
        student.addSemester(sem2);
        student.setDefence(Mark.FIVE);
        assert (student.isRedDiplomaPossible());
    }


}
