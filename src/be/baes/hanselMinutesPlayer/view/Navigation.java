package be.baes.hanselMinutesPlayer.view;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 30/12/11
 * Time: 10:25
 * To change this template use File | Settings | File Templates.
 */
public interface Navigation {
    void openSettings();
    void openDetails(String parent);
    void openSearch();
    void openMain();
    void openDetailsWitFlingAnimation(String parent);
    void openMainWithFlingAnimation();
    void openSearchWithFlingAnimation();
}
