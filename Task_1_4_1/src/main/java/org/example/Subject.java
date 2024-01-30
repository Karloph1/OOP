package org.example;

/**
 * subject class.
 */
public class Subject {

    private String subject;
    private Mark grade;

    /**
     * subject function.
     */
    public Subject(String name, Mark grade) {
        this.subject = name;
        this.grade = grade;
    }

    /**
     * get function.
     */
    public Mark getGrades() {
        return this.grade;
    }

    /**
     * get function.
     */
    public String getSubjectName() {
        return subject;
    }
}