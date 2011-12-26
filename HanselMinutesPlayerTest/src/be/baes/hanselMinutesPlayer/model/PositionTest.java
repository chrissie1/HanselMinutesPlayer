package be.baes.hanselMinutesPlayer.model;

import junit.framework.TestCase;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 12/12/11
 * Time: 9:22
 */
public class PositionTest extends TestCase {
    
    public void testIfTimerIsSetByConstructor()
    {
        Position position = new Position("timer","message",1,2,true);
        assertEquals("timer",position.getTimer());
    }

    public void testIfMessageIsSetByConstructor()
    {
        Position position = new Position("timer","message",1,2,true);
        assertEquals("message",position.getMessage());
    }

    public void testIfProgressIsSetByConstructor()
    {
        Position position = new Position("timer","message",1,2,true);
        assertEquals(1,position.getProgress());
    }

    public void testIfMaxDurationIsSetByConstructor()
    {
        Position position = new Position("timer","message",1,2,true);
        assertEquals(2,position.getMaxDuration());
    }

    public void testIfHasPodCastIsSetByConstructor()
    {
        Position position = new Position("timer","message",1,2,true);
        assertTrue(position.getHasPodCast());
    }

    public void testIfTimerCanBeSet()
    {
        Position position = new Position("timer","message",1,2,true);
        position.setTimer("timer2");
        assertEquals("timer2",position.getTimer());
    }

    public void testIfMessageCanBeSet()
    {
        Position position = new Position("timer","message",1,2,true);
        position.setMessage("message2");
        assertEquals("message2",position.getMessage());
    }

    public void testIfProgressCanBeSet()
    {
        Position position = new Position("timer","message",1,2,true);
        position.setProgress(4);
        assertEquals(4,position.getProgress());
    }

    public void testIfMaxDurationCanBeSet()
    {
        Position position = new Position("timer","message",1,2,true);
        position.setMaxDuration(5);
        assertEquals(5,position.getMaxDuration());
    }

    public void testIfHasPodCastCanBeSet()
    {
        Position position = new Position("timer","message",1,2,true);
        position.setHasPodCast(false);
        assertFalse(position.getHasPodCast());
    }
}
