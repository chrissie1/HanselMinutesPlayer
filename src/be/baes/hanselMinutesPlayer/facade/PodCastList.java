package be.baes.hanselMinutesPlayer.facade;

import android.content.res.Resources;
import be.baes.hanselMinutesPlayer.model.FillListResult;

import java.util.Observer;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 12/12/11
 * Time: 14:38
 */
public interface PodCastList {
    void addObserver(Observer observer);

    void load(int page);

    void load(int page, int position);

    void getListFromRssAndUpdateDatabase(String feed);

    void updateList(FillListResult result);

    int getCurrentPage();

    void deleteObserver(Observer observer);
}
