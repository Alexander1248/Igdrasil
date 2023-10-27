package ru.alexander.igdrasil._v2.managers.task;

public abstract class Task {
    private final int priority;
    protected int repeatingCount;

    public Task(int priority, int repeatingCount) {
        this.priority = priority;
        this.repeatingCount = repeatingCount;
    }

    public abstract void run();
    public int getPriority() {
        return priority;
    }
}
