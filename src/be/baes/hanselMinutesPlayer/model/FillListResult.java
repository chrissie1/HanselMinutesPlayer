package be.baes.hanselMinutesPlayer.model;

import java.util.List;

public class FillListResult
{
    private List<PodCast> podCasts;
    private String numberOfPodCasts;
    private int position;

    public FillListResult(List<PodCast> podCasts, String numberOfPodCasts, int position) {
        this.podCasts = podCasts;
        this.numberOfPodCasts = numberOfPodCasts;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public List<PodCast> getPodCasts() {
        return podCasts;
    }

    public void setPodCasts(List<PodCast> podCasts) {
        this.podCasts = podCasts;
    }

    public String getNumberOfPodCasts() {
        return numberOfPodCasts;
    }

    public void setNumberOfPodCasts(String numberOfPodCasts) {
        this.numberOfPodCasts = numberOfPodCasts;
    }
}
