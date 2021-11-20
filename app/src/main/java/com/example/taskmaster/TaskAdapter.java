package com.example.taskmaster;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    public List<Task> taskList;

    public TaskAdapter(List<Task> taskList) {
        this.taskList = taskList;
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder{
        public Task task;
        public View view;
        public TaskViewHolder (@NonNull View view){
            super(view);
            this.view=view;
        }


    }
    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder (@NonNull ViewGroup parent ,int viewTitle ){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task,parent,false);
        TaskViewHolder taskViewHolder = new TaskViewHolder(view);
        return taskViewHolder;
    }

    @Override
    public  void onBindViewHolder (@NonNull TaskViewHolder holder , int position ){
        holder.task = taskList.get(position);
        TextView title=holder.itemView.findViewById(R.id.taskTitle);
        TextView body=holder.itemView.findViewById(R.id.taskBody);
        TextView state=holder.itemView.findViewById(R.id.taskState);
        
    }

    @Override
    public int getItemCount (){
        return taskList.size();
    }

}
