package be.baes.hanselMinutesPlayer.facade;

import be.baes.hanselMinutesPlayer.model.Position;
import be.baes.hanselMinutesPlayer.resources.StringResources;
import com.google.inject.Singleton;
import junit.framework.TestCase;
import static org.easymock.EasyMock.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 12/12/11
 * Time: 10:01
 */
@Singleton
public class PositionUpdaterTest extends TestCase {
    private Position position;
    private PositionUpdater positionUpdater;
    private Player player;
    private StringResources stringResources;
    
    public void setUp()
    {
        positionUpdater = new PositionUpdater();
        player = createMock(Player.class);
        stringResources = createMock(StringResources.class);
        positionUpdater.player = player;
        positionUpdater.stringResources = stringResources;
        TestObserver testObserver = new TestObserver();
        positionUpdater.addObserver(testObserver);
    }

    public void tearDown()
    {
    }

    protected class TestObserver implements Observer
    {
        @Override
        public void update(Observable observable, Object o) {
            position = (Position)o;
        }
    }

    public void testIfEmptyFileHasCorrectMessageInPosition()
    {
        expect(stringResources.getTimerWithTime()).andStubReturn("Timer: %s / %s");
        expect(stringResources.getNoFileSelected()).andStubReturn("No file selected.");
        replay(stringResources);
        positionUpdater.emptyFile();
        assertEquals("No file selected.", position.getMessage());
    }

    public void testIfEmptyFileHasCorrectProgressInPosition()
    {
        expect(stringResources.getTimerWithTime()).andStubReturn("Timer: %s / %s");
        expect(stringResources.getNoFileSelected()).andStubReturn("No file selected.");
        replay(stringResources);
        positionUpdater.emptyFile();
        assertEquals(0, position.getProgress());
    }

    public void testIfEmptyFileHasCorrectMaxDurationInPosition()
    {
        expect(stringResources.getTimerWithTime()).andStubReturn("Timer: %s / %s");
        expect(stringResources.getNoFileSelected()).andStubReturn("No file selected.");
        replay(stringResources);
        positionUpdater.emptyFile();
        assertEquals(0, position.getMaxDuration());
    }

    public void testIfEmptyFileHasCorrectTimerInPosition()
    {
        expect(stringResources.getTimerWithTime()).andStubReturn("Timer: %s / %s");
        expect(stringResources.getNoFileSelected()).andStubReturn("No file selected.");
        replay(stringResources);
        positionUpdater.emptyFile();
        assertEquals("Timer: 00:00 / 00:00", position.getTimer());
    }

    public void testIfEmptyFileHasCorrectHasPodCastInPosition()
    {
        expect(stringResources.getTimerWithTime()).andStubReturn("Timer: %s / %s");
        expect(stringResources.getNoFileSelected()).andStubReturn("No file selected.");
        replay(stringResources);
        positionUpdater.emptyFile();
        assertFalse(position.getHasPodCast());
    }

    public void testIfStopPositionHasCorrectTimerInPosition()
    {
        expect(stringResources.getTimerWithTime()).andStubReturn("Timer: %s / %s");
        expect(stringResources.getStopped()).andStubReturn("Stopped: %s");
        replay(stringResources);
        expect(player.getDuration()).andStubReturn(10000);
        expect(player.getCurrentTitle()).andStubReturn("");
        replay(player);
        positionUpdater.stopPosition();
        assertEquals("Timer: 00:00 / 00:10", position.getTimer());
    }

    public void testIfStopPositionHasCorrectMessageInPosition()
    {
        expect(stringResources.getTimerWithTime()).andStubReturn("Timer: %s / %s");
        expect(stringResources.getStopped()).andStubReturn("Stopped: %s");
        replay(stringResources);
        expect(player.getDuration()).andStubReturn(10);
        expect(player.getCurrentTitle()).andStubReturn("file");
        replay(player);
        positionUpdater.stopPosition();
        assertEquals("Stopped: file", position.getMessage());
    }

    public void testIfStopPositionHasCorrectMaxDurationInPosition()
    {
        expect(stringResources.getTimerWithTime()).andStubReturn("Timer: %s / %s");
        expect(stringResources.getStopped()).andStubReturn("Stopped: %s");
        replay(stringResources);
        expect(player.getDuration()).andStubReturn(10);
        expect(player.getCurrentTitle()).andStubReturn("");
        replay(player);
        positionUpdater.stopPosition();
        assertEquals(10, position.getMaxDuration());
    }

