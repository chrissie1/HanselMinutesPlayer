package be.baes.hanselMinutesPlayer.facade;

import be.baes.hanselMinutesPlayer.model.FillListResult;

import java.util.Observer;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 31/12/11
 * Time: 8:56
 * To change this template use File | Settings | File Templates.
 */
public interface SearchList {
    void search(String query);

    void addObserver(Observer observer);

    void updateList(FillListResult result);

    void deleteObserver(Observer observer);

    void search(String query, int position);
}
