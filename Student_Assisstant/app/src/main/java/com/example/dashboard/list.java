package com.example.dashboard;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dashboard.Adapter.ToDoAdapter;
import com.example.dashboard.Model.toDoModel;
import com.example.dashboard.Utils.AddNewTask;
import com.example.dashboard.Utils.DatabaseHandler;
import com.example.dashboard.Utils.DialogCloseListener;
import com.example.dashboard.Utils.RecyclerItemTouchHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class list extends AppCompatActivity implements DialogCloseListener {
    //give access modifer to our objects
    private DatabaseHandler db;
    private RecyclerView tasksRecyclerView;
    private ToDoAdapter tasksAdapter;
    private FloatingActionButton fab;
    private List<toDoModel> taskList;//store all of our task


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        //hide Action bar
        getSupportActionBar().hide();

        db = new DatabaseHandler(this);
        db.openDatabase();
        //this action help us to create new card in recyclerview
        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //start give our Adapter the data base and activity to work on it
        tasksAdapter = new ToDoAdapter(db, list.this);
        tasksRecyclerView.setAdapter(tasksAdapter);

        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new RecyclerItemTouchHelper(tasksAdapter));
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView);

        fab = findViewById(R.id.fab);

        taskList = db.getAllTasks(MainActivity.email);
        Collections.reverse(taskList);

        tasksAdapter.setTasks(taskList);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTask.newInstance().show(getSupportFragmentManager(), AddNewTask.TAG);
            }
        });
    }

    @Override
    public void handleDialogClose(DialogInterface dialog){
        taskList = db.getAllTasks(MainActivity.email);
        Collections.reverse(taskList);
        tasksAdapter.setTasks(taskList);
        tasksAdapter.notifyDataSetChanged();
    }
}