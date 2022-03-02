package assignment1v2;

/**
 *
 * @author Guan Wang, 12141158
 */

//This is implemented by the Simulator class as the simulator will be required 
//to schedule events in the event queue according to their scheduled time.
public interface IScheduler {
    public void schedule(Event e);
}
