package ru.alexander.igdrasil.managers.tick;

import java.util.ArrayList;
import java.util.List;

public class TickManager {
    private final static int[] maxSize = {
            4, 64, 1024, 4096, 65536, 262144
    };

    private final List<TickListener> listeners = new ArrayList<>();

    private final int[] clock;
    private long time;

    public TickManager() {
        time = System.nanoTime();
        clock = new int[maxSize.length + 1];
    }

    public void update() {
        if (System.nanoTime() - time > 15625000) {
            time = System.nanoTime();
            clock[0]++;

            int depth = 0;
            callUpdate(depth);
            while (depth < maxSize.length && clock[depth] >= maxSize[depth]) {
                clock[depth] = 0;
                clock[++depth]++;
                callUpdate(depth);
            }
        }
    }
    private void callUpdate(int depth) {
        switch (depth) {
            case 0 -> {
                for (TickListener listener : listeners)
                    listener.OnTickUpdate();
            }
            case 1 -> {
                for (TickListener listener : listeners)
                    listener.OnShortTickUpdate();
            }
            case 2 -> {
                for (TickListener listener : listeners)
                    listener.OnSecondTickUpdate();
            }
            case 3 -> {
                for (TickListener listener : listeners)
                    listener.OnLongTickUpdate();
            }
            case 4 -> {
                for (TickListener listener : listeners)
                    listener.OnNearMinuteTickUpdate();
            }
            case 5 -> {
                for (TickListener listener : listeners)
                    listener.OnVeryLongTickUpdate();
            }
            case 6 -> {
                for (TickListener listener : listeners)
                    listener.OnNearHourTickUpdate();
            }
        }

    }

    public List<TickListener> getListeners() {
        return listeners;
    }
}
