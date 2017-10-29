package com.github.binarylei._2_4;

public class PriorityTask implements Comparable<PriorityTask> {

    private int id;

    public int compareTo(PriorityTask task) {
        return this.id > task.id ? 1 : (this.id < task.id ? -1 : 0);
    }

    public PriorityTask(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
