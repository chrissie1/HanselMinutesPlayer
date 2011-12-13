package be.baes.hanselMinutesPlayer.facade;

import be.baes.hanselMinutesPlayer.model.FillListResult;
import roboguice.inject.ContextScoped;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 12/12/11
 * Time: 14:38
 * To change this template use File | Settings | File Templates.
 */
public interface PodCastList {
    void addObserver(Observer observer);

    void load(int page);

    void getListFromRssAndUpdateDatabase();

    void updateList(FillListResult result);
}
