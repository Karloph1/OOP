package org.example;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * main class.
 */
public class RecordBook {

    private Mark defence;
    private ArrayList<Semester> Semesters = new ArrayList<>();
    private int lastSemester = 0;

    /**
     * add function.
     */
    public void addSemester(Semester sem) {
        Semesters.add(sem);
        lastSemester += 1;
    }

    /**
     * average function.
     */
    public double getAverageMark() {
        double ans = Semesters.stream()
                .mapToDouble(x -> x.getAverage())
                .average().getAsDouble();
        return ans;
    }

    /**
     * defence function.
     */
    public void setDefence(Mark grade) {
        this.defence = grade;
    }

    /**
     * reddiploma function.
     */
    public boolean isRedDiplomaPossible() {
        HashMap<String, Mark> lastMarks = new HashMap<>();
        for (int i = 0; i < lastSemester; i++) {
            Semesters.get(i).getSubjects()
                    .stream()
                    .forEach(x -> lastMarks
                            .put(x.getSubjectName(), x.getGrades()));
        }
        int fiveCount = 0;
        int fourCount = 0;
        for (HashMap.Entry<String, Mark> e : lastMarks.entrySet()) {
            if (e.getValue() == Mark.FIVE) {
                fiveCount += 1;
            } else if (e.getValue() == Mark.FOUR) {
                fourCount += 1;
            }
        }

        boolean noThree = Semesters.stream()
                .allMatch(Semester::StipendPossibility);

        return (noThree & (defence == Mark.FIVE) & ((double) fiveCount / (fourCount + fiveCount)) >= 0.75);
    }

    /**
     * stipend function.
     */
    public boolean isStipendPossible() {
        return Semesters.get(lastSemester - 1).StipendPossibility();
    }

}