    public void testIfStopPositionHasCorrectProgressInPosition()
    {
        expect(stringResources.getTimerWithTime()).andStubReturn("Timer: %s / %s");
        expect(stringResources.getStopped()).andStubReturn("Stopped: %s");
        replay(stringResources);
        expect(player.getDuration()).andStubReturn(0);
        expect(player.getCurrentTitle()).andStubReturn("");
        replay(player);
        positionUpdater.stopPosition();
        assertEquals(0, position.getProgress());
    }

    public void testIfStopPositionHasCorrectHasPodCastInPosition()
    {
        expect(stringResources.getTimerWithTime()).andStubReturn("Timer: %s / %s");
        expect(stringResources.getStopped()).andStubReturn("Stopped: %s");
        replay(stringResources);
        expect(player.getDuration()).andStubReturn(0);
        expect(player.getCurrentTitle()).andStubReturn("");
        replay(player);
        positionUpdater.stopPosition();
        assertTrue(position.getHasPodCast());
    }

    public void testIfPausePositionHasCorrectTimerInPosition()
    {
        expect(stringResources.getTimerWithTime()).andStubReturn("Timer: %s / %s");
        expect(stringResources.getPausing()).andStubReturn("Pausing: %s");
        replay(stringResources);
        expect(player.getDuration()).andStubReturn(10000);
        expect(player.getCurrentTitle()).andStubReturn("");
        expect(player.getCurrentPosition()).andStubReturn(1000);
        replay(player);
        positionUpdater.pausePosition();
        assertEquals("Timer: 00:01 / 00:10", position.getTimer());
    }

    public void testIfPausePositionHasCorrectMessageInPosition()
    {
        expect(stringResources.getTimerWithTime()).andStubReturn("Timer: %s / %s");
        expect(stringResources.getPausing()).andStubReturn("Pausing: %s");
        replay(stringResources);
        expect(player.getDuration()).andStubReturn(10);
        expect(player.getCurrentTitle()).andStubReturn("file");
        expect(player.getCurrentPosition()).andStubReturn(1000);
        replay(player);
        positionUpdater.pausePosition();
        assertEquals("Pausing: file", position.getMessage());
    }

    public void testIfPausePositionHasCorrectMaxDurationInPosition()
    {
        expect(stringResources.getTimerWithTime()).andStubReturn("Timer: %s / %s");
        expect(stringResources.getPausing()).andStubReturn("Pausing: %s");
        replay(stringResources);
        expect(player.getDuration()).andStubReturn(10);
        expect(player.getCurrentTitle()).andStubReturn("");
        expect(player.getCurrentPosition()).andStubReturn(1000);
        replay(player);
        positionUpdater.pausePosition();
        assertEquals(10, position.getMaxDuration());
    }

    public void testIfPausePositionHasCorrectProgressInPosition()
    {
        expect(stringResources.getTimerWithTime()).andStubReturn("Timer: %s / %s");
        expect(stringResources.getPausing()).andStubReturn("Pausing: %s");
        replay(stringResources);
        expect(player.getDuration()).andStubReturn(0);
        expect(player.getCurrentTitle()).andStubReturn("");
        expect(player.getCurrentPosition()).andStubReturn(1000);
        replay(player);
        positionUpdater.pausePosition();
        assertEquals(1000, position.getProgress());
    }

    public void testIfPausePositionHasCorrectHasPodCastInPosition()
    {
        expect(stringResources.getTimerWithTime()).andStubReturn("Timer: %s / %s");
        expect(stringResources.getPausing()).andStubReturn("Pausing: %s");
        replay(stringResources);
        expect(player.getDuration()).andStubReturn(0);
        expect(player.getCurrentTitle()).andStubReturn("");
        expect(player.getCurrentPosition()).andStubReturn(1000);
        replay(player);
        positionUpdater.pausePosition();
        assertTrue(position.getHasPodCast());
    }

    public void testIfStartPositionHasCorrectTimerInPosition()
    {
        expect(stringResources.getTimerWithTime()).andStubReturn("Timer: %s / %s");
        expect(stringResources.getSelected()).andStubReturn("Selected: %s");
        replay(stringResources);
        expect(player.getDuration()).andStubReturn(10000);
        expect(player.getCurrentTitle()).andStubReturn("");
        expect(player.getCurrentPosition()).andStubReturn(1000);
        replay(player);
        positionUpdater.startPosition();
        assertEquals("Timer: 00:00 / 00:10", position.getTimer());
    }

    public void testIfStartPositionHasCorrectMessageInPosition()
    {
        expect(stringResources.getTimerWithTime()).andStubReturn("Timer: %s / %s");
        expect(stringResources.getSelected()).andStubReturn("Selected: %s");
        replay(stringResources);
        expect(player.getDuration()).andStubReturn(10);
        expect(player.getCurrentTitle()).andStubReturn("file");
        expect(player.getCurrentPosition()).andStubReturn(1000);
        replay(player);
        positionUpdater.startPosition();
        assertEquals("Selected: file", position.getMessage());
    }

