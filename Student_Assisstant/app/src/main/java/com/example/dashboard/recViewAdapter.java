package com.example.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recViewAdapter extends RecyclerView.Adapter<recViewAdapter.ViewHolder> {


    Context context;
    ArrayList<Subject> subjects;

    //  The constructor for our Recycler View Adapter
    public recViewAdapter(Context context, ArrayList<Subject> subjects) {

        this.context = context;
        this.subjects = subjects;

    }

    //  The overridden onCreateViewHolder method for our View Holder
    @Override
    public @NonNull
    ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
    }

    //  The overridden onBindViewHolder method for our View Holder
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

//      Sets the text view of the item to the entered number in the Subjects Hours Edit Text
        holder.crHrs.setText(String.valueOf(subjects.get(holder.getAdapterPosition()).getCreditHours()));

//      Sets the Spinner of Grades to A by default
        holder.GradeSpinner.setSelection(0);
        subjects.get(holder.getAdapterPosition()).setGradeString(holder.gpa.get(0));

//      Item selected listener for the Spinner of Grades to set the Grade of the subject to the selected Grade
        holder.GradeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                subjects.get(holder.getAdapterPosition()).setGradeString(holder.gpa.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

//      Long Click listener for the item of the list to delete the item
//      on Condition that it is long clicked
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                subjects.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                return true;
            }
        });
    }

    //  The overridden getItemCount method for our Adapter
    @Override
    public int getItemCount() {
        return subjects.size();
    }

    //  View holder class which holds the created item of the recycler view
    public class ViewHolder extends RecyclerView.ViewHolder {

        //      Declare the needed XML elements
        Spinner GradeSpinner;
        private final TextView crHrs;
        ArrayList<String> gpa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            GradeSpinner = itemView.findViewById(R.id.Spinner1);
            crHrs = itemView.findViewById(R.id.crHrs);

            //Spinner start
            gpa = new ArrayList<>();
            gpa.add("A");
            gpa.add("A-");
            gpa.add("B+");
            gpa.add("B");
            gpa.add("B-");
            gpa.add("C+");
            gpa.add("C");
            gpa.add("C-");
            gpa.add("D+");
            gpa.add("D");
            gpa.add("F");
            ArrayAdapter<String> gpaAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, gpa);
            GradeSpinner.setAdapter(gpaAdapter);
            GradeSpinner.setSelection(0);
            // Spinner end


        }
    }
}
