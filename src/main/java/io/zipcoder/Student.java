package io.zipcoder;

import java.util.ArrayList;
import java.util.Arrays;

public class Student implements Comparable<Student> {
    private String firstName;
    private String lastName;
    private ArrayList<Double> examScores;

    public Student(String firstName, String lastName) {
        this(firstName, lastName, new Double[0]);
    }

    public Student(String firstName, String lastName, Double[] scores) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.examScores = new ArrayList<>();
        examScores.addAll(Arrays.asList(scores));
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public ArrayList<Double> getExamScores() {
        return examScores;
    }

    public int numberOfExamsTaken() {
        return examScores.size();
    }

    public void takeExam(double score) {
        examScores.add(score);
    }

    public void updateExamScore(int examNumber, double newScore) {
        if (examScores.size() < examNumber)
            examScores.add(newScore);
        else
            examScores.set(examNumber - 1, newScore);
    }

    /**
     * Return the average for all of the exams that the student has taken.
     * If they haven't taken any, be nice and give them 100.0.
     * @return The average for all the exams a student has taken.
     */
    public double getAverage() {
        double sum = 0.0;
        for (double d : examScores) {
            sum += d;
        }
        return Math.round(sum / examScores.size());
    }

    private int compareByLastName(Student o) {
        return this.getLastName().compareTo(o.getLastName());
    }

    private int compareByFirstName(Student o) {
        int r = this.getFirstName().compareTo(o.getFirstName());
        return (r == 0) ? compareByLastName(o) : r;
    }

    @Override
    public int compareTo(Student o) {
        int r = Double.compare(this.getAverage(), o.getAverage());
        return (r == 0) ? compareByFirstName(o) : r;
    }
}
