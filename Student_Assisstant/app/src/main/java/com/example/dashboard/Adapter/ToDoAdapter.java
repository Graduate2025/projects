package com.example.dashboard.Adapter;
// this class responsable about each operation happen in task like delet and edit etc

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dashboard.Model.toDoModel;
import com.example.dashboard.R;
import com.example.dashboard.Utils.AddNewTask;
import com.example.dashboard.Utils.DatabaseHandler;
import com.example.dashboard.list;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {
    //give access modifer to link this class with other classes
    private List<toDoModel> todoList;
    private DatabaseHandler db;
    private list activity;
    //constructor take the the place of card and activity work in
    public ToDoAdapter(DatabaseHandler db, list activity) {
        this.db = db;
        this.activity = activity;
    }

    @NonNull
    @Override
    //this function call the task layout and return it to itemview
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        db.openDatabase();

        final toDoModel item = todoList.get(position);//get postion of specfic task
        holder.task.setText(item.getTask());//get text in the task
        holder.task.setChecked(toBoolean(item.getStatus()));//know the status of checkbox
        holder.task.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    db.updateStatus(item.getId(), 1);
                } else {
                    db.updateStatus(item.getId(), 0);
                }
            }
        });
    }
    //we use this function in chek the status of check box
    private boolean toBoolean(int n) {
        return n != 0;
    }
    //help recycler view to know the number of card
    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public Context getContext() {
        return activity;
    }

    public void setTasks(List<toDoModel> todoList) {
        this.todoList = todoList;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        toDoModel item = todoList.get(position);
        db.deleteTask(item.getId());
        todoList.remove(position);
        notifyItemRemoved(position);
    }

    public void editItem(int position) {
        toDoModel item = todoList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("task", item.getTask());
        AddNewTask fragment = new AddNewTask();
        fragment.setArguments(bundle);
        fragment.show(activity.getSupportFragmentManager(), AddNewTask.TAG);
    }

    //this function which get the view of  checkbox
    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox task;

        ViewHolder(View view) {
            super(view);
            task = view.findViewById(R.id.todoCheckBox);
        }
    }
}