package be.baes.hanselMinutesPlayer.dal;

import be.baes.hanselMinutesPlayer.model.PodCast;

import java.util.List;

public interface PodCastAdapter {

    List<PodCast> getAllItems();

    boolean updatePodCast(PodCast podCast);

    long insertPodCast(PodCast podCast);

    boolean deleteAll();
    
    int numberOfPodcasts();
    
    List<PodCast> getAllItems(Integer pageFrom, Integer pageTo);

    List<PodCast> findItems(String s);
}