    public void testIfStartPositionHasCorrectMaxDurationInPosition()
    {
        expect(stringResources.getTimerWithTime()).andStubReturn("Timer: %s / %s");
        expect(stringResources.getSelected()).andStubReturn("Selected: %s");
        replay(stringResources);
        expect(player.getDuration()).andStubReturn(10);
        expect(player.getCurrentTitle()).andStubReturn("");
        expect(player.getCurrentPosition()).andStubReturn(1000);
        replay(player);
        positionUpdater.startPosition();
        assertEquals(10, position.getMaxDuration());
    }

    public void testIfStartPositionHasCorrectProgressInPosition()
    {
        expect(stringResources.getTimerWithTime()).andStubReturn("Timer: %s / %s");
        expect(stringResources.getSelected()).andStubReturn("Selected: %s");
        replay(stringResources);
        expect(player.getDuration()).andStubReturn(0);
        expect(player.getCurrentTitle()).andStubReturn("");
        expect(player.getCurrentPosition()).andStubReturn(1000);
        replay(player);
        positionUpdater.startPosition();
        assertEquals(0, position.getProgress());
    }

    public void testIfStartPositionHasCorrectHasPodCastInPosition()
    {
        expect(stringResources.getTimerWithTime()).andStubReturn("Timer: %s / %s");
        expect(stringResources.getSelected()).andStubReturn("Selected: %s");
        replay(stringResources);
        expect(player.getDuration()).andStubReturn(0);
        expect(player.getCurrentTitle()).andStubReturn("");
        replay(player);
        positionUpdater.startPosition();
        assertTrue(position.getHasPodCast());
    }

    public void testIfUpdatePositionHasCorrectTimerInPosition()
    {
        expect(stringResources.getTimerWithTime()).andStubReturn("Timer: %s / %s");
        expect(stringResources.getPlaying()).andStubReturn("Playing: %s");
        replay(stringResources);
        expect(player.getDuration()).andStubReturn(10000);
        expect(player.getCurrentTitle()).andStubReturn("");
        expect(player.getCurrentPosition()).andStubReturn(1000);
        replay(player);
        positionUpdater.updatePosition();
        assertEquals("Timer: 00:01 / 00:10", position.getTimer());
    }

    public void testIfUpdatePositionHasCorrectMessageInPosition()
    {
        expect(stringResources.getTimerWithTime()).andStubReturn("Timer: %s / %s");
        expect(stringResources.getPlaying()).andStubReturn("Playing: %s");
        replay(stringResources);
        expect(player.getDuration()).andStubReturn(10);
        expect(player.getCurrentTitle()).andStubReturn("file");
        expect(player.getCurrentPosition()).andStubReturn(1000);
        replay(player);
        positionUpdater.updatePosition();
        assertEquals("Playing: file", position.getMessage());
    }

    public void testIfUpdatePositionHasCorrectMaxDurationInPosition()
    {
        expect(stringResources.getTimerWithTime()).andStubReturn("Timer: %s / %s");
        expect(stringResources.getPlaying()).andStubReturn("Playing: %s");
        replay(stringResources);
        expect(player.getDuration()).andStubReturn(10);
        expect(player.getCurrentTitle()).andStubReturn("");
        expect(player.getCurrentPosition()).andStubReturn(1000);
        replay(player);
        positionUpdater.updatePosition();
        assertEquals(10, position.getMaxDuration());
    }

    public void testIfUpdatePositionHasCorrectProgressInPosition()
    {
        expect(stringResources.getTimerWithTime()).andStubReturn("Timer: %s / %s");
        expect(stringResources.getPlaying()).andStubReturn("Playing: %s");
        replay(stringResources);
        expect(player.getDuration()).andStubReturn(0);
        expect(player.getCurrentTitle()).andStubReturn("");
        expect(player.getCurrentPosition()).andStubReturn(1000);
        replay(player);
        positionUpdater.updatePosition();
        assertEquals(1000, position.getProgress());
    }

    public void testIfUpdatePositionHasCorrectHasPodCastInPosition()
    {
        expect(stringResources.getTimerWithTime()).andStubReturn("Timer: %s / %s");
        expect(stringResources.getPlaying()).andStubReturn("Playing: %s");
        replay(stringResources);
        expect(player.getDuration()).andStubReturn(0);
        expect(player.getCurrentTitle()).andStubReturn("");
        expect(player.getCurrentPosition()).andStubReturn(1000);
        replay(player);
        positionUpdater.updatePosition();
        assertTrue(position.getHasPodCast());
    }

}
