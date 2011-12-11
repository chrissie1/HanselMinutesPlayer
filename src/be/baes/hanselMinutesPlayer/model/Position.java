package be.baes.hanselMinutesPlayer.model;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 11/12/11
 * Time: 10:53
 * To change this template use File | Settings | File Templates.
 */
public class Position {
    private String timer;
    private String message;
    private int progress;
    private int maxDuration;
    private boolean hasPodCast;

    public Position(String timer, String message, int progress, int maxDuration, boolean hasPodCast) {
        this.timer = timer;
        this.message = message;
        this.progress = progress;
        this.maxDuration = maxDuration;
        this.hasPodCast = hasPodCast;
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
}
