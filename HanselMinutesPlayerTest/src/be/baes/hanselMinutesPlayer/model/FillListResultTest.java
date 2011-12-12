package be.baes.hanselMinutesPlayer.model;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 12/12/11
 * Time: 9:40
 */
public class FillListResultTest extends TestCase {

    public void testIfPositionIsSetByConstructor()
    {
        FillListResult fillListResult = new FillListResult(new ArrayList<PodCast>(),"numberofpodcasts",1);
        assertEquals(1, fillListResult.getPosition());
    }
    
    public void testIfPodCastsIsSetByConstructor()
    {
        List<PodCast> podCasts = new ArrayList<PodCast>();
        FillListResult fillListResult = new FillListResult(podCasts,"numberofpodcasts",1);
        assertEquals(podCasts, fillListResult.getPodCasts());
    }

    public void testIfNumberOfPodCastsIsSetByConstructor()
    {
        FillListResult fillListResult = new FillListResult(new ArrayList<PodCast>(),"numberofpodcasts",1);
        assertEquals("numberofpodcasts", fillListResult.getNumberOfPodCasts());
    }

    public void testIfNumberOfPodCastsCanBeSet()
    {
        FillListResult fillListResult = new FillListResult(new ArrayList<PodCast>(),"numberofpodcasts",1);
        fillListResult.setNumberOfPodCasts("numberofpodcasts2");
        assertEquals("numberofpodcasts2", fillListResult.getNumberOfPodCasts());
    }

    public void testIfPodCastsCanBeSet()
    {
        List<PodCast> podCasts = new ArrayList<PodCast>();
        FillListResult fillListResult = new FillListResult(null,"numberofpodcasts",1);
        fillListResult.setPodCasts(podCasts);
        assertEquals(podCasts, fillListResult.getPodCasts());
    }
}
