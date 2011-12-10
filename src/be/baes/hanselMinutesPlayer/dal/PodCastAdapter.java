package be.baes.hanselMinutesPlayer.dal;

import be.baes.hanselMinutesPlayer.model.PodCast;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 9/12/11
 * Time: 9:39
 * To change this template use File | Settings | File Templates.
 */
public interface PodCastAdapter {

    List<PodCast> getAllItems();

    boolean updatePodCast(PodCast podCast);

    long insertPodCast(PodCast podCast);

    boolean deleteAll();
    
    int numberOfPodcasts();
    
    List<PodCast> getAllItems(Integer pageFrom, Integer pageTo);
}
