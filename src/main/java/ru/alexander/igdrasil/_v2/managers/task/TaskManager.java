package ru.alexander.igdrasil._v2.managers.task;

import java.util.Comparator;
import java.util.PriorityQueue;

public class TaskManager {
    private final PriorityQueue<Task> tasks = new PriorityQueue<>(Comparator.comparingInt(Task::getPriority));

    private final Thread[] threads;
    public TaskManager(int threadCount) {
        threads = new Thread[threadCount];
    }

    public void addTask(Task task) {
        synchronized (tasks) {
            tasks.offer(task);
        }
    }

    private boolean run = true;

    public void start() {
        new Thread(() -> {
            while (run) {
                for (int i = 0; i < threads.length; i++)
                    if (threads[i] == null || !threads[i].isAlive()) {
                        synchronized (tasks) {
                            if (tasks.isEmpty()) break;
                            Task task = tasks.poll();
                            threads[i] = new Thread(() -> {
                                task.run();
                                if (task.repeatingCount > 0) {
                                    synchronized (tasks) {
                                        tasks.add(task);
                                    }
                                    task.repeatingCount--;
                                }
                            });
                            threads[i].start();
                        }
                    }
            }
        }).start();
    }

    public void stop() {
        run = false;
    }
}
