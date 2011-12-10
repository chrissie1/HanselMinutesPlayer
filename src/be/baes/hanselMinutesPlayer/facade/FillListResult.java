package be.baes.hanselMinutesPlayer.facade;

import be.baes.hanselMinutesPlayer.adapters.PodCastAdapter;

public class FillListResult
{
    private PodCastAdapter podCastAdapter;
    private String numberOfPodCasts;
    private int page;

    public FillListResult(PodCastAdapter podCastAdapter, String numberOfPodCasts, int page) {
        this.podCastAdapter = podCastAdapter;
        this.numberOfPodCasts = numberOfPodCasts;
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
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
