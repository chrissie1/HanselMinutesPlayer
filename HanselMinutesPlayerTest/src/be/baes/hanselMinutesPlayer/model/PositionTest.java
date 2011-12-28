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
        Position position = new Position("timer","message",1,2,true,"description");
        assertEquals("timer",position.getTimer());
    }

    public void testIfMessageIsSetByConstructor()
    {
        Position position = new Position("timer","message",1,2,true,"description");
        assertEquals("message",position.getMessage());
    }

    public void testIfProgressIsSetByConstructor()
    {
        Position position = new Position("timer","message",1,2,true,"description");
        assertEquals(1,position.getProgress());
    }

    public void testIfMaxDurationIsSetByConstructor()
    {
        Position position = new Position("timer","message",1,2,true,"description");
        assertEquals(2,position.getMaxDuration());
    }

    public void testIfHasPodCastIsSetByConstructor()
    {
        Position position = new Position("timer","message",1,2,true,"description");
        assertTrue(position.getHasPodCast());
    }

    public void testIfDescriptionIsSetByConstructor()
    {
        Position position = new Position("timer","message",1,2,true,"description");
        assertEquals("description", position.getDescription());
    }

    public void testIfTimerCanBeSet()
    {
        Position position = new Position("timer","message",1,2,true,"description");
        position.setTimer("timer2");
        assertEquals("timer2",position.getTimer());
    }

    public void testIfMessageCanBeSet()
    {
        Position position = new Position("timer","message",1,2,true,"description");
        position.setMessage("message2");
        assertEquals("message2",position.getMessage());
    }

    public void testIfProgressCanBeSet()
    {
        Position position = new Position("timer","message",1,2,true,"description");
        position.setProgress(4);
        assertEquals(4,position.getProgress());
    }

    public void testIfMaxDurationCanBeSet()
    {
        Position position = new Position("timer","message",1,2,true,"description");
        position.setMaxDuration(5);
        assertEquals(5,position.getMaxDuration());
    }

    public void testIfHasPodCastCanBeSet()
    {
        Position position = new Position("timer","message",1,2,true,"description");
        position.setHasPodCast(false);
        assertFalse(position.getHasPodCast());
    }

    public void testIfDescriptionCanBeSet()
    {
        Position position = new Position("timer","message",1,2,true,"description");
        position.setDescription("test");
        assertEquals("test",position.getDescription());
    }
}
