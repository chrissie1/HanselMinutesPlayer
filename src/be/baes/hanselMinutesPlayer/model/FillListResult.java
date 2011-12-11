package be.baes.hanselMinutesPlayer.model;

import be.baes.hanselMinutesPlayer.adapters.PodCastAdapter;

public class FillListResult
{
    private PodCastAdapter podCastAdapter;
    private String numberOfPodCasts;
    private int position;

    public FillListResult(PodCastAdapter podCastAdapter, String numberOfPodCasts, int position) {
        this.podCastAdapter = podCastAdapter;
        this.numberOfPodCasts = numberOfPodCasts;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public PodCastAdapter getPodCastAdapter() {
        return podCastAdapter;
    }

    public void setPodCastAdapter(PodCastAdapter podCastAdapter) {
        this.podCastAdapter = podCastAdapter;
    }

    public String getNumberOfPodCasts() {
        return numberOfPodCasts;
    }

    public void setNumberOfPodCasts(String numberOfPodCasts) {
        this.numberOfPodCasts = numberOfPodCasts;
    }
}
