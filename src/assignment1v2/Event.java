package assignment1v2;

import java.util.Random;

/**
 *
 * @author Guan Wang, 12141158
 */

//This is the super class of ArrivalEvent, OrderEvent and LeaveEvent. 
//Each event type will inherit from this and implement their own process method
public abstract class Event {
    //member variable time, used to record event happen time.
    private int time = 0;
    private static Random generator = new Random(1);//use a seed of 1

    public abstract void process(ShopModel shopmodel, IScheduler scheduler);
    
    //constractor of this class
    public Event(int time) {
        this.time = time;
    }

    //method to get the random number generator
    public Random getGenerator() {
        return generator;
    }

    //use this method to get the time
    public int getTime() {
        return time;
    }

}
