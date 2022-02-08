package com.example.tasks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FinishedTasksFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    TaskAdapter finishedTasksAdapter;

    public FinishedTasksFragment(TaskAdapter tasksOnHoldAdapter) {
        this.finishedTasksAdapter = new TaskAdapter(tasksOnHoldAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_finished_tasks, container, false);
        recyclerView = view.findViewById(R.id.rvFinishedTasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(finishedTasksAdapter);
        return view;
    }

    public TaskAdapter getFinishedTasksAdapter() {
        return finishedTasksAdapter;
    }
}