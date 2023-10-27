package ru.alexander.igdrasil._v2.managers.tick;

public class TickListener {
    /**
     * 1 standard tick = 1 / 64 second
     */
    public void OnTickUpdate() {}

    /**
     * 1 short tick = 1 / 16 second
     */
    public void OnShortTickUpdate() {}

    /**
     * 1 second tick = 1 second
     */
    public void OnSecondTickUpdate() {}

    /**
     * 1 long tick = 16 seconds
     */
    public void OnLongTickUpdate() {}

    /**
     * 1 near minute tick = 64 seconds = 1.0(6) minutes
     */
    public void OnNearMinuteTickUpdate() {}

    /**
     * 1 very long tick = 1024 seconds = 17.0(6) minutes
     */
    public void OnVeryLongTickUpdate() {}

    /**
     * 1 near hour tick = 4096 seconds = 68.2(6) minutes = 1.13(7) hours
     */
    public void OnNearHourTickUpdate() {}
}
