package be.baes.hanselMinutesPlayer.model;

import java.io.Serializable;

public class Position implements Serializable {
    private String timer;
    private String message;
    private int progress;
    private int maxDuration;
    private boolean hasPodCast;
    private String description;

    public Position(String timer, String message, int progress, int maxDuration, boolean hasPodCast, String description) {
        this.timer = timer;
        this.message = message;
        this.progress = progress;
        this.maxDuration = maxDuration;
        this.hasPodCast = hasPodCast;
        this.description = description;
    }

    public boolean getHasPodCast() {
        return hasPodCast;
    }

    public void setHasPodCast(boolean hasPodCast) {
        this.hasPodCast = hasPodCast;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getMaxDuration() {
        return maxDuration;
    }

    public void setMaxDuration(int maxDuration) {
        this.maxDuration = maxDuration;
    }

    public String getTimer() {
        return timer;
    }

    public void setTimer(String timer) {
        this.timer = timer;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
}
