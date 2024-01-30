package org.example;

import java.util.ArrayList;

/**
 * semester class.
 */
public class Semester {
    private int term;

    private ArrayList<Subject> subjects;

    /**
     * semester function.
     */
    public Semester(int term) {
        this.term = term;
        this.subjects = new ArrayList<>();
    }

    /**
     * add function.
     */
    public void addSubject(Subject subject) {
        this.subjects.add(subject);
    }

    /**
     * get function.
     */
    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    /**
     * stipend function.
     */
    public boolean stipendPossibility;

    public boolean stipendPossibility() {
        return this.subjects.stream().noneMatch(x -> x.getGrades().value < 4);
    }

    /**
     * average function.
     */
    public double getAverage() {
        double ans = this.subjects.stream()
                .mapToInt(x -> x.getGrades().value)
                .average().getAsDouble();
        return ans;
    }
}