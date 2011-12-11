package be.baes.hanselMinutesPlayer.view;

public interface ProgressReport {
    void startProgress(String message);

    void endProgress();

    void updateProgess(String message);
}
