package com.example.dashboard;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Gpa_Calculator extends AppCompatActivity {

    //  Declare the needed XML elements for the calculator
    private ArrayList<Subject> subjects;
    private EditText crHrsEt, ernHrsET, cgpaET;
    RecyclerView recyclerView;
    private recViewAdapter adapter;
    Button calculate, AddSubj;
    TextView GpaViewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gpa_calculator);



        ernHrsET = findViewById(R.id.ernHrsET);
        cgpaET = findViewById(R.id.cgpaET);
        GpaViewer = findViewById(R.id.GpaViewer);
        crHrsEt = findViewById(R.id.crHrsET);
        calculate = findViewById(R.id.calculate);
        AddSubj = findViewById(R.id.AddSubj);

        // Recycler View start
        subjects = new ArrayList<>();
        setSubjects(subjects);
        recyclerView = findViewById(R.id.itemList);
        adapter = new recViewAdapter(this, subjects);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        // Recycler View end

//      The onClick listener for the calculate button of the GpaCalculator layout file
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int totalNumberOfHours = 0, earnedHours;
                float totalGrade = 0, gpa, cgpa;
                String earnedHoursS = ernHrsET.getText().toString();
                String cgpaS = cgpaET.getText().toString();

                //      It won't take input unless the user types both
                //      The earned hours and The CGPA of the past semesters
                if (!earnedHoursS.equals("") && !cgpaS.equals("") && (Float.parseFloat(cgpaS) <= 4)) {
                    cgpa = Float.parseFloat(cgpaS);
                    earnedHours = Integer.parseInt(earnedHoursS);
                } else {
                    earnedHours = 0;
                    cgpa = 0;
                    ernHrsET.setText("");
                    cgpaET.setText("");
                }

                //      Grade Loop to calculate The total Grade (The nominator)
                for (int i = 0; i < subjects.size(); i++) {
                    totalGrade += subjects.get(i).getCreditHours() * subjects.get(i).getGrade();
                }
                //      Loop to calculate the total Number of hours of subjects
                for (int i = 0; i < subjects.size(); i++) {
                    totalNumberOfHours += subjects.get(i).getCreditHours();
                }
                //      Adds the earned hours of the past semesters to our total number of hours
                totalNumberOfHours += earnedHours;
                //      Calculates the Gpa
                gpa = (totalGrade + (cgpa * earnedHours)) / (float) totalNumberOfHours;

                //      Set the text of the GpaViewer text view to the calculated GPA
                GpaViewer.setText("Your GPA is: " + gpa);
            }
        });

//      Click listener for the AddSubject Button
        AddSubj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = crHrsEt.getText().toString();
                if (string.equals("")) {
                } else {
                    subjects.add(new Subject(Integer.parseInt(string)));
                    adapter.notifyItemInserted(subjects.size() - 1);
                    crHrsEt.setText("");
                }
            }
        });

    }


    //  Method to initialize the ArrayList of items of recycler view
    public void setSubjects(ArrayList<Subject> subs) {
        for (int i = 0; i < subs.size(); i++) {
            subs.add(new Subject(0));
        }
    }

}

