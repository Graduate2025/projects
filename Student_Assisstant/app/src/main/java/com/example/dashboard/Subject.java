package com.example.dashboard;

public class Subject {

//  Declare Attribute of the class subjects
    private int creditHours;
    private String GradeString;
    private double Grade = 0;

//  Class constructor
    public Subject(int creditHours) {
        this.creditHours = creditHours;
    }

//  Class constructor
    public Subject(int creditHours, String gradeString) {
        this.creditHours = creditHours;
        GradeString = gradeString;
        switch (GradeString) {
            case "A":
                Grade = 4;
                break;
            case "A-":
                Grade = 3.666;
                break;
            case "B+":
                Grade = 3.333;
                break;
            case "B":
                Grade = 3;
                break;
            case "B-":
                Grade = 2.666;
                break;
            case "C+":
                Grade = 2.333;
                break;
            case "C":
                Grade = 2;
                break;
            case "C-":
                Grade = 1.666;
                break;
            case "D+":
                Grade = 1.333;
                break;
            case "D":
                Grade = 1;
                break;
            case "F":
                Grade = 0;
                break;
        }
    }

    public void setGradeString(String gradeString) {
        GradeString = gradeString;
        switch (GradeString) {
            case "A":
                Grade = 4;
                break;
            case "A-":
                Grade = 3.666;
                break;
            case "B+":
                Grade = 3.333;
                break;
            case "B":
                Grade = 3;
                break;
            case "B-":
                Grade = 2.666;
                break;
            case "C+":
                Grade = 2.333;
                break;
            case "C":
                Grade = 2;
                break;
            case "C-":
                Grade = 1.666;
                break;
            case "D+":
                Grade = 1.333;
                break;
            case "D":
                Grade = 1;
                break;
            case "F":
                Grade = 0;
                break;
        }

    }

    public int getCreditHours() {
        return creditHours;
    }

    public double getGrade() {
        return Grade;
    }

}
