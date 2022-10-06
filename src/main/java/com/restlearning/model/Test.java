package com.restlearning.model;

import java.util.List;

public class Test {
    private List<Student> students;

    public Test() {
    }

    public Test(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Test{" +
                "students=" + students +
                '}';
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